package com.xhj.quickdevelopment.dragger.module;

import android.app.Activity;

import com.xhj.quickdevelopment.dragger.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;


/**
 * @Description:Activity模型
 * */
@Module
public class ActivityModule {
	private Activity mActivity;

	public ActivityModule(Activity activity) {
		this.mActivity = activity;
	}

	@Provides
	@ActivityScope
	public Activity provideActivity() {
		return mActivity;
	}
}
