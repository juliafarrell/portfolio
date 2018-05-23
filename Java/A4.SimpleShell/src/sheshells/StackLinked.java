package sheshells;

public class StackLinked<E> implements Stack <E>{
	private LinkedList<E> theStack;

	public StackLinked() {
		theStack = new LinkedList<E>();
	}
	
	@Override
	public void push(E v) {
		theStack.insertAtHead(v);
	}

	@Override
	public E pop() {
		if(theStack.size() == 0) return null;
		return theStack.removeHead();
	}

	@Override
	public E top() {
		if(theStack.size() == 0) return null;
		return theStack.first();
	}

	@Override
	public int size() {
		return theStack.size();
	}
	
	
}
