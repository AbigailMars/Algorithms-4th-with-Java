package elementarySort;

import java.util.Comparator;

public class shellSort {
 
// This class should not be instantiated.
private shellSort() { } 

//Rearranges the array in ascending order,using the natural order
public static void sort(Comparable[] a) {
	int n = a.length;
	System.out.println("N: " + n);
	int h = 1;
	while(h < n/3) h = 3*h + 1;//1,4,13,40,121,364,1093...
	
	//output h for understanding
	System.out.println("H: " + h);
	
	while(h >= 1) {
		//h-sort the array		
		System.out.println("in while H: " + h);
		for (int i = h; i < n ; i++) {
			show(a);
			System.out.println(" ");
			for(int j = i; j >= h && less(a[j],a[j-h]); j-= h) {
				exch(a,j,j-h);
			}
		}
		assert isHSorted(a,h);
		h/=3;
	}
   assert isSorted(a);
}


/**********************************************************
 * Helper Sorting functions 
 * ********************************************************/

// is v < W ?
private static boolean less( Comparable v, Comparable w) {
	return v.compareTo(w) < 0;
}


//exchange a[i] and a[j]
private static void exch(Object[] a,int i,int j) {
	Object swap  = a[i];
	a[i] = a[j];
	a[j] = swap;
}

/**********************************************************
 * Check if array is sorted - useful for debugging
 * ********************************************************/

//is the array[] sorted ?
public static boolean isSorted(Comparable[] a) {
	for(int i = 1; i < a.length;i++)
	    if(less(a[i], a[i-1])) return false;
	return true;
}


//is the array[] hsorted ?
public static boolean isHSorted(Comparable[] a,int h) {
	for(int i = h; i < a.length;i++)
	    if(less(a[i], a[i-h])) return false;
	return true;
}




//print array to standard output
private static void show(Comparable[] a) {
	for(int i = 0; i < a.length;i++) 
		System.out.print(a[i]+" ");
}


public static void main(String[] args) {
	String[] a = {"S","H","E","L","L","S","O","R","T","E","X","A","M","P","L","E"};
	sort(a);
	assert isSorted(a);
	show(a);
}


}
