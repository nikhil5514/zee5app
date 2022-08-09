package com.zee.zee5app;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.zee.zee5app.dto.User;

public class SortMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Integer> list = Arrays.asList(1,2,3,4,4,5,5,5,1);
		
		//only for printing
		//list.forEach(e -> System.out.println(e));
		
		
		//list.forEach(System.out::println);
		
		//sorting
		Collections.sort(list);
		//list.forEach(e -> System.out.println(e));
		
		//Comparator Method 2
		Comparator<User> cmp1 = new Comparator<User>() {

			@Override
			public int compare(User o1, User o2) {
				return o1.getUserId().compareTo(o2.getUserId());
			}
		};
		
		
		//Comparator Method 3
		Comparator<User> cmp2 = (e1, e2) -> {
			return e1.getUserId().compareTo(e2.getUserId());
		};
		
		HashMap<Integer, String> hashMap = new HashMap<>();
		
		hashMap.put(1, "a");
		hashMap.put(2, "b");
		hashMap.put(5, "c");
		hashMap.put(3, "d");
		
		//method1
		/*for (Entry<Integer, String> entry : hashMap.entrySet()) {
			System.out.println(entry.getKey()+ " " +entry.getValue());
		}*/
		
		//System.out.println(hashMap.get(5));
			
		//method2
		/*for (Map.Entry<Integer, String> entry : hashMap.entrySet()) {
			Integer key = entry.getKey();
			String val = entry.getValue();
			
		}*/
		
		hashMap.forEach((key,value) -> {System.out.println(key + " "+ value);} );
		
		hashMap.containsKey(1);
		hashMap.containsValue("a");
		
		
		
		
		
	}

	
	
	
	
	
	
}
