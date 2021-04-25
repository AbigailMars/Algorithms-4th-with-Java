package StackQueues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityStackOfStrings implements Iterable<String>{
      private String[] a;
      private int N;
      
      public FixedCapacityStackOfStrings(int capacity) {
    	  a = new String[capacity];
    	  N = 0;
      }
      
      public boolean isEmpty(){
    	  return N == 0;
      }
      
      public boolean isFull(){
    	  return N == a.length;
      }
      
      public void push(String item) {
    	  a[N++] = item;
      }
      
      public String pop() {
    	  return a[--N];
      }
      
      public String peak() {
    	  return a[N -1];
      }
      
      public Iterator<String> iterator() {
    	  return new ReverseArrayIterator();
      }
      
     
	public class ReverseArrayIterator implements Iterator<String>{
        private int i = N - 1;
        
         @Override
		public boolean hasNext() {
			return i >= 0;
		}

		@Override
		public String next() {
			if(!hasNext()) throw new NoSuchElementException();
			return a[i--];
		}
    	
		public void remove() {
			throw new UnsupportedOperationException();
		}		
      }
	
	
	
	public static void main(String[] args) {
		int max = Integer.parseInt(args[0]);
		FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(max);
		while(!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if(!item.contentEquals("-")) stack.push(item);
			else if(stack.isEmpty())     StdOut.println("Bad Input");
			else                         StdOut.print(stack.pop() + " ");
		}
		StdOut.println();
		
		StdOut.print("Left on stack: ");
		for(String s : stack) {
			StdOut.print(s+ " ");
		}
		StdOut.println();
	}
}
