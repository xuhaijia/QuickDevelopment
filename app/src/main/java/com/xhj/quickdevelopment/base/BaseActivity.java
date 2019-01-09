package com.xhj.quickdevelopment.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.xhj.quickdevelopment.MainApplication;
import com.xhj.quickdevelopment.R;
import com.xhj.quickdevelopment.dragger.compent.ActivityComponent;
import com.xhj.quickdevelopment.dragger.compent.DaggerActivityComponent;
import com.xhj.quickdevelopment.dragger.module.ActivityModule;
import com.xhj.quickdevelopment.utils.ExitAppUtils;
import com.xhj.quickdevelopment.view.impl.IMainView;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @author @Qiu
 * @version V1.0
 * @Description:父类
 * @date 2017/7/4 9:13
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseActivity, IBaseView {

	@Inject
	protected T mPresenter;
	protected Context mContext;
	protected Unbinder mUnbinder;
//	protected FinalToast mToast;
//	private CustomClipLoading dialog;// 加载

	private boolean isSettingBottom;
	private boolean mBottomSetting;
	private boolean isGlobalLayout;
	private boolean isScrollow;

	private FrameLayout content;
	private int lastDiff;
	private int heightDiff;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getApplicationContext();

		getMyCreateView();
		initInject();
		initPresenter();
		iniBindView();
		initView();
		initData();
		//内存观测
//		RefWatcher refWatcher = MainApplication.getRefWatcher(this);
//		refWatcher.watch(this);

	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		ExitAppUtils.getInstance().delActivity(this);
		if (mUnbinder != null) {
			mUnbinder.unbind();
			mUnbinder = null;
		}
		if (mPresenter != null && this instanceof IBaseView) {
			mPresenter.detachView();
			mPresenter = null;
		}
		super.onDestroy();
	}

	private void getMyCreateView() {
		int layoutRes = initCreateMyView();
		if (layoutRes != -1) {
			setContentView(layoutRes);
		}
	}

	/**
	 * 实例P层
	 */
	private void initPresenter() {
		if (mPresenter != null && this instanceof IBaseView) {
			mPresenter.attach((IBaseView) this, this);
		}
	}

	/**
	 * 绑定
	 */
	protected void iniBindView() {
		mUnbinder = ButterKnife.bind(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	public void showToast(String message) {

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
		return false;
	}

	@Override
	public void showFaild() {

	}

	@Override
	public void showNoNet() {

	}

	@Override
	public void showLoadingDialog() {

	}

	@Override
	public void showLoadingDialogNon() {

	}

	@Override
	public void hideLoadingDialog() {

	}

	@Override
	public void tokenFail() {

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
		startActivity(new Intent(this, cla));
	}

	protected ActivityComponent getActivityComponent() {
		return DaggerActivityComponent.builder()
				.appComponent(MainApplication.getAppComponent())
				.activityModule(getActivityModule())
				.build();
	}

	protected ActivityModule getActivityModule() {
		return new ActivityModule(this);
	}

	@Override
	public void onPointerCaptureChanged(boolean hasCapture) {

	}
}
