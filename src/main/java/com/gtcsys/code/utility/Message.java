package com.gtcsys.code.utility;

/*
 * Description :- this is used to define common message
 * Name :- Kuldeep 
 * Date :- 28/07/2022 
 * 
 * */
public class Message {

	public static enum LoanStatus {
		PENDING, APPROVED
	}

	public final static String SAVE = "save successfully";
	public final static String REGISTER = "register successfully";
	public final static String DELETE = " delete successfully";
	public final static String UPDATE = "update successfully";
	public final static String LOGIN = "login successfully";
	public final static String LOGOUT = "logout successfully";
	public final static String SERVERERROR = "internal server error";
	public final static String FETCH = " fetch successfully";
	public final static String NOTEXISTS = "not xists";
	public final static String NOTFOUND = "not found";
	public static final String ALREADYEXIST = "already exist";
	public static final String INVALID_CREDENTIAL = "invalid credential";

}
