package com.xhj.quickdevelopment.model.rx;


import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.orhanobut.logger.Logger;
import com.xhj.quickdevelopment.base.IBaseView;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Locale;

import io.reactivex.subscribers.DisposableSubscriber;


/**
 * @author @Qiu
 * @version V1.0
 * @Description:订阅封装(网络)
 * @date 2017/7/7 14:20
 */
public abstract class ApiSubscriber<T> extends DisposableSubscriber<T> {

	//HTTP的状态码
	private static final int HTTP_BAD_REQUEST = 400;
	private static final int HTTP_FORBIDDEN = 403;
	private static final int HTTP_NOT_FOUND = 404;
	private static final int HTTP_TIMEOUT = 408;
	private static final int HTTP_INTERNAL_SERVER_ERROR = 500;
	private static final int HTTP_REQUEST = 504;
	//出错提示
	private static final String MSG_NETWORK_ERROR = "网络错误";
	private static final String MSG_NETWORK_CONNECTION_ERROR = "网络连接不可用，请检查或稍后重试";
	private static final String MSG_UNKNOWN_ERROR = "好像出错了~";
	private static final String MSG_TIME_OUT = "网络请求超时";
	private static final String MSG_SERVER_ERROR = "服务异常，请稍后重试";

	private IBaseView view;

	public ApiSubscriber() {
	}

	public ApiSubscriber(IBaseView view) {
		this.view = view;
	}

	@Override
	public void onComplete() {
//		Logger.d("onComplete------------------------------------------------------------------>");
//		if (view != null) {
//			view.hideLoadingDialog();
//		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		if (view != null) {
			view.showLoadingDialog();
		}
	}

	@Override
	public void onNext(T t) {
		//返回数据
		if (view != null) {
			view.hideLoadingDialog();
		}
		onAccept(t);
	}

	@Override
	public void onError(Throwable e) {
		Logger.d("onError------------------------------->");
		if (view != null) {
			view.hideLoadingDialog();
		}
		resolveException(e);
	}

	private void resolveException(Throwable e) {
		Throwable throwable = e;
		//获取最根源的异常
//		while (throwable.getCause() != null) {
//			e = throwable;
//			throwable = throwable.getCause();
//		}

		if (e instanceof ApiException) {
			String code = ((ApiException) e).getCode();
			String msg = ((ApiException) e).getMsg();
			if (msg == null || msg.isEmpty()) {
				msg = String.format(Locale.CHINA, "出错了！错误代码：%d", ((ApiException) e).getCode());
			}
			onError(msg);
		} else if (e instanceof HttpException) {
			e.printStackTrace();
			HttpException httpException = (HttpException) e;
			switch (httpException.code()) {
				case HTTP_BAD_REQUEST:
				case HTTP_FORBIDDEN:
				case HTTP_NOT_FOUND:
				case HTTP_INTERNAL_SERVER_ERROR:
					onError(MSG_SERVER_ERROR);
					break;
				case HTTP_TIMEOUT:
					onError(MSG_TIME_OUT);
					break;
				case HTTP_REQUEST:
					onError(MSG_NETWORK_ERROR);
					break;
				default:
					onError(MSG_NETWORK_ERROR);
					break;
			}
		} else if (e instanceof SocketTimeoutException) {
			onError(MSG_TIME_OUT);
		} else if (e instanceof ConnectException) {
			onError(MSG_NETWORK_ERROR);
		} else if (e instanceof UnknownHostException) {
			onError(MSG_NETWORK_CONNECTION_ERROR);
		} else if (e instanceof SocketException) {
			onError(MSG_SERVER_ERROR);
		} else {
			e.printStackTrace();
			onError(MSG_UNKNOWN_ERROR);
		}
	}

	protected abstract void onAccept(T t);

	protected abstract void onError(String msg);

}
