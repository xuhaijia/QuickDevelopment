package com.xhj.quickdevelopment.dragger.qualifter;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * @author @Qiu
 * @version V1.0
 * @Description:Api注解
 * @date 2017/7/11 下午3:24
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface ApiUrl {

}