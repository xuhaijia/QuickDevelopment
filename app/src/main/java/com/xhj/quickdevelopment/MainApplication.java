package com.xhj.quickdevelopment;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.xhj.quickdevelopment.dragger.compent.AppComponent;
import com.xhj.quickdevelopment.dragger.compent.DaggerAppComponent;
import com.xhj.quickdevelopment.dragger.module.AppModule;
import com.xhj.quickdevelopment.dragger.module.HttpModule;

/**
 * Author: 7
 * Create: 2018/12/12 16:23
 */
public class MainApplication extends MultiDexApplication {

	protected static Context sCtx;
	private static AppComponent mAppComponent;

	public static AppComponent getAppComponent() {
		return mAppComponent;
	}

	public static Context getContext() {
		return sCtx;
	}


	@Override
	public void onCreate() {
		super.onCreate();
		sCtx = getApplicationContext();

		//dagger
		initComponent();
	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}

	/**
	 * dragger初始化
	 */
	private void initComponent() {
		mAppComponent = DaggerAppComponent.builder()
				.httpModule(new HttpModule())
				.appModule(new AppModule(sCtx))
				.build();
	}
}
