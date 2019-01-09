package com.xhj.quickdevelopment.net;


import android.content.Context;

import com.orhanobut.logger.Logger;
import com.xhj.quickdevelopment.MainApplication;
import com.xhj.quickdevelopment.utils.FileUtils;
import com.xhj.quickdevelopment.utils.NetworkUtils;


import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author @Qiu
 * @version V1.0
 * @Description:okHttp 帮助类
 * @date 2017/7/11 下午2:17
 */
public class OkHttpHelper {
	//读取时间
	public static final long DEFAULT_READ_TIMEOUT_MILLIS = 20 * 1000;
	//写入时间
	public static final long DEFAULT_WRITE_TIMEOUT_MILLIS = 20 * 1000;
	//超时时间
	public static final long DEFAULT_CONNECT_TIMEOUT_MILLIS = 20 * 1000;
	//最大缓存
	private static final long HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 20 * 1024 * 1024;//设置20M
	//长缓存有效期为7天
	public static final int CACHE_STALE_LONG = 60 * 60 * 24 * 7;

	private static volatile OkHttpHelper sInstance;

	private OkHttpClient mOkHttpClient;
	private Context mContext = MainApplication.getContext();

	private OkHttpHelper() {
		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
			@Override
			public void log(String message) {
				Logger.d("message:" + message);
			}
		});
		//包含header、body数据
		loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

		mOkHttpClient = new OkHttpClient.Builder()
				.readTimeout(DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
				.writeTimeout(DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
				.connectTimeout(DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
				// 失败重发
				.retryOnConnectionFailure(true)
				.addNetworkInterceptor(mRewriteCacheControlInterceptor)
				.addInterceptor(mRewriteCacheControlInterceptor)
				.addInterceptor(loggingInterceptor)
				.build();
	}

	public static OkHttpHelper getInstance() {
		if (sInstance == null) {
			synchronized (OkHttpHelper.class) {
				if (sInstance == null) {
					sInstance = new OkHttpHelper();
				}
			}
		}
		return sInstance;
	}

	public OkHttpClient getOkHttpClient() {
		return mOkHttpClient;
	}

	/**
	 * 设置缓存路径
	 *
	 * @param context 上下文
	 */
	public void setCache(Context context) {
		final File baseDir = context.getApplicationContext().getCacheDir();
		if (baseDir != null) {
			final File cacheDir = new File(baseDir, "CopyCache");
			mOkHttpClient.newBuilder().cache((new Cache(cacheDir, HTTP_RESPONSE_DISK_CACHE_MAX_SIZE)));
		}
	}


	/**
	 * 获取缓存路径
	 *
	 * @return cache
	 */
	public Cache getCache(Context context) {
		Cache cache = null;
		String path = FileUtils.createRootPath(context);
		final File baseDir = new File(path);
		if (baseDir != null) {
			final File cacheDir = new File(baseDir, "CopyCache");
			cache = new Cache(cacheDir, HTTP_RESPONSE_DISK_CACHE_MAX_SIZE);
		}
		return cache;
	}

	// 云端响应头拦截器，用来配置缓存策略
	private Interceptor mRewriteCacheControlInterceptor = chain -> {
		Request request = chain.request();
		// Logger.d(NetworkUtils.isAvailable(mContext));
		if (!NetworkUtils.isConnected(mContext)) {
			request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
		}
		Response originalResponse = chain.proceed(request);
		if (NetworkUtils.isConnected(mContext)) {
			//有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
			String cacheControl = request.cacheControl().toString();
			return originalResponse.newBuilder().header("Cache-Control", cacheControl)
					.removeHeader("Pragma").build();
		} else {
			return originalResponse.newBuilder()
					.header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_LONG)
					.removeHeader("Pragma").build();
		}
	};


}
