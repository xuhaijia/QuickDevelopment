package com.xhj.quickdevelopment.entity;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 基类
 */
@SuppressLint("ParcelCreator")
public class BaseEntity implements Parcelable {
	private int status;
	private String statusMsg;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.status);
		dest.writeString(this.statusMsg);
	}

	public BaseEntity() {
	}

	protected BaseEntity(Parcel in) {
		this.status = in.readInt();
		this.statusMsg = in.readString();
	}
}
