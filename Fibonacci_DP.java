package com.jiungkris.algorithm;

public class Fibonacci_DP {
	
	private int MAX = 1000;
	
	private int[] fib = new int[MAX];
	
	public int topDown(int n) {
		if(fib[n] != 0 || n == 0) return fib[n];
		else {
			if(n == 1 || n == 2) fib[n] = 1;
			else fib[n] = topDown(n-1) + topDown(n-2);
			return fib[n];
		}
	}
	
	public int bottomUp(int n) {
		fib[1] = fib[2] = 1;
		for(int i = 3; i<=n; i++) {
			fib[i] = fib[i-1] + fib[i-2];
		}
		return fib[n];
	}
	
}
