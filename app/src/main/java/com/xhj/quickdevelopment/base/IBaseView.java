package com.xhj.quickdevelopment.base;




/**
 * @author @Qiu
 * @version V1.0
 * @Description:为view和presenter提供接口服务,解耦
 * @date 2017/7/4 15:51
 */
public interface IBaseView {

	void showToast(String message);

	void onRefresh();

	void showSuccess();

	void showEmpty();

	boolean checkNet();

	void showFaild();

	void showNoNet();

	void showLoadingDialog();

	void showLoadingDialogNon();

	void hideLoadingDialog();

	void tokenFail();



}
