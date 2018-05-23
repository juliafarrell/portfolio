package forestry;

import java.util.Random;

import queue.QueueArray;
import stack.StackLinked;

public class Forest {
	
	int[][] forest;
	int height, width;
	
	/**
	 * Constructor creates 2D grid for the forest simulation and populates
	 * the cells based on a probability p.
	 */
	public Forest(int height, int width, double p) {
		this.height = height;
		this.width = width;
		forest = new int[height][width];
		
		// Random Number for populating cells with probability p
		Random rand = new Random();
		for (int i = 0; i < height; i++) 
		{
			for (int j = 0; j < width; j++)
			{
				/* If the random number generated is between 0 and p, the
				 * cell is populated with vegetation. Otherwise it is empty.
				 */
				if (rand.nextDouble() < p)
				{
					forest[i][j] = 1;
				}
				else {
					forest[i][j] = 0;
				}
			}
		}
	}
	
	/**
	 * @return listed output of forest array
	 */
	public String toString() {
		String me = "";
		for (int i = 0; i < height; i++) 
		{
			for (int j = 0; j < width; j++)
			{
				me = me + forest[i][j] + ", ";
			}
		}
		return me;
	}
	
	/**
	 * @return grid output of forest array
	 */
	public void printGrid() {
		for (int i = 0; i < height; i++) 
		{
			System.out.println();
			for (int j = 0; j < width; j++)
			{
				System.out.print(forest[i][j]);
			}
		}
	}
	
	public int getWidth() {
		return height;
	}
	
	public int getHeight() {
		return width;
	}
	
	/**
	 * @return true if the first in the forest spreads, false otherwise
	 * Uses Depth First technique
	 */
	public boolean DepthFirstSearch() {
		StackLinked cellsToExplore = new StackLinked();
		Cell current;
		
		// Push all vegetation cells onto cellsoToExplore from first row
		for (int i = 0; i < width; i++)
		{
			if (forest[0][i] == 1)
			{
				cellsToExplore.push(new Cell(0,i));
			}
		}
		
		while (cellsToExplore.size() != 0) {
			
			// pop top and make current cell
			current = (Cell) cellsToExplore.pop();
			
			// label current as burning
			forest[current.row][current.col] = 2;
			
			// if current is on bottom, return true
			if (current.row == height - 1) return true;
			
			// if cell above is veg, push
			if (current.getUpRow() != -1)
			{
				if (forest[current.getUpRow()][current.getCol()] == 1) 
				{
					cellsToExplore.push(new Cell(current.getUpRow(), current.getCol()));
				}
			}
			
			// if cell to right is veg, push
			if (current.getRightCol() != -1)
			{
				if (forest[current.getRow()][current.getRightCol()] == 1) {
					cellsToExplore.push(new Cell(current.getRow(), current.getRightCol()));
				}
			}
			
			// if cell to left is veg, push
			if (current.getLeftCol() != -1)
			{
				if (forest[current.getRow()][current.getLeftCol()] == 1) {
					cellsToExplore.push(new Cell(current.getRow(), current.getLeftCol()));
				}
			}
			
			// if cell below is veg, push
			if (current.getLowRow() != -1)
			{
				if (forest[current.getLowRow()][current.getCol()] == 1) {
					cellsToExplore.push(new Cell(current.getLowRow(), current.getCol()));
				}
			}
		}
		return false;
	}
	
	/*
	 * Inner class cell
	 */
	private class Cell {
		int row, col;
		
		public Cell(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		public int getRow() {return row;}
		public int getCol() {return col;}
		
		/**
		 * Finds the column of the cell to the left
		 * @return -1 if off the grid
		 * @return column to the left
		 */
		public int getLeftCol() {
			return col - 1;
		}
		/**
		 * Finds the column of the cell to the right
		 * @return -1 if off the grid
		 * @return column to the right
		 */
		public int getRightCol() {
			if (col + 1 >= width) return -1;
			else return col + 1;
		}
		
		/**
		 * Finds the row of the cell above current
		 * @return -1 if off the grid
		 * @return row of cell above
		 */
		public int getUpRow() {
			return row - 1;
		}
		
		/**
		 * Finds the row of the cell below current
		 * @return -1 if off the grid
		 * @return row of cell below
		 */
		public int getLowRow() {
			if (row + 1 >= height) return -1;
			else return row + 1;
		}
	}

	
	/**
	 * @return true if the first in the forest spreads, false otherwise
	 * Uses Breadth First technique
	 */
	public boolean BreadthFirstSearch() {
		QueueArray cellsToExplore = new QueueArray(width * height);
		Cell current;
		
		// enqueue vegetation cells in top row
		for (int i = 0; i < width; i++)
		{
			if (forest[0][i] == 1)
			{
				cellsToExplore.enqueue(new Cell(0,i));
			}
		}
		
		// while queue is not empty, do the search
		while (cellsToExplore.size() != 0) 
		{	
			// Dequeue first cell and assign to current
			current = (Cell) cellsToExplore.dequeue();
			
			// label current as burning
			forest[current.getRow()][current.getCol()] = 2;
			
			// if current is on the bottom, return true
			if (current.getRow() >= height - 1) return true;
			
			// enqueue all the neighbors if vegetation
				// top
			if (current.getUpRow() != -1)
			{
				if (forest[current.getUpRow()][current.getCol()] == 1)
				{
					cellsToExplore.enqueue(new Cell(current.getUpRow(),current.getCol()));
				}
			}
				// bottom
			if (current.getLowRow() != -1)
			{
				if (forest[current.getLowRow()][current.getCol()] == 1)
				{
					cellsToExplore.enqueue(new Cell(current.getLowRow(),current.getCol()));
				}
			}
				// left
			if (current.getLeftCol() != -1)
			{
				if (forest[current.getRow()][current.getLeftCol()] == 1)
				{
					cellsToExplore.enqueue(new Cell(current.getRow(),current.getLeftCol()));
				}
			}
				// right
			if (current.getRightCol() != -1)
			{
				if (forest[current.getRow()][current.getRightCol()] == 1)
				{
					cellsToExplore.enqueue(new Cell(current.getRow(),current.getRightCol()));
				}
			}
		}
		return false;
	}
	

}
