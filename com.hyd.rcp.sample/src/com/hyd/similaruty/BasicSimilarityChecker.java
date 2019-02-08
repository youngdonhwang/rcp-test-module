package com.hyd.similaruty;

import org.apache.commons.text.similarity.LevenshteinDistance;

public class BasicSimilarityChecker implements SimilarityChecker {
	
	LevenshteinDistance instance;
	
	public BasicSimilarityChecker() {
		instance = LevenshteinDistance.getDefaultInstance();
	}

	/* (non-Javadoc)
	 * @see com.lgcns.testpilot.common.rule.recommend.SimilarityChecker#check(java.lang.String, java.lang.String)
	 */
	@Override
	public int check(String item1, String item2) {
		double distance = instance.apply(item1, item2);
		double max_length;
		double ratio;
		
		if(item1.length() > item2.length()) max_length = item1.length();
		else max_length = item2.length();
		
		ratio = (max_length - distance) / (double)max_length;
		
//		double ratio = (item1.length() - distance) / (double)item1.length();
		return (int) (ratio * 100);
	}
	
	public static void main(String[] args) {
		BasicSimilarityChecker basiccheck = new BasicSimilarityChecker();
		int result;
		
		result = basiccheck.check("hello", "HELLO");
		System.out.println("1 : " + result);
		result = basiccheck.check("hello-1234", "hello-0000");
		System.out.println("2 : " + result);
		result = basiccheck.check("000-0000-000", "111-1111-11111111111");
		System.out.println("3 : " + result);
		result = basiccheck.check("000-0000-000", "000-000-000");
		System.out.println("4 : " + result);
		
	}
}