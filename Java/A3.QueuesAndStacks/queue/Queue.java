package queue;

public interface Queue <E> {

	public E dequeue();
	public E first();
	public int size();
	boolean enqueue(E v);
	
}
