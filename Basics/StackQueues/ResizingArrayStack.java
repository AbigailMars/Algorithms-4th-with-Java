package StackQueues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ResizingArrayStack<Item> implements Iterable<Item> {
    private static final int  INIT_CAPACITY = 8;
    
    private Item[] a;
    private int N; // number of elements on stack
    
    public ResizingArrayStack() {
    	a = (Item[]) new Object[INIT_CAPACITY];
    	N = 0;
    }
    
    public boolean isEmpty() {
    	return N == 0;
    }
    
    public int size() {
    	return N;
    }
    
    private void resize(int capacity) {
    	assert capacity >= N;
    	
    	Item[] copy =(Item[]) new Object[capacity];
    	for(int i = 0;i < N;i++) {
    		copy[i] = a[i];
    	}
    	a = copy;
    
        //alternative implementation
    	//a = java.util.Arrays.copyOf(a,capacity)
    }
    
    public void push(Item item) {
    	if( N == a.length) resize(2*a.length);
    	a[N++] = item;
    }
    
    public Item pop() {
    	 if(isEmpty()) throw new NoSuchElementException("Stack underflow");
    	 Item item = a[N-1];
    	 a[N-1] = null;   // to avoid loitering 
    	 N--;
    	 //shrink size of array if necessary
    	 if(N > 0 && N == a.length/4) resize(a.length/2);
		return item;
    }
    
    public Item peek() {
    	if(isEmpty()) throw new NoSuchElementException("Stack underflow");
    	return a[N-1];
    }
        
	@Override
	public Iterator<Item> iterator() {
		return new ReverseArrayIterator();
	}
	
	public class ReverseArrayIterator implements Iterator<Item>{
        private int i = N - 1;
        
         @Override
		public boolean hasNext() {
			return i >= 0;
		}

		@Override
		public Item next() {
			if(!hasNext()) throw new NoSuchElementException();
			return a[i--];
		}
    	
		public void remove() {
			throw new UnsupportedOperationException();
		}		
      }
	
	
	public static void main(String[] args) {
		ResizingArrayStack<String> stack = new ResizingArrayStack<String>();
		while(!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if(!item.equals("-")) stack.push(item);
			else if (!stack.isEmpty()) StdOut.print(stack.pop() + " ");
		}
		StdOut.println("(" + stack.size() + " left on stack)");
	}
   
}
