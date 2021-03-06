package StackQueues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Queue<Item> implements Iterable<Item>{
    private Node<Item> first; // beginning of queue
    private Node<Item> last;  // end of queue
    private int n;           // number of elements on queue
    
    //helper linked list class
    private static class Node<Item>{
    	private Item item;
    	private Node<Item> next;
    }
    
    /**
     * Initializes an empty stack
     */
    
    public Queue() {
    	first = null;
    	last = null;
    	n = 0;
    }
    
    public boolean isEmpty() {
    	return first == null;
    }
    
    public int size() {
    	return n;
    }
    
    public void enqueue(Item item) {
    	Node<Item> oldfirst = last;
    	last = new Node<Item>();
    	last.item = item;
    	last.next = null;
    	if(isEmpty()) first = last;
    	n++;
    }
    
    public Item dequeue() {
    	if(isEmpty()) throw new NoSuchElementException("Stack underflow");
		Item item = first.item;  //save item to return
		first = first.next;
		n--;
		if(isEmpty()) last = null;  // to avoid loitering
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
			Queue<String> queue = new Queue<String>();
			while(!StdIn.isEmpty()) {
				String item = StdIn.readString();
				if(!item.equals("-")) queue.enqueue(item);
				else if (!queue.isEmpty()) StdOut.print(queue.dequeue() + " ");
			}
			StdOut.println("(" + queue.size() + " left on stack)");
		}

}
