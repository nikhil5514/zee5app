package com.zee.zee5app.exceptions;

import lombok.ToString;

public class InvalidIdException extends Exception {

	//toString
	//super call
	public InvalidIdException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}
	
	
}
