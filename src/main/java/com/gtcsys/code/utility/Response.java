package com.gtcsys.code.utility;
/*
 * Description :- this is used to custom response with aditional data
 * Name :- Kuldeep 
 * Date :- 28/07/2022 
 * 
 * */
public class Response {

	private boolean success = false;

	private long statuscode = 0L;

	private Object data = null;

	private String message = null;

	public Response() {
	}

	public Response(boolean success) {
	}

	public Response(boolean success, long statusCode) {
		setSuccess(success, statusCode);
	}

	public Response(boolean success, long statusCode, String message) {
		setSuccess(success, statusCode);
		addMessage(message);
	}

	public Response(boolean success, long statusCode, String message, Object value) {
		setSuccess(success, statusCode);
		addMessage(message);
		addData(value);
	}

	public long getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(long statuscode) {
		this.statuscode = statuscode;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success, long statuscode) {
		this.success = success;
		this.statuscode = statuscode;
	}

	public void addData(Object value) {
		this.data = value;
	}

	public Object getData() {
		return this.data;
	}

	public void addMessage(String value) {
		this.message = value;
	}

	public String getMessage() {
		return this.message;
	}

}
