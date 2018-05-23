package bsp;

import java.util.ArrayList;
import java.util.Random;

public class Driver {

	public static void main(String[] args) {
		
		Random rand = new Random();
		ArrayList<Segment> segs = new ArrayList<>();
		
		for (int i = 0; i < 30 ; i++)
		{
			Segment newSeg = new Segment(rand.nextDouble(), rand.nextDouble(), 
					rand.nextDouble(), rand.nextDouble());
			segs.add(newSeg);
		}
		
		BSPTree test = new BSPTree(segs);
		
		while (true)
		{
			if (StdDraw.mousePressed())
			{
			test.draw(StdDraw.mouseX(), StdDraw.mouseY());
			}
		}
	}

}
