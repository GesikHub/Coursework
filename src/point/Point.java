package point;

public class Point implements Comparable<Point>{
	private Double x;
	private Double y;
	
	Point() {  }
	public Point(Double x, Double y) {
		this.x = x;
		this.y = y;
	}
	public Point(Point point) {
		this.x = point.getX();
		this.y = point.getY();
	}
	
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	
	public void setX(Double x) {
		this.x = x;
	}	
	public void setY(Double y) {
		this.y = y;
	}
	public void setPoint(Double x, Double y) {
		this.x = x;
		this.y = y;
	}
	public void setPoint(Point point) {
		setPoint(point.getX(), point.getY());
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null || !(obj instanceof Point))
			return false;
		Point point = (Point) obj;
		return point.getX() == this.x &&
			   point.getY() == this.y;
	}
	
	@Override
	public int compareTo(Point point) {
		return Double.compare(this.x, point.getX());
	}
}
