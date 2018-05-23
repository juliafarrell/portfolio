package bsp;

public class Segment {

	private double x1, y1, x2, y2;
	public Segment(double x1, double y1, double x2, double y2){
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	public String toString()
	{
		return x1 + ", " + y1 + ", " + x2 + ", " + y2;
	}
	
	//compute which side of the segment the point is on, the + side or the - side
	//the + side is "in front of" the segment, the "-" side is behind it
	public int whichSidePoint(double x, double y){
		double nx = y1 - y2; //-y
		double ny = x2 - x1; //+x
		double lx = x - x1;
		double ly = y - y1;
		
		return (int) Math.signum(nx*lx + ny*ly);
	}
	
	//which side of the segment is the segment "other"
	//1 -- both points are "in front of" this
	//-1 -- both points are "behind" this
	//0 -- one endpoint is on each side of this
	public int whichSide(Segment other){
		
		
		int side1 = whichSidePoint(other.x1, other.y1);
		int side2 = whichSidePoint(other.x2, other.y2);
		
		if(side1 > 0 && side2 > 0){
			return 1; //+ side
		} else if(side1 < 0 && side2 < 0){
			return -1; //- side
		} else {
			return 0; //straddling
		}
		

	}
	
	//based on the answer from here: 
	//http://stackoverflow.com/questions/563198/how-do-you-detect-where-two-line-segments-intersect
	//the first point in the array is on the '-' side, 
	//the second is on the '+' side
	public Segment[] split(Segment other){
		//return 2 points
		Segment[] ret = new Segment[2];
		//vector between endpoints of other
		double rx = other.x2 - other.x1;
		double ry = other.y2 - other.y1;
		
		//vector between endpoints of this
		double sx = x2 - x1;
		double sy = y2 - y1;
		
		//v × w to be vx wy − vy wx 
		double rxs = rx*sy - ry*sx;
		
		double qps = (x1 - other.x1)*sy - (y1 - other.y1)*sx;
		
		//compute where this crosses other
		double t = qps/rxs;
		
		double splitX = other.x1 + t*(other.x2 - other.x1);
		double splitY = other.y1 + t*(other.y2 - other.y1);
		
		//return them so the first segment is on the - side of this,
		//and the second is on the + side
		if(this.whichSidePoint(other.x1, other.y1) < 0){
			ret[0] = new Segment(other.x1, other.y1, splitX, splitY);
			ret[1] = new Segment(splitX, splitY, other.x2, other.y2);
		} else {
			//return the - side first
			ret[1] = new Segment(other.x1, other.y1, splitX, splitY);
			ret[0] = new Segment(splitX, splitY, other.x2, other.y2);
		}
		return ret;
	}
	
	public void draw(){
		StdDraw.line(x1, y1, x2, y2);
	}
}
