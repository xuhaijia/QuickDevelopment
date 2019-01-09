package com.xhj.quickdevelopment.dragger.module;

import android.app.Activity;
import android.support.v4.app.Fragment;


import com.xhj.quickdevelopment.dragger.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;


/**
 * @Description:Fragment模型
 */
@Module
public class FragmentModule {

	private Fragment mFragment;

	public FragmentModule(Fragment fragment) {
		this.mFragment = fragment;
	}

	@Provides
	@FragmentScope
	public Activity provideActivity() {
		return mFragment.getActivity();
	}
}
