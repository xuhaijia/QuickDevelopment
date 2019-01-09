package com.xhj.quickdevelopment.dragger.compent;

import android.app.Activity;

 import com.xhj.quickdevelopment.view.ui.MainActivity;
import com.xhj.quickdevelopment.dragger.module.ActivityModule;
import com.xhj.quickdevelopment.dragger.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
	Activity getActivity();

	void inject(MainActivity mainActivity);


}
