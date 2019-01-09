package com.xhj.quickdevelopment.entity;

import android.os.Parcel;

/**
 * @author Qiu
 * @version V1.0
 * @Description:
 * @date 2017/9/12 上午9:11
 */

public class VersionEntity extends BaseEntity {

	private String channel;
	private String descrip;
	private String deviceId;
	private String downloadUrl;
	private String forceVersion;//强制更新版本
	private String type;
	private String update;
	private String version;
	private String versionId;

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getForceVersion() {
		return forceVersion;
	}

	public void setForceVersion(String forceVersion) {
		this.forceVersion = forceVersion;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeString(this.channel);
		dest.writeString(this.descrip);
		dest.writeString(this.deviceId);
		dest.writeString(this.downloadUrl);
		dest.writeString(this.forceVersion);
		dest.writeString(this.type);
		dest.writeString(this.update);
		dest.writeString(this.version);
		dest.writeString(this.versionId);
	}

	public VersionEntity() {
	}

	protected VersionEntity(Parcel in) {
		super(in);
		this.channel = in.readString();
		this.descrip = in.readString();
		this.deviceId = in.readString();
		this.downloadUrl = in.readString();
		this.forceVersion = in.readString();
		this.type = in.readString();
		this.update = in.readString();
		this.version = in.readString();
		this.versionId = in.readString();
	}

	public static final Creator<VersionEntity> CREATOR = new Creator<VersionEntity>() {
		@Override
		public VersionEntity createFromParcel(Parcel source) {
			return new VersionEntity(source);
		}

		@Override
		public VersionEntity[] newArray(int size) {
			return new VersionEntity[size];
		}
	};


}
