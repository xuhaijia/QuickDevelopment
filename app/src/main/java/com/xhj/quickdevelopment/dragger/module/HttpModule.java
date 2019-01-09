package com.xhj.quickdevelopment.dragger.module;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xhj.quickdevelopment.MainApplication;
import com.xhj.quickdevelopment.R;
import com.xhj.quickdevelopment.dragger.qualifter.ApiUrl;
import com.xhj.quickdevelopment.model.HttpRequestApi;
import com.xhj.quickdevelopment.model.SystemRequesetApi;
import com.xhj.quickdevelopment.model.impl.HttpWrapperImpl;
import com.xhj.quickdevelopment.model.impl.SystemRequestImpl;
import com.xhj.quickdevelopment.net.OkHttpHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @Description:HttpApi网络模型封装
 */
@Module
public class HttpModule {
	public Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
		return builder
				.baseUrl(url)
				.client(client)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.build();
	}


	@Singleton
	@Provides
	public HttpWrapperImpl provideApiHelper(HttpRequestApi httpReqestApi) {
		return new HttpWrapperImpl(httpReqestApi);
	}

	@Singleton
	@Provides
	public SystemRequesetApi provideSystemRequestApi(SystemRequestImpl systemRequest) {
		return systemRequest;
	}

	@Singleton
	@Provides
	public OkHttpClient provideOkHttpClient() {
		return OkHttpHelper.getInstance().getOkHttpClient();
	}

	@Singleton
	@Provides
	public Retrofit.Builder provideRetrofitBuilder() {
		return new Retrofit.Builder();
	}

	@Singleton
	@Provides
	@ApiUrl
	public Retrofit provideApiRetrofit(Retrofit.Builder builder, OkHttpClient client) {
		return createRetrofit(builder, client, MainApplication.getContext().getResources().getString(R.string.baseUrl));
	}

	@Singleton
	@Provides
	public HttpRequestApi provideHttpRequestApi(@ApiUrl Retrofit retrofit) {
		return retrofit.create(HttpRequestApi.class);
	}
}
