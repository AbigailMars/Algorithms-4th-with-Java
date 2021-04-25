package StackQueues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Stack<Item> implements Iterable<Item> {
    private Node<Item> first; // top of Stack
    private int n; 

    //helper linked list class
    private static class Node<Item>{
    	private Item item;
    	private Node<Item> next;
    }
    
    /**
     * Initializes an empty stack
     */
    
    public Stack() {
    	first = null;
    	n = 0;
    }
    
    public boolean isEmpty() {
    	return first == null;
    }
    
    public int size() {
    	return n;
    }
    
    public void push(Item item) {
    	Node<Item> oldfirst = first;
    	first = new Node<Item>();
    	first.item = item;
    	first.next = oldfirst;
    	n++;
    }
    
    public Item pop() {
    	if(isEmpty()) throw new NoSuchElementException("Stack underflow");
		Item item = first.item;  //save item to return
		first = first.next;
		n--;
		return item;
    }
    
    public Item peak() {
       if(isEmpty()) throw new NoSuchElementException("Stack underflow");
       return first.item;   	
    }
    
    public String toString() {
    	StringBuilder s = new StringBuilder();
    	for(Item item : this) {
    		s.append(item);
    		s.append(' ');
    	}
		return s.toString();    	
    }
    
    
	@Override
	public Iterator<Item> iterator() {
		return new LinkedIterator(first);
	}
	
	private class LinkedIterator implements Iterator<Item>{
        private Node<Item> current;
        
        public LinkedIterator(Node<Item> first) {
        	current = first;
        }
        
         @Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if(!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
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
