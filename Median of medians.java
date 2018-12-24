/*
You should use the statndard input/output

in order to receive a score properly.

Do not use file input and output

Please be very careful. 
*/

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class Solution {
	static int Answer;
	
	public static void main(String args[]) throws Exception	{
		/*
		   The method below means that the program will read from input.txt, instead of standard(keyboard) input.
		   To test your program, you may save input data in input.txt file,
		   and call below method to read from the file when using nextInt() method.
		   You may remove the comment symbols(//) in the below statement and use it.
		   But before submission, you must remove the freopen function or rewrite comment symbols(//).
		 */		

		/*
		   Make new scanner from standard input System.in, and read data.
		 */
		Scanner sc = new Scanner(System.in);
		//Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			Answer = 0;

			int N = sc.nextInt();
			int[] arr = new int[N];
			
			for(int i=0; i<N; i++) {
				arr[i] = sc.nextInt();
			}
			
			System.out.println(Arrays.toString(arr));

			System.out.println(sort(arr, 0, arr.length-1, 3));
			
			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}		
	}

	private static int sort(int[] arr, int l, int r, int k) {
		int n = r-l+1;
		int i=0;
		
		int[] median = new int[(n+4)/5];

		for(i=0; i<n/5; i++) {
			median[i] = findMedian(arr, i, 5);
		}
		if(i*5 < n) {
			median[i] = findMedian(arr, i, n%5);
			i++;
		}
		int medOfMed = (i == 1) ? median[i-1] : sort(median, 0, i-1, i/2);
		
		int pos = partition(arr, l, r, medOfMed);
		
		int ePos = pos-l+1;
		
		if(k < ePos) return sort(arr, l, pos-1, k);
		else if(ePos == k) return arr[pos];
		else return sort(arr, pos+1, r, k-ePos);
	}

	private static int partition(int[] arr, int p, int r, int x) {
		int i = p-1;
		int tmp = 0;
		int xIndex = 0;
		
		for(int j=p; j<=r; j++) {
			xIndex = j;
			if(arr[j] == x) break;
		}
		
		tmp = arr[xIndex];
		arr[xIndex] = arr[r];
		arr[r] = tmp;
		
		for(int j=p; j<r; j++) {
			if(arr[j] < x) {
				tmp = arr[++i];
				arr[i] = arr[j];
				arr[j] = tmp;
			}
		}
		
		tmp = arr[i+1];
		arr[i+1] = arr[r];
		arr[r] = tmp;
		
		return i+1;
	}

	private static int findMedian(int[] arr, int m, int k) {
		int[] tmpArr = new int[k];
		
		for(int i=0; i<k; i++) {
			tmpArr[i] = arr[k*m + i];
		}		
		
		for(int i=k-1; i>0; i--) {
			int smallIdx = i;
			
			for(int j=0; j<i; j++) {
				if(tmpArr[smallIdx] > tmpArr[j]) smallIdx = j;
			}
			
			int tmp = tmpArr[smallIdx];
			tmpArr[smallIdx] = tmpArr[i];
			tmpArr[i] = tmp;
		}
		
		return tmpArr[k/2];
	}
	
	
}
