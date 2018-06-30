package com.jiungkris.algorithm;

public class Sort {
	
		/**time O(n^2), space O(1)
		 * @param A array to sort
		 * @param n length of array
		 * @return sorted array
		 */
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
		
		/**time O(n^2), space O(1)
		 * @param A array to sort
		 * @param n length of array
		 * @return sorted array
		 */
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
		
		/**time O(n^2), space O(1)
		 * @param A array to sort
		 * @param n length of array
		 * @return sorted array
		 */
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
		
		/**time O(nlogn), space O(n)
		 * @param A array to sort
		 * @param p index start number
		 * @param r index end number
		 */
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
		
		/**time O(nlogn), space O(1)
		 * @param A array to sort
		 * @param n length of array
		 */
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
		
		/**time O(nlogn), space O(1)
		 * @param A array to sort
		 * @param p index start number
		 * @param r index end number
		 */
		public void quickSort(int[] A, int p, int r) {
			if(p < r) {
				int q = partition(A, p, r);
				quickSort(A, p, q-1);
				quickSort(A, q+1, r);
			}
		}

		private int partition(int[] A, int p, int r) {
			int x = A[r];
			int i = p - 1;
			
			for(int j=p; j<r; j++) {
				if(A[j] <= x) {
					int tmp = A[++i];
					A[i] = A[j];
					A[j] = tmp;
				}
			}
			int tmp = A[i+1];
			A[i+1] = A[r];
			A[r] = tmp;
			
			return i+1;
		}
		
		/**time O(n), space O(n+k).
		 * A is the input array, B is the result array and C is temporary array.
		 * Counting Sort is sorting algorithm for natural number.
		 * @param A the input array
		 * @param n length of array
		 */
		public void countingSort(int[] A, int n) {
			int k = getMax(A, A.length);
			int[] B = new int[n];
			int[] C = new int[k+1];
			
			for(int i=0; i<n; i++) {
				C[A[i]]++;
			}
			
			for(int i=2; i<k+1; i++) {
				C[i] = C[i] + C[i-1];
			}
			
			for(int i=n-1; i>=0; i--) {
				B[C[A[i]]-1] = A[i];
				C[A[i]]--; // It makes adding duplicated number possible. It means pivot if the number is same.
				// C array means last index of the input number.
			}

			for(int i=0; i<A.length; i++) {
				A[i] = B[i];
			}
			
		}
		
		/**time O(n), space O(n)
		 * @param A array to sort
		 * @param n length of array
		 * */
		public void radixSort(int[] A, int n) {
	        int max = getMax(A, n);
	        for (int digit = 1; max/digit > 0; digit *= 10)
	            countingSortByDigit(A, n, digit);
		}
		
		private int getMax(int A[], int n) {
	        int max = A[0];
	        for (int i = 1; i < n; i++) {
	        	if (A[i] > max) {
	            	max = A[i];
	            }
	        }
	               
	        return max;
		}
		
		private void countingSortByDigit(int A[], int n, int digit) {
	    	int k = getMax(A, A.length);
	        int[] B = new int[n];
	        int[] C = new int[k+1];
	 
	        for (int i = 0; i < n; i++)
	            C[ (A[i]/digit)%10 ]++;
	 
	        for (int i = 1; i < 10; i++)
	        	C[i] = C[i] + C[i-1];

	        for (int i = n - 1; i >= 0; i--)
	        {
	            B[C[ (A[i]/digit)%10 ] - 1] = A[i];
	            C[ (A[i]/digit)%10 ]--;
	        }

	        for (int i = 0; i < n; i++)
	            A[i] = B[i];
		}
}
