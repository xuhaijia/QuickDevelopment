package com.xhj.quickdevelopment.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xhj.quickdevelopment.MainApplication;
import com.xhj.quickdevelopment.R;
import com.xhj.quickdevelopment.model.interceptor.DownloadProgressInterceptor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Qiu
 * @version V1.0
 * @Description:下载管理
 * @date 2017/11/20 下午3:31
 */
public class DownloadManager {
	public static HttpRequestApi createService() {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		//特别注意下载大文件时千万不要打开下面注释代码 否则会导致OOM
		//.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
		OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
				.readTimeout(5, TimeUnit.SECONDS)
				.connectTimeout(5, TimeUnit.SECONDS)
				.connectTimeout(10, TimeUnit.SECONDS)
				.addInterceptor(new DownloadProgressInterceptor())
				.build();

		Gson gson = new GsonBuilder().setLenient().create();
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(MainApplication.getContext().getResources().getString(R.string.baseUrl))
				.client(okHttpClient)
				.addConverterFactory(GsonConverterFactory.create(gson))
				.callbackExecutor(executorService) //默认CallBack回调在主线程进行,当设置下载大文件时需设置注解@Stream 不加这句话会报android.os.NetworkOnMainThreadException
				.build();
		HttpRequestApi apiService = retrofit.create(HttpRequestApi.class);
		return apiService;
	}
}
