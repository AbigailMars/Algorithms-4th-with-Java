package StackQueues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityStack<Item> implements Iterable<Item> {
    private Item[] a;
    private int N;
    
    public FixedCapacityStack(int capacity) {
         a = (Item[]) new Object[capacity];
         N = 0;
    }
    
    public boolean isEmpty() {
    	return N == 0;
    }
    
    public void push(Item item) {
    	a[N++] = item;
    }
    
    public Item pop() {
    	return a[--N];
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
		int max = Integer.parseInt(args[0]);
		FixedCapacityStack<String> stack = new FixedCapacityStack<String>(max);
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
