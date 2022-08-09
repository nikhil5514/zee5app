package com.zee.zee5app.utils;

import java.util.Comparator;

import com.zee.zee5app.dto.User;

public class IdComparator implements Comparator<User> {

	@Override
	public int compare(User o1, User o2) {
		// TODO Auto-generated method stub
		return o1.getUserId().compareTo(o2.getUserId());
	}

}
