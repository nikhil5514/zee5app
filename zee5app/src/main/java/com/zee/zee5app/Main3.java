package com.zee.zee5app;

public class Main3 {
	
	static {
		System.out.println("Hi from sblock");
	}
	
	//no object, so it won't be executed
	{
		System.out.println("hi from iblock");
	}
	
	public static void main(String[] args) {
		System.out.println("Hi from main method");
	}
}
