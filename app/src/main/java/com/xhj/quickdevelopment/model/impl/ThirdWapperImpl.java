package com.xhj.quickdevelopment.model.impl;



import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xhj.quickdevelopment.model.HttpRequestApi;
import com.xhj.quickdevelopment.net.OkHttpHelper;

import io.reactivex.Flowable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Qiu
 * @version V1.0
 * @Description:一些第三方的请求，放不到dragger里
 * @date 2017/9/15 下午5:47
 */
public class ThirdWapperImpl {

	private Retrofit retrofit;

	public ThirdWapperImpl(String url) {
		retrofit = new Retrofit.Builder()
				.baseUrl(url)
				.client(OkHttpHelper.getInstance().getOkHttpClient())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.build();
	}




}
