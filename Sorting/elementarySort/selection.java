package elementarySort;

import java.util.Comparator;

public class selection {

// This class should not be instantiated.
private selection() { } 

//Rearranges the array in ascending order,using the natural order
public static void sort(Comparable[] a) {
	int n = a.length;
	for(int i = 0; i < n ; i++) {
	    // set the minimum number to the last number of ordered part : Integer i 
		int min = i;
		for(int j = i+1; j< n ; j++) {
			if(less(a[j],a[min])) min = j;			
		}
		exch(a,i,min);
		assert isSorted(a,0,i);
	}
	assert isSorted(a);
	
}

//Rearranges the array in ascending order,using a comparator
public static void sort(Object[] a ,Comparator comparator) {
	int n = a.length;
	for(int i = 0; i < n ; i++) {
	    // set the minimum number to the last number of ordered part : Integer i 
		int min = i;
		for(int j = i+1; j< n ; j++) {
			if(less(comparator,a[j],a[min])) min = j;			
		}
		exch(a,i,min);
		assert isSorted(a,comparator,0,i);
	}
	assert isSorted(a,comparator);
	
}

/**********************************************************
 * Helper Sorting functions 
 * ********************************************************/

// is v < W ?
private static boolean less( Comparable v, Comparable w) {
	return v.compareTo(w) < 0;
}

// is v < w ?
private static boolean less( Comparator comparator,Object v,Object w) {
	return comparator.compare(v,w) < 0;
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
	return isSorted(a,0,a.length - 1);
}

//is the array sorted from a[lo] to a[hi] ?
public static boolean isSorted(Comparable[] a,int lo, int hi) {
	for(int i = lo + 1; i < hi; i++)
		if(less(a[i],a[i-1])) return false;
	return true;
}

//is the array[] sorted ?
public static boolean isSorted(Object[] a,Comparator comparator) {
	return isSorted(a,comparator,0,a.length -1);
}

//is the array sorted from a[lo] to a[hi] ?
public static boolean isSorted(Object[] a,Comparator comparator,int lo,int hi) {
	for(int i = lo + 1; i < hi; i++)
		if(less(comparator,a[i],a[i-1])) return false;
	return true;
}



//print array to standard output
private static void show(Comparable[] a) {
	for(int i = 0; i < a.length;i++) 
		System.out.print(a[i]+" ");
}


public static void main(String[] args) {
	String[] a = {"S","O","R","T","E","X","A","M","P","L","E"};
	sort(a);
	assert isSorted(a);
	show(a);
}


}
