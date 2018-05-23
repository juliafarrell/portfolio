package sheshells;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GeneralTree<E> implements Tree<E>, Serializable {
	Node<E> root;
	int size;
	
	
	public GeneralTree()
	{
		root = null;
		size = 0;
	}
	
	public Position<E> addRoot(E v)
	{
		if (root != null) return root;
		root = new Node<E>(v, null, new ArrayList<Node<E>>());
		return root;
	}
	
	public Position<E> addChild(Position<E> parent, E value) throws IllegalArgumentException
	{
		Node<E> p = validate(parent);
		Node<E> newChild = new Node<E>(value, p, new ArrayList<Node<E>>());
		p.children.add(newChild);
		size++;
		return newChild;
	}
	
	protected Node<E> validate(Position<E> p) throws IllegalArgumentException
	{
		if (!(p instanceof Node)) 
			throw new IllegalArgumentException("Not a valid position type");
		Node<E> node = (Node<E>) p;
		return node;
	}

	@Override
	public Position<E> root() 
	{
		return root;
	}

	@Override
	public Position<E> parent(Position<E> p) throws IllegalArgumentException 
	{
		Node<E> n = validate(p);
		return n.parent;
	}

	@Override
	public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException 
	{
		Node<E> n = validate(p);
		ArrayList<Position<E>> test = new ArrayList<Position<E>>();
		for (int i = 0; i < n.children.size(); i++)
		{
			test.add(n.children.get(i));
		}
		return test;
		//return n.children;
	}

	@Override
	public int numChildren(Position<E> p) throws IllegalArgumentException 
	{
		Node<E> n = validate(p); 
		ArrayList<Node<E>> children = n.children;
		System.out.println(children.size());
		return children.size();
	}

	@Override
	public boolean isInternal(Position<E> p) throws IllegalArgumentException 
	{
		return numChildren(p) > 0;
	}

	@Override
	public boolean isExternal(Position<E> p) throws IllegalArgumentException 
	{
		return numChildren(p) == 0;
	}

	@Override
	public boolean isRoot(Position<E> p) throws IllegalArgumentException 
	{
		return p == root();
	}

	@Override
	public int size() { return size; }

	@Override
	public boolean isEmpty() { return size() == 0; }

	@Override
	public Iterator<E> iterator() 
	{
		Iterator<E> iterator = ((Tree<E>) root).iterator();
		return iterator;
	}
	
	public Iterable<Position<E>> PreOrder()
	{
		List<Position<E>> snapshot = new ArrayList<>();
		if (!isEmpty())
		{
			preOrderSubtree(root(), snapshot);
		}
		return snapshot;
	}
	
	private void preOrderSubtree(Position<E> p, List<Position<E>> snapshot)
	{
		snapshot.add(p);
		for (Position<E> c : children(p))
		{
			preOrderSubtree(c, snapshot);
		}
	}

	@Override
	public Iterable<Position<E>> positions() 
	{
		return PreOrder();
	}
	
	public void PreOrderNodes(ArrayList<Position<E>> al, Node<E> n)
	{
		if (n == null) return;
		al.add(n);
		for (Node<E> node: n.children)
		{
			PreOrderNodes(al, node);
		}
	}
	
	public boolean remove(Position<E> toRemove)
	{
		Node<E> p = validate(toRemove).parent;
		Node<E> rem = validate(toRemove);
		if (rem.children.size() != 0) return false;
		size--;
		return p.remove(rem);
	}
	
	public int getDepth(Position<E> p)
	{
		int depth = 0;
		Node<E> n = validate(p);
		while (n.getParent() != null)
		{
			depth++;
			n = n.parent;
		}
		return depth;
	}
	

	/**
	 * Node Class
	 * @author jfarrell
	 * @param <E>
	 */
	private static class Node<E> implements Position<E>, Serializable
	{
		private E value;
		private Node<E> parent;
		private ArrayList<Node<E>> children;
		
		public Node(E v, Node<E> p, ArrayList<Node<E>> kids) 
		{
			value = v;
			parent = p;
			children = kids;
		}

		public E getValue() { return value; }
		public Node<E> getParent() { return parent; }
		
		public void setValue(E v) { value = v; }
		public void setParent(Node<E> parentNode) { parent = parentNode;}
		
		public boolean remove(Node<E> toRemove)
		{
			for (Node<E> n: children)
			{
				if (n.value.equals(toRemove.value))
				{
					children.remove(toRemove);
					return true;
				}
			}
			return false;
		}
		
		public String toString()
		{
			return value.toString();
		}
	}
}
