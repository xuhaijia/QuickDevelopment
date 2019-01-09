package com.xhj.quickdevelopment.dragger.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * @author @Qiu
 * @version V1.0
 * @Description:Fragment 生命周期
 * @date 2017/7/11 下午1:24
 */
@Scope
@Retention(RUNTIME)
public @interface FragmentScope {
}
