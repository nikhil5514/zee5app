package com.zee.zee5app;

import java.util.HashSet;

import com.zee.zee5app.dto.User;

public class HashSetDemo {

	public static void main(String[] args) {
		
		HashSet hashSet = new HashSet();
		
		User u = new User();
		User u2 = new User();
		User u3 = new User();
		
		hashSet.add(u);
		hashSet.add(u2);
		hashSet.add(u3);
		
		System.out.println(hashSet);
		

	}

}
