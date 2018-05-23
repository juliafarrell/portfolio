package sheshells;

public class LinkedList<E> {
	
	private Node<E> head;
	private Node<E> tail;
	private int size;
	
	public LinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public void insertAtHead(E v) {
		// Case 1: Empty List
		if (head == null) {
			// Step 1: Create node and set value to v, next to null
			Node<E> newNode = new Node<E>(v,null);
			// Step 2: Set the head to the new node
			head = newNode;
			// Step 3: Set the tail to the new node (only element)
			tail = newNode;
		}
		// Case 2: Not Empty
		else {
			// Step 1: Create node and set value to v, next to the second
			Node<E> newNode = new Node<E>(v, head);
			// Step 2: Set head to new node;
			head = newNode;
		}
		size++;
	}
	
	public void insertAtTail(E v) {
		if (head == null) {
			// Step 1: Create node and set value to v, next to null
			Node<E> newNode = new Node<E>(v,null);
			// Step 2: Set the head to the new node
			head = newNode;
			// Step 3: Set the tail to the new node (only element)
			tail = newNode;
		}
		else {
			Node<E> newNode = new Node<E>(v, null);
			tail.nextNode = newNode;
			tail = newNode;
		}
		
	}
	
	public E removeHead() {
		if(isEmpty()) return null;
		
		E temp = head.value;
		head = head.nextNode;
		size--;
		return temp;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public String toString() {
		String result = "";
		Node<E> temp = head;
		
		if (temp == null) {
			result = "list is empty!";
		}
		
		while (temp != null) {
			result += temp.toString() + ", ";
			temp = temp.nextNode;
		}
		return result;
	}
	

	public void removeLast() {
		Node<E> temp = head;
		if (size() == 0) {
			System.out.println("Nothing to remove, list is empty!");
		}
		
		else if (size() == 1) {
			head = null;
			tail = null;
			size--;
		}
		
		else {
			while (temp.nextNode.nextNode != null) {
				temp = temp.nextNode;
			}
			temp.nextNode = null;
			tail = temp;
			size--;
		}
	}
	
	
	/*
	 * Nested class Node
	 */
	private static class Node<E> {
		E value; 
		Node<E> nextNode;
		
		public Node (E v, Node<E> N) {
			value = v;
			nextNode = N;
		}
		public String toString() {
			return "" + value;
		}
	}
	/*
	 * End Nested class Node
	 */


	public E first() {
		return head.value;
	}
	

}