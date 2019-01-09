package com.xhj.quickdevelopment.dragger.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;


/**
 * @Description:App模型
 * */
@Module
public class AppModule {
	private Context mContext;

	public AppModule(Context context) {
		this.mContext = context;
	}
	@Provides
	public Context provideContext() {
		return mContext;
	}

}
