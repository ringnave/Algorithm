package com.jiungkris.algorithm;

import java.util.Arrays;

public class EratosthenesSieve {
	
	/**
	 * Eratosthenes' Sieve (Prime number algorithm)
	 * @param num You can find the prime numbers until the num.
	 * @return Array of the prime numbers that you found.
	 */
	public int[] execute(int num) {
		
		int i, count = 0;
		int sqrt = (int) Math.sqrt(num);
		int[] tmp;
		int[] arr = new int[num];
		
		Arrays.fill(arr, -1);
		
		for(i=1; i<num; i++) {
			arr[i] = i+1;
		}
		
		for(i=2; i<=sqrt; i++) {
			if(arr[i-1] != -1) {
				for(int j=i-1; j<num; j+=i) {
					if(j == i-1) continue;
					arr[j] = -1;
				}
			}
		}
		
		for(i=0; i<num; i++) {
			if(arr[i] != -1) count++;
		}
		
		tmp = new int[count];
		count = 0;
		
		for(i=0; i<num; i++) {
			if(arr[i] != -1) {
				tmp[count++] = arr[i];
			}
		}
		
		return tmp;
	}
}
