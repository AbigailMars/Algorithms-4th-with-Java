package UnionFind;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickFindUF {
    private int[] id; // id[i] = component identifier of i
    private int count;  // number of components
    
    public QuickFindUF(int n) {
    	count = n;
    	id = new int[n];
    	for(int i = 0;i < n; i++){
    		id[i] = i;
    	}
    }
    
    public int count() {
    	return count;
    }
    
    public int find(int p) {
    	validate(p);
    	return id[p];
    }
    
    //validate that p is a validate index
    private void validate(int p) {
    	int n = id.length;
    	if(p < 0 || p >= n) {
    		throw new IllegalArgumentException("index " + p + "is not between 0 and " + (n-1));
    	}
    }
    
    public boolean connected(int p,int q) {
		return find(p) == find(q);    	
    }
    
    public void union(int p, int q) {
    	validate(p);
    	validate(q);
    	int pID = id[p];
    	int qID = id[q];
    	
    	if(pID == qID) return;
    	
    	for(int i = 0;i < id.length;i++) {
    		if (id[i] == pID) id[i] = qID;
    	}
    	count--;
    }
    
    public static void main(String[] args) {
    	int n = StdIn.readInt();
    	QuickFindUF uf = new QuickFindUF(n);
        StdOut.println(uf.count() + " components");
    	while(!StdIn.isEmpty()) {
    		int p = StdIn.readInt();
    		int q = StdIn.readInt();
    		if(uf.find(p) == uf.find(q)) continue;
    		uf.union(p, q);
    		StdOut.println(p + " " + q);    		
    	}
    	StdOut.println(uf.count());   	
    }
    
}
