package com.xhj.quickdevelopment.net;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;


/**
 * @author @Qiu
 * @version V1.0
 * @Description:Token验证拦截
 * @date 2017/7/4 9:25
 */
public class TokenInterceptor implements Interceptor {

	private static final String TAG = TokenInterceptor.class.getSimpleName();

	private static final Charset UTF8 = Charset.forName("UTF-8");

	@Override
	public Response intercept(Chain chain) throws IOException {

		Request request = chain.request();
		Response originalResponse = chain.proceed(request);

		/**
		 * 曲线取到请求完成的数据(待考虑更好的方法)
		 */
		ResponseBody responseBody = originalResponse.body();
		BufferedSource source = responseBody.source();
		source.request(Long.MAX_VALUE); // Buffer the entire body.
		Buffer buffer = source.buffer();
		Charset charset = UTF8;
		MediaType contentType = responseBody.contentType();
		if (contentType != null) {
			charset = contentType.charset(UTF8);
		}

		String bodyString = buffer.clone().readString(charset);
//		Log.e(TAG, "response---------->" + bodyString);
		/***************************************************************************/
		//根据和服务端的约定判断token过期
		if (bodyString.contains("用户名密码过期")) {
			// token认证失败
		}
		return originalResponse;
	}
}