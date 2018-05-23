package stack;

public interface Stack<E> {
	
	public E pop();
	public E top();
	public int size();
	void push(E v);

}
