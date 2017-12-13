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
		this.sort();
	}
	
	public void sort() {
		points.sort(new CompareByX());
	}
	
	public int count() {
		return points.size();
	}
	
	public int index(Double point) {
		int index = -1;
		for(int i = 0; i < count(); i++) {
			if(point.equals(points.get(i).getX())) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public Point get(int index) {
		return points.get(index);
	}
	
	public boolean checkRepeat(Double x, int size) {
		int repeat = 0;
		for(int i = 0; i < size; i++) {
			if(x == points.get(i).getX()) {
				repeat++;
			}
		}
		if(repeat > 0)
			return true;
		return false;
	}
	
	public void clear() {
		points = new ArrayList<>();
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
