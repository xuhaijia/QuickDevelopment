package com.xhj.quickdevelopment.model;

import com.xhj.quickdevelopment.entity.HttpResponse;
import com.xhj.quickdevelopment.entity.VersionEntity;

import io.reactivex.Flowable;

/**
 * @author Qiu
 * @version V1.0
 * @Description:请求Api封装
 * @date 2017/7/21 上午9:34
 */
public interface HttpWrapperApi {
	/**
	 * 请求版本
	 */
	Flowable<HttpResponse<VersionEntity>> reqVersion(String version, String type, String deviceId, String channel);
}
