package point.pointTest;

import point.ArrayPoint;
import point.Point;

public class ArrayPointTest {
	
	public static void main(String[] args) {
		ArrayPoint points = new ArrayPoint();
		
		points.addXY(1.0, 2.0);
		points.addXY(3.0, 1.0);
		points.addXY(-1.0, 2.0);
		points.addXY(-3.0, 9.0);
		points.addXY(10.0, 11.0);
		points.sort();
		
		System.out.println(points.toString());
		System.out.println(points.index(3.0));
	}
}
