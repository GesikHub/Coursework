package equation.equationsTest;

import equation.Equations;
import function.FuctionLinearInterpolation;
import function.FunctionLangrandzh;
import point.ArrayPoint;

public class EquationsTest {
	public static void main(String[] args) {
		ArrayPoint points = new ArrayPoint();
		points.addXY(-1.0, 4.0);
		points.addXY(0.0, 4.0);
		points.addXY(-3.0, 4.0);
		FunctionLangrandzh function = new FunctionLangrandzh(points);
		System.out.println(function.getValues().count());
		
		ArrayPoint points1 = new ArrayPoint();	
		points1.addXY(-1.0, 4.0);
		points1.addXY(0.0, 4.0);
		points1.addXY(-3.0, 4.0);
		FuctionLinearInterpolation function1 = new FuctionLinearInterpolation(points1);
		System.out.println(function1.getValues().count());
		
		Equations eq = new Equations(function1, function);
		System.out.println(eq.toString());
	}
}
