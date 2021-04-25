package StackQueues;

import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ResizingArrayQueue<Item> implements Iterable<Item> {
     //initial capacity of underlying resizing array
	 private static final int INIT_CAPACITY = 8;
	 
	 private Item[] q;
	 private int n; //number of elements on queue
	 private int first;  //index of first element of queue
	 private  int last;  //index of next available slot
	 
	 public ResizingArrayQueue() {
		 q =(Item[]) new Object[INIT_CAPACITY];
		 n = 0;
		 first = 0;
		 last = 0;
	 }
	 
	 public boolean isEmpty() {
		 return n == 0;
	 }
	 
	 public int size() {
		 return n;
	 }
	 
	 private void resize(int capacity) {
		 assert capacity >= n;
		 Item[] copy = (Item[]) new Object[capacity];
		 for(int i = 0; i < n; i++) {
			 copy[i] = q[(first + i) % q.length]; ////?????? why % q.length 
		 }
		 q = copy;
		 first = 0;
		 last = n;
	 }
	 
	 public void enqueue(Item item) {
		 if(n == q.length) resize(2*q.length);
		 q[last++] = item; //add item
		 if(last == q.length) last = 0; //wrap - around ??
		 n++;		 
	 }
	 
	 public Item dequeue() {
		 if(isEmpty()) throw new NoSuchElementException("Queue underflow");
		 Item item = q[first];
		 q[first] = null; // to avoid loitering
		 n--;
		 first++;
		 if(first ==  q.length) first = 0; // wrap - around
		 if(n > 0 && n == q.length /4) resize(q.length/2);
		 return item;
	 }
	 
	 public Item peek() {
		 if(isEmpty()) throw new NoSuchElementException("Queue underflow");
		 return q[first];
	 }
	 
	 @Override
	public Iterator<Item> iterator() {
	     return new ArrayIterator();
	}
	 
	 //an iterator,does not implement remove()since it is optional
	 private class ArrayIterator implements Iterator<Item>{
		 private int i = 0;
		 public boolean hasNext() {
			 return i < n ;
		 }
		 
		 public void remove() {
			 throw new UnsupportedOperationException();
		 }
		 
		 public Item next() {
			 if(!hasNext()) throw new NoSuchElementException();
			 Item item = q[(first + i) % q.length];
			 i++;
			 return item;
		 }
	 }  
	 
	 
	 public static void main(String[] args) {
				ResizingArrayQueue<String> queue = new ResizingArrayQueue<String>();
				while(!StdIn.isEmpty()) {
					String item = StdIn.readString();
					if(!item.equals("-")) queue.enqueue(item);
					else if (!queue.isEmpty()) StdOut.print(queue.dequeue() + " ");
				}
				StdOut.println("(" + queue.size() + " left on stack)");
			}

	
		 
 }
