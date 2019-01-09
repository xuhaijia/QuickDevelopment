package com.xhj.quickdevelopment.dragger.compent;

import android.app.Activity;


import com.xhj.quickdevelopment.dragger.module.FragmentModule;
import com.xhj.quickdevelopment.dragger.scope.FragmentScope;

import dagger.Component;


/**
 * @author @Qiu
 * @version V1.0
 * @Description:FragmentComponent
 * @date 2017/7/11 下午2:24
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

	Activity getActivity();


}
