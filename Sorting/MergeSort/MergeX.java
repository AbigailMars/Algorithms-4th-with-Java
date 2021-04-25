package MergeSort;

import elementarySort.insertion;

// 3 ways improvements
public class MergeX {
	final static int CUTOFF = 10;
	//This class should not be instantiated
    private MergeX() { }
    
    //stably merge a[lo....mid] with a[mid+1..hi] using aux[lo ..hi]
    private static void merge(Comparable[] a,Comparable [] aux, int lo, int mid, int hi) {
       //precondition : a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
       assert isSorted(a,lo,mid);
       assert isSorted(a,mid+1,hi);
       
       //merge back to a[]
       int i = lo, j = mid + 1;
       for(int k = lo; k <= hi; k++) {// k < hi ? k <= hi !
    	   if     (i > mid)             aux[k] = a[j++];
    	   else if(j > hi)              aux[k] = a[i++];
    	   else if(less(a[i],a[j]))     aux[k] = a[i++];
    	   else                         aux[k] = a[j++];
       }
       
       //postcondition: a[lo..hi] is sorted
       assert isSorted(a,lo,hi);
    }
    
    //mergesort a[lo..hi] using auxiliary array aux[[lo..hi]
    private static void sort(Comparable[] a,Comparable[] aux,int lo, int hi) {
    	
    	//1. small number of sortings use insertion sort
    	if(hi< lo + CUTOFF) {
    		insertion.sort(a, lo, hi);
    		return;
    	}
    	
    	if(hi <= lo) return;//important !! to stop the recursion
    	int mid = lo + (hi - lo)/2;
    	sort(a,aux,lo,mid);
    	sort(a,aux,mid+1,hi);
    	//2.if a[mid] < a[mid+1],left-right array is already in order
    	if(!less(a[mid+1],a[mid])) {
    		System.arraycopy(a,lo,aux,lo,hi - lo + 1);
    		return;
    	}
    	merge(a,aux,lo,mid,hi);
    } 
    
     public static void sort(Comparable[] a) {
    	//3.reduce copy time
        Comparable[] aux = a.clone();
        sort(aux,a,0,a.length - 1);
        assert isSorted(a);
    }
    /**********************************************************
     * Helper Sorting functions 
     * ********************************************************/

    // is v < W ?
    private static boolean less( Comparable v, Comparable w) {
    	return v.compareTo(w) < 0;
    }

    /**********************************************************
     * Check if array is sorted - useful for debugging
     * ********************************************************/
    
    //is the array[] sorted ?
    public static boolean isSorted(Comparable[] a) {
    	return isSorted(a,0,a.length - 1);
    }

    //is the array[lo..hi] sorted ?
    public static boolean isSorted(Comparable[] a,int lo,int hi) {
    	for(int i = lo+1; i < hi+1;i++)//i = lo + 1
    	    if(less(a[i], a[i-1])) return false;
    	return true;
    }

    
    
   //print array to standard output
    private static void show(Comparable[] a) {
    	for(int i = 0; i < a.length;i++) 
    		System.out.print(a[i]+" ");
    }

    public static void main(String[] args) {
    	String[] a = {"M","E","R","G","E","S","O","R","T","E","X","A","M","P","L","E"};
    	MergeX.sort(a);
    	assert isSorted(a);
    	show(a);
    }
}
