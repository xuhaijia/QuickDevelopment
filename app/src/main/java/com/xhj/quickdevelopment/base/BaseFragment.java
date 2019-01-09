package com.xhj.quickdevelopment.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.xhj.quickdevelopment.MainApplication;
import com.xhj.quickdevelopment.dragger.compent.DaggerFragmentComponent;
import com.xhj.quickdevelopment.dragger.compent.FragmentComponent;
import com.xhj.quickdevelopment.dragger.module.FragmentModule;
import com.xhj.quickdevelopment.utils.NetworkUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @author @Qiu
 * @version V1.0
 * @Description:BaseFragment
 * @date 2017/7/5 14:16
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements OnClickListener,
		OnRefreshListener, IBaseFragment ,IBaseView {

	@Inject
	protected T mPresenter;// P层基类
	protected Context mContext;
//	protected FinalToast mToast;
//	protected LoadingView fl_loading;// 加载
	//	private RefWatcher refWatcher;
//	private CustomClipLoading dialog;// 加载
	private Unbinder unbinder;




	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view;
		mContext = MainApplication.getContext();
		view = onCreateMyView(inflater, container, savedInstanceState);
		initInject();
		initPresenter();
		iniBindView(view);
		initView(view);
		initData();
		return view;
	}

	@Override
	public void onDestroy() {
		if (mPresenter != null && this instanceof IBaseView) {
			mPresenter.detachView();
			mPresenter = null;
		}
		unbinder.unbind();
		super.onDestroy();
	}


	/**
	 * 绑定view
	 */
	protected void iniBindView(View view) {
		unbinder = ButterKnife.bind(this, view);
	}


	/**
	 * 实例P层
	 */
	private void initPresenter() {
		if (mPresenter != null && this instanceof IBaseView) {
			mPresenter.attach((IBaseView) this, getActivity());
		}
	}

	/**
	 * 自定义监听
	 */
	public void onMyClick(View view) {
	}


	@Override
	public void onClick(View view) {
		try {
			onMyClick(view);
		} catch (Exception e) {
			// 异常处理|网络
			e.printStackTrace();
		}
	}

	/**
	 * 跳转Intent
	 */
	protected void startAnimActivity(Intent intent) {
		startActivity(intent);
	}

	/**
	 * 跳转Intent
	 */
	protected void startAnimActivity(Class<?> cla) {
		startActivity(new Intent(getActivity(), cla));
	}

	@Override
	public void onRefresh() {
	}


	@Override
	public void showSuccess() {

	}

	@Override
	public void showEmpty() {



	}

	@Override
	public boolean checkNet() {
		return NetworkUtils.isConnected(mContext.getApplicationContext());
	}

	@Override
	public void showFaild() {

	}

	@Override
	public void showNoNet() {

	}






	protected FragmentComponent getFragmentComponent() {
		return DaggerFragmentComponent.builder()
				.appComponent(MainApplication.getAppComponent())
				.fragmentModule(getFragmentModule())
				.build();
	}

	protected FragmentModule getFragmentModule() {
		return new FragmentModule(this);
	}


	/**
	 * 显示Toast
	 */
	public void showToast(String message) {

	}

	/**
	 * 显示进度条
	 */
	public void showLoadingDialog() {

	}

	/**
	 * 显示进度条
	 */
	public void showLoadingDialogNon() {

	}

	/**
	 * token过期
	 */
	public void tokenFail() {

	}

	/**
	 * 隐藏
	 */
	public void hideLoadingDialog() {

	}












}
