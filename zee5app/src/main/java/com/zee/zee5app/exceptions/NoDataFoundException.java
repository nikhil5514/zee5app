package com.zee.zee5app.exceptions;

public class NoDataFoundException extends Exception {
	

	//toString
	//super call
	public NoDataFoundException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}
	
}
