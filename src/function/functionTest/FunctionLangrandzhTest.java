package function.functionTest;

import function.FunctionLangrandzh;
import point.ArrayPoint;

public class FunctionLangrandzhTest {
	public static void main(String[] args) {
		ArrayPoint points = new ArrayPoint();
		
		points.addXY(1.0, 1.0);
		points.addXY(3.0, 3.0);

		
		FunctionLangrandzh function = new FunctionLangrandzh(points);
		
		System.out.println(function.f(2.0));
		System.out.println(function.f(2.5));
		System.out.println(function.f(0.0));
	}	
}