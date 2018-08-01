package com.jiungkris.algorithm;

public class EuclideanAlgorithm {
	
	public int getGCD(int a, int b) {
		int r = 0;
		
		if(a < b) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		
		while(true) {
			r = a%b;
			
			if(r == 0) break;
			
			a = b;
			b = r;
		}
		
		return b;
	}
}
