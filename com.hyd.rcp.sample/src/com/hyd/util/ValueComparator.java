package com.hyd.util;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

public class ValueComparator implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		if(o1 instanceof Map.Entry && o2 instanceof Map.Entry) {
			Map.Entry e1 = (Map.Entry) o1;
			Map.Entry e2 = (Map.Entry) o2;
			
			int v1 = ((Integer) e1.getValue()).intValue();
			int v2 = ((Integer) e2.getValue()).intValue();
			
			return v2-v1;
			
		}
		return -1;
	}

}
