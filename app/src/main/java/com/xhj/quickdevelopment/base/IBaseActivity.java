package com.xhj.quickdevelopment.base;

/**
 * @author @Qiu
 * @version V1.0
 * @Description:Activity共同封装
 * @date 2017/7/5 14:14
 */
public interface IBaseActivity {

	/**
	 * 获取视图
	 */
	int initCreateMyView();

	/**
	 * 初始化dragger2
	 */
	void initInject();

	/**
	 * 初始化视图
	 */
	void initView();

	/**
	 * 初始化数据
	 */
	void initData();


}
