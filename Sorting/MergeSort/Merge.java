package MergeSort;

//Top-Down merge sort
public class Merge {
	
	//This class should not be instantiated
    private Merge() { }
    
    //stably merge a[lo....mid] with a[mid+1..hi] using aux[lo ..hi]
    private static void merge(Comparable[] a,Comparable aux[], int lo, int mid, int hi) {
       //precondition : a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
       assert isSorted(a,lo,mid);
       assert isSorted(a,mid+1,hi);
       
       //copy to aux[]
       for(int k = lo;k <= hi;k++) {// ** k = lo; k < = hi
    	   aux[k] = a[k];
       }
       
       //merge back to a[]
       int i = lo, j = mid + 1;
       for(int k = lo; k <= hi; k++) {// k < hi ? k <= hi !
    	   if     (i > mid)             a[k] = aux[j++];
    	   else if(j > hi)              a[k] = aux[i++];
    	   else if(less(aux[i],aux[j])) a[k] = aux[i++];
    	   else                         a[k] = aux[j++];
       }
       
       //postcondition: a[lo..hi] is sorted
       assert isSorted(a,lo,hi);
    }
    
    //mergesort a[lo..hi] using auxiliary array aux[[lo..hi]
    private static void sort(Comparable[] a,Comparable[] aux,int lo, int hi) {
    	if(hi <= lo) return;//important !! to stop the recursion
    	int mid = lo + (hi - lo)/2;
    	sort(a,aux,lo,mid);
    	sort(a,aux,mid+1,hi);
    	merge(a,aux,lo,mid,hi);
    } 
    
     public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a,aux,0,a.length - 1);
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
    	for(int i = lo; i < hi+1;i++)
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
    	Merge.sort(a);
    	assert isSorted(a);
    	show(a);
    }
    
}