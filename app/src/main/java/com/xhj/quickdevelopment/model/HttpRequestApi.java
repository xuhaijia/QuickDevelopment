package com.xhj.quickdevelopment.model;


import com.xhj.quickdevelopment.entity.HttpResponse;
import com.xhj.quickdevelopment.entity.VersionEntity;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;


/**
 * @Description:Http请求Api
 */
public interface HttpRequestApi<T> {

	/**
	 * 请求版本
	 */
	@FormUrlEncoded
	@POST("/app/FsGetAppVersion")
	Flowable<HttpResponse<VersionEntity>> reqVerion(@Field("version") String version, @Field("type") String type, @Field("deviceId") String deviceId, @Field("channel") String channel);

}


