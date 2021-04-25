package elementarySort;

import java.util.Random;

public class Shuffle {
	// This class should not be instantiated.
	private Shuffle() { } 

	//Rearranges the array in ascending order,using the natural order
	public static void shuffle(Comparable[] a) {
		 int n = a.length;
         for(int i = 0; i < n;i++) {
        	 Random  random = new Random();
        	 int r = random.nextInt(i+1);
        	 exch(a,i,r);
         }
	}


	/**********************************************************
	 * Helper Sorting functions 
	 * ********************************************************/

	//exchange a[i] and a[j]
	private static void exch(Object[] a,int i,int j) {
		Object swap  = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	/**********************************************************
	 * Check if array is sorted - useful for debugging
	 * ********************************************************/

	//print array to standard output
	private static void show(Comparable[] a) {
		for(int i = 0; i < a.length;i++) 
			System.out.print(a[i]+" ");
	}


	public static void main(String[] args) {
		String[] a = {"A","B","C","D"};
		shuffle(a);
		show(a);
	}

}
