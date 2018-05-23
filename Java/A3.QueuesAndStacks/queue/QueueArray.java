package queue;

import java.util.ArrayList;

public class QueueArray<E> implements Queue <E> {

	private ArrayList<E> theQueue;
	private int firstIndex;
	private int size;
	private int capacity;
	
	public QueueArray(int c) {
		capacity = c;
		theQueue = new ArrayList<E>(c);
		for (int i = 0; i < c; i ++) {
			theQueue.add(null);
		}
		size = 0;
		firstIndex = 0;
	}
	
	@Override
	public boolean enqueue(E v) {
		if(size == capacity) return false;
		theQueue.set((firstIndex+size) % capacity, v);
		size++;
		return true;
	}
	@Override
	public E dequeue() {
		if(size == 0) return null;
		E value = theQueue.get(firstIndex);
		theQueue.set(firstIndex,null);
		firstIndex = (firstIndex+1) % capacity;
		size--;
		return value;
	}
	@Override
	public E first() {
		if(size == 0) return null;
		return theQueue.get(firstIndex);
	}
	@Override
	public int size() {
		return size;
	}
	
	public String toString() {
		String r = "";
		for (int n = 0; n < size; n++) {
			r += theQueue.get(firstIndex);
		}
		return r;
	}
	
}
