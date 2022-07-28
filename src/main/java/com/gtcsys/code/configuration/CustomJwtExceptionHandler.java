package com.gtcsys.code.configuration;

public class CustomJwtExceptionHandler extends Exception {
	private static final long serialVersionUID = -8391354025464897255L;

	public CustomJwtExceptionHandler(String s) {
		super(s);
	}
}