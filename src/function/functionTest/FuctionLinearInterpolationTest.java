package function.functionTest;

import function.FuctionLinearInterpolation;
import point.ArrayPoint;

public class FuctionLinearInterpolationTest {
	
	public static void main(String[] args) {
		ArrayPoint points = new ArrayPoint();
		
		points.addXY(1.0, 1.0);
		points.addXY(3.0, 3.0);
		
		FuctionLinearInterpolation function = new FuctionLinearInterpolation(points);
		
		System.out.println(function.f(2.0));
		System.out.println(function.f(-1.5));
		System.out.println(function.f(4.0));
	}
}
