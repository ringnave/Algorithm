package com.jiungkris.algorithm;

public class Sort {
	
	//time O(N^2), space O(1)
		public int[] bubbleSort(int[] A, int n) {
			for(int last = n-1; last >= 1; last--) {
				for(int i=0; i<last; i++) {
					if(A[i] > A[i+1]) {
						int tmp = A[i+1];
						A[i+1] = A[i];
						A[i] = tmp;
					}
				}
			}
			
			return A;
		}
		
		//time O(n^2), space O(1)
		public int[] selectionSort(int[] A, int n) {
			for(int last = n-1; last>=1; last--) {
				int max = 0;
				int maxIndex = 0;
				for(int i=0; i<=last; i++) {
					if(max < A[i]) {
						max = A[i];
						maxIndex = i;
					}
				}
				int tmp = A[maxIndex];
				A[maxIndex] = A[last];
				A[last] = tmp;
			}
			
			return A;
		}
		
		//time O(n^2), space O(1)
		public int[] insertionSort(int[] A, int n) {
			for(int i=1; i<n; i++) {
				int loc = i-1;
				int newItem = A[i];
				while(loc >= 0 && newItem < A[loc]) {
					A[loc+1] = A[loc];
					loc--;
				}
				A[loc+1] = newItem;
			}
			return A;
		}
		
		//time O(nlogn), space O(n)
		//p and r is index numbers.
		public void mergeSort(int[] A, int p, int r) {
			if(p < r) {
				int q = (p+r)/2;
				mergeSort(A, p, q);
				mergeSort(A, q+1, r);
				merge(A, p, q, r);
			}
		}

		private void merge(int[] A, int p, int q, int r) {
			int i = p;
			int j = q+1;
			int t = 0;
			int[] tmp = new int[A.length];
			
			while(i <= q && j <= r) {
				if(A[i] <= A[j]) tmp[t++] = A[i++];
				else tmp[t++] = A[j++];
			}
			while(i <= q) tmp[t++] = A[i++];
			while(j <= r) tmp[t++] = A[j++];
			i = p;
			t = 0;
			while(i <= r) {
				A[i++] = tmp[t++];
			}
		}
		
		//time O(nlogn), space O(1)
		public void heapSort(int[] A, int n) {
			int[] tmpArr = new int[A.length];
			int tmpIndex = 0;
			
			buildHeap(A, n);
			for(int i=n; i>=0; i--) {
				tmpArr[tmpIndex++] = A[0];
				int tmp = A[0];
				A[0] = A[i];
				A[i] = tmp;
				heapify(A, 0, i-1);
			}
			
			for(int i=0; i<A.length; i++) {
				A[i] = tmpArr[i];
			}
		}

		private void buildHeap(int[] A, int n) {
			for(int i=n/2; i>=0; i--) {
				heapify(A, i, n);
			}
		}

		private void heapify(int[] A, int k, int n) {
			int left = 2*k;
			int right = 2*k + 1;
			int smaller = 0;
			
			if(right <= n) {
				if(A[left] < A[right]) smaller = left;
				else smaller = right;
			}
			else if(left <= n) smaller = left;
			else return;
			
			if(A[smaller] < A[k]) {
				int tmp = A[k];
				A[k] = A[smaller];
				A[smaller] = tmp;
				heapify(A, smaller, n);
			}
		}
}
