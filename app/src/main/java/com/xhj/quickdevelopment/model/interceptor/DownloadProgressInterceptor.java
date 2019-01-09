package com.xhj.quickdevelopment.model.interceptor;




import com.xhj.quickdevelopment.model.response.DownloadProgressResponseBody;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;


public class DownloadProgressInterceptor implements Interceptor {

	public DownloadProgressInterceptor() {
	}

	@Override
	public Response intercept(Chain chain) throws IOException {
		Response originalResponse = chain.proceed(chain.request());
		return originalResponse.newBuilder().body(new DownloadProgressResponseBody(originalResponse.body())).build();
	}
}
