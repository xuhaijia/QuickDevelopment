package com.xhj.quickdevelopment.base;

import android.content.Context;



import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * @author @Qiu
 * @version V1.0
 * @Description:presenter-层基类
 * @date 2017/7/4 16:25
 */
public abstract class BasePresenter<T extends IBaseView> {

	public T mView;
	public Context context;
	protected CompositeDisposable mCompositeDisposable;


	/**
	 * @param
	 * @Description: 激活view
	 */
	public void attach(T mView, Context context) {
		this.mView = mView;
		this.context = context;
	}

	/**
	 * @param
	 * @Description: 销毁持有的View
	 */
	public void detachView() {
		unSubscribe();
		if (mView != null) {
			mView = null;
		}
	}

	/**
	 * 注册订阅
	 *
	 * @param subscription
	 */
	public void registerSubscribe(Disposable subscription) {
		if (mCompositeDisposable == null) {
			mCompositeDisposable = new CompositeDisposable();
		}
		mCompositeDisposable.add(subscription);
	}

	/**
	 * 解除订阅
	 */
	public void unSubscribe() {
		if (mCompositeDisposable != null) {
			mCompositeDisposable.clear();
			mCompositeDisposable = null;
		}
	}
}
