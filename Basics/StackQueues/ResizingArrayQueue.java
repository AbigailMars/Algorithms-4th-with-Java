package StackQueues;

public class ResizingArrayQueue<Item> implements Iterable<Item> {
     //initial capacity of underlying resizing array
	 private static final int INIT_CAPACITY = 8;
	 
	 private Item[] q;
	 private int n;
	 private int first;  //index of first element of queue
	 private  int last;  //index of next available slot
}
