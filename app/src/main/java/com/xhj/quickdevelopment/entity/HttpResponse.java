package com.xhj.quickdevelopment.entity;


/**
 * @Description:响应体
 */
public class HttpResponse<T> {

	/**
	 * 成功标志
	 */
	private String code = "9999";
	/**
	 * 异常信息
	 */
	private String message;
	private T result;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
}
