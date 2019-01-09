package com.xhj.quickdevelopment.dragger.compent;

import android.content.Context;


import com.xhj.quickdevelopment.dragger.module.AppModule;
import com.xhj.quickdevelopment.dragger.module.HttpModule;
import com.xhj.quickdevelopment.model.impl.HttpWrapperImpl;

import javax.inject.Singleton;

import dagger.Component;


/**
 * @author @Qiu
 * @version V1.0
 * @Description:AppComponent
 * @date 2017/7/11 下午2:20
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
	Context getContext();

	HttpWrapperImpl getHttpRequest();

}
