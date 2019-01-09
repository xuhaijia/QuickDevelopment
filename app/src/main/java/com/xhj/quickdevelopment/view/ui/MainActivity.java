package com.xhj.quickdevelopment.view.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xhj.quickdevelopment.R;
import com.xhj.quickdevelopment.base.BaseActivity;
import com.xhj.quickdevelopment.gui.BottomNavigationViewEx;
import com.xhj.quickdevelopment.view.impl.IMainView;
import com.xhj.quickdevelopment.view.presenter.MainPresenter;

import butterknife.BindView;
import io.reactivex.Observable;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainView, BottomNavigationView.OnNavigationItemSelectedListener {



	@BindView(R.id.bnve)
	BottomNavigationViewEx bnve;
	private Badge b;

	/**
	 * 获取视图
	 */
	@Override
	public int initCreateMyView() {
		return R.layout.activity_main;
	}

	/**
	 * 初始化dragger2
	 */
	@Override
	public void initInject() {
		getActivityComponent().inject(this);
	}

	/**
	 * 初始化视图
	 */
	@Override
	public void initView() {
		bnve.enableAnimation(false);
		bnve.enableItemShiftingMode(false);
		bnve.enableShiftingMode(false);
		bnve.setIconsMarginTop(BottomNavigationViewEx.dp2px(this, 4));
		bnve.setOnNavigationItemSelectedListener(this);
		addBadgeAt(1 , 100);
		Log.d("MainActivity", "随便改点");
	}

	/**
	 * 初始化数据
	 */
	@Override
	public void initData() {
		mPresenter.reqTest(new RxPermissions(this));
	}

	@Override
	public void callBack() {
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
		b.hide(true);
		Log.d("MainActivity", "menuItem.getItemId():" + menuItem.getItemId());
		// false 拦截 true不拦截

		return true;
	}

	private Badge addBadgeAt(int position, int number) {
		// add badge
		b = new QBadgeView(this)
			   .setBadgeNumber(number)
			   .setGravityOffset(2, 2, true)
			   .bindTarget(bnve.getBottomNavigationItemView(position))
			   .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
				   @Override
				   public void onDragStateChanged(int dragState, Badge badge, View targetView) {
				   }
			   });
		 return b;
	}
}
