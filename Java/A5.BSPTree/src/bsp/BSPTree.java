package bsp;

import java.util.ArrayList;
import java.util.Random;

public class BSPTree {
	
	Node root;
	
	public BSPTree(ArrayList<Segment> in)
	{
		root = new Node(in);
		
	}
	
	public void draw(double x , double y)
	{
		StdDraw.clear();
		drawRecurse(x, y, root);
	}
	
	public void drawRecurse(double x, double y, Node node)
	{
		if (node == (null)) return;
		if (node.l == null && node.r == null)
		{
			node.seg.draw();
		}
		if (node.seg.whichSidePoint(x, y) > 0)
		{
			drawRecurse(x, y, node.l);
			node.seg.draw();
			drawRecurse(x, y, node.r);
		}
		else
		{
			drawRecurse(x, y, node.r);
			node.seg.draw();
			drawRecurse(x, y, node.l);
		}
	}
	
	
	/*
	 * Inner Node Class
	 */
	private class Node
	{
		Segment seg;
		Node l,r;
		Random rand = new Random();
		
		public Node(ArrayList<Segment> in)
		{
			seg = in.remove(rand.nextInt(in.size()));
			ArrayList<Segment> inFront = new ArrayList<Segment>();
			ArrayList<Segment> behind = new ArrayList<Segment>();
			
			for (Segment s : in)
			{
				int x = seg.whichSide(s);
				if (x == 0)
				{
					Segment[] split = seg.split(s);
					behind.add(split[0]);
					inFront.add(split[1]);
				}
				
				else if (x == -1)
				{
					behind.add(s);
				}
				
				else if (x == 1) 
				{
					inFront.add(s);
				}
			}
			if (inFront.isEmpty())
			{
				r = null;
			}
			else 
			{
				r = new Node(inFront);
			}
			if (behind.isEmpty())
			{
				l = null;
			}
			else
			{
				l = new Node(behind);
			}
		}
	}
	
}
