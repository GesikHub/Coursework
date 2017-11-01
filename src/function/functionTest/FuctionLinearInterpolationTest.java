package function.functionTest;


import function.FuctionLinearInterpolation;
import point.ArrayPoint;

public class FuctionLinearInterpolationTest {

	public static void main(String[] args) {
		FuctionLinearInterpolation function = new FuctionLinearInterpolation();
		function.setValuesToJSON("data.json");
		System.out.println(function.getValues());
		System.out.println(function.f(-1.0));
		System.out.println(function.f(-0.5));
		System.out.println(function.f(2.0));
		System.out.println(function.f(2.5));
		function.getValuesFromJSON("data.json");
	}
}
