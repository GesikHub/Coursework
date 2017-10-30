package point;

import java.util.Comparator;

public class CompareByX implements Comparator<Point>{
	CompareByX() {}
	public int compare(Point p1, Point p2) {
		return p1.compareTo(p2);
	}
}
