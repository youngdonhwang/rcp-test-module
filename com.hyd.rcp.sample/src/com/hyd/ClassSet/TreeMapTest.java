package com.hyd.ClassSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.hyd.util.ValueComparator;

public class TreeMapTest {
	public static void main(String[] args) {
		TreeMap treemap = new TreeMap();
//		treemap.put("A", Arrays.asList(1,4,6,8));
//		treemap.put("Z", Arrays.asList(100,45,60,8));
//		treemap.put("B", Arrays.asList(3,6,2, 7));
		
		treemap.put("A", 100);
		treemap.put("Z", 200);
		treemap.put("B", 50);
		
		// MAP's value로 loop
		Iterator it = treemap.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Entry) it.next();
			System.out.println("Key sort : " + entry.getKey() + " " + entry.getValue());
		}
		// Map's value sorting
		ArrayList temp = new ArrayList(treemap.entrySet());
		System.out.println("temp : " + temp);
		Collections.sort(temp, new ValueComparator());
		System.out.println("sort temp : " + temp);
		
		// MAP's key로 loop
		Iterator its = treemap.keySet().iterator();
		while(its.hasNext()) {
			String key = (String) its.next();
			System.out.println("value sort : " + key + " " + treemap.get(key));
			
		}
		
		// Sort된 ArrayList로 loop
		Iterator it1 = temp.iterator();
		while(it1.hasNext()) {
			Map.Entry entry = (Entry) it1.next();
			System.out.println("Key sort : " + entry.getKey() + " " + entry.getValue());
		}
		
	}
}
