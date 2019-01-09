package com.xhj.quickdevelopment.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * @author @Qiu
 * @version V1.0
 * @Description:Fragment共同封装
 * @date 2017/7/5 14:15
 */
public interface IBaseFragment {
	/**
	 * 创建视图，统一处理
	 */
	View onCreateMyView(LayoutInflater inflater, ViewGroup container,
						Bundle savedInstanceState);

	/**
	 * 初始化视图
	 */
	void initView(View view);

	/**
	 * 初始化数据
	 */
	void initData();

	/**
	 * 初始化dragger2
	 */
	void  initInject();



}
