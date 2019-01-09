package com.xhj.quickdevelopment.model.impl;


import com.xhj.quickdevelopment.entity.HttpResponse;
import com.xhj.quickdevelopment.entity.VersionEntity;
import com.xhj.quickdevelopment.model.HttpRequestApi;
import com.xhj.quickdevelopment.model.HttpWrapperApi;

import io.reactivex.Flowable;

/**
 * @author @Qiu
 * @version V1.0
 * @Description:Api请求类
 * @date 2017/7/7 13:57
 */
@SuppressWarnings("All")
public class HttpWrapperImpl implements HttpWrapperApi {

	private HttpRequestApi mHttpReqestApi;

	public HttpWrapperImpl(HttpRequestApi httpReqestApi) {
		this.mHttpReqestApi = httpReqestApi;
	}


	/**
	 * 请求版本
	 *
	 * @return
	 */
	@Override
	public Flowable<HttpResponse<VersionEntity>> reqVersion(String version, String type, String deviceId, String channel) {
		return mHttpReqestApi.reqVerion(version, type, deviceId, channel);
	}




}
