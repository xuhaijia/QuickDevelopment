package com.xhj.quickdevelopment.view.presenter;

import android.Manifest;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xhj.quickdevelopment.base.BasePresenter;
import com.xhj.quickdevelopment.model.impl.HttpWrapperImpl;
import com.xhj.quickdevelopment.view.impl.IMainView;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Author: 7
 * Create: 2018/12/13 8:46
 */
public class MainPresenter extends BasePresenter<IMainView> {

	private HttpWrapperImpl mApiHelper;

	@Inject
	public MainPresenter(HttpWrapperImpl apiHelper) {
		this.mApiHelper = apiHelper;
	}

	public void reqTest(RxPermissions rxPermissions) {
		registerSubscribe(rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE ,Manifest.permission.WRITE_EXTERNAL_STORAGE)
				.subscribe(new Consumer<Boolean>() {
					@Override
					public void accept(Boolean granted) {
						if (granted) {
							mView.callBack();
						} else {
						}
					}
				})
		);

	}
}
