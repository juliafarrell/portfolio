package forestry;

public class FireProbability {
	
	public static double ProbOfSpreading(double p) {
		double count = 0;
		Forest forest;
		for (int i = 0; i < 10000; i++) 
		{
			forest = new Forest(20,20,p);
			if (forest.DepthFirstSearch())
			{
				count++;
			}
		}
		return count / 10000;
	}
	
	public static double DensityCheck() {
		double low = 0;
		double high = 1;
		double p = 0.5;
		for (int i = 0; i < 20; i++) {
			p = (high + low) / 2;
			if (ProbOfSpreading(p) > 0.5)
			{
				high = p;
			}
			else {
				low = p;
			}
		}
		return p;
	}
	
	
	
	public static void main(String args[]) {
		
		Forest test;
		long start, stop;
		
		test = new Forest(5,5,0.5);
		DensityCheck();
		
		// comparison of depth and breadth
		double breadth = 0, depth = 0;
		for (int i = 0 ; i < 1000; i++)
		{
			test = new Forest(20, 20, 0.5);
			start = System.currentTimeMillis();
			test.BreadthFirstSearch();
			stop = System.currentTimeMillis();
			breadth += (stop-start);
		}
		System.out.println("Breadth First 20x20 : " + breadth / 1000);
		
		for (int i = 0; i < 1000; i++) 
		{
			test = new Forest(20, 20, 0.5);
			start = System.currentTimeMillis();
			test.DepthFirstSearch();
			stop = System.currentTimeMillis();
			depth += (stop-start);
		}
		System.out.println("Depth First 20x20 : " + depth / 1000);
		
		breadth = 0;
		depth = 0;
		for (int i = 0 ; i < 1000; i++)
		{
			test = new Forest(1000, 1000, 0.5);
			start = System.currentTimeMillis();
			test.BreadthFirstSearch();
			stop = System.currentTimeMillis();
			breadth += (stop-start);
		}
		System.out.println("Breadth First 1000x1000 : " + breadth / 1000);
		
		for (int i = 0; i < 1000; i++) 
		{
			test = new Forest(100, 100, 0.5);
			start = System.currentTimeMillis();
			test.DepthFirstSearch();
			stop = System.currentTimeMillis();
			depth += (stop-start);
		}
		System.out.println("Depth First 1000x1000 : " + depth / 1000);
		
		
		// computing optimal p value:
		System.out.println("Density check gives p= " + DensityCheck() + " as optimal.");
		
		
		/*
		 * Quick comment about the running times
		 * 
		 * Breadth First: The run time depends on how many cells are present in the grid.
		 * Worst case scenario, it visits every cell.
		 * Characterized by O(n)
		 * 
		 * Depth First: Similar explanation to the breadth first, the run time depends on 
		 * how many cells are present. Worst case, it visits every cell.
		 * Characterized by O(n)
		 */
		
		/*
		 * Breadth First 20x20 : 0.065
	 	 * Depth First 20x20 : 0.0
		 * Breadth First 1000x1000 : 14.852
		 * Depth First 1000x1000 : 0.014
		 * Density check gives p= 0.590510368347168 as optimal.
		 * What is returned if you run the program.
		 */
	}

}
