package point;

import java.util.ArrayList;

public class ArrayPoint {
	private ArrayList<Point> points;
	
	public ArrayPoint() {
		points = new ArrayList<>();
	}
	public ArrayPoint(ArrayList<Point> point) {
		points = point;
	}
	
	public void addXY(Double x, Double y) {
		points.add(new Point(x, y));
		this.sort();
	}
	
	public void addPoint(Point point) {
		addXY(point.getX(), point.getY());
	}
	
	public void sort() {
		points.sort(new CompareByX());
	}
	
	public int count() {
		return points.size();
	}
	
	public int index(Point point) {
		int index = -1;
		for(int i = 0; i < count(); i++) {
			if(point.getX() == points.get(i).getX()) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public Point get(int index) {
		return points.get(index);
	}
	
	@Override
	public String toString() {
		String input = "";
		for(Point point : points) {
 			input += "x = " + point.getX() + " y = " + point.getY() + "\n";
		}
		return input;
	}
}
