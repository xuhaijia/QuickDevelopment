package com.xhj.quickdevelopment.model.rx;

/**
 * @author @Qiu
 * @version V1.0
 * @Description:业务异常
 * @date 2017/7/7 14:11
 */
public class ApiException extends RuntimeException {

	private String code;
	private String msg;

	public ApiException(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
