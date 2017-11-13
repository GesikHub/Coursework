package function.functionTest;


import function.FuctionLinearInterpolation;
import function.functionException.RepeatXException;
import point.ArrayPoint;

public class FuctionLinearInterpolationTest {

	public static void main(String[] args) {
		ArrayPoint points = new ArrayPoint();
		
		points.addXY(1.0, 1.0);
		points.addXY(3.0, 3.0);
		
		FuctionLinearInterpolation function = new FuctionLinearInterpolation(points);
		
		
		System.out.println(function.getValues());
		System.out.println(function.f(2.0));
		function.getValuesFromJSON("data.json");
	}
}
