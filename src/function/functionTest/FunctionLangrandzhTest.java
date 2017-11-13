package function.functionTest;


import function.FuctionLinearInterpolation;
import function.FunctionLangrandzh;
import function.functionException.RepeatXException;
import point.ArrayPoint;

public class FunctionLangrandzhTest {
	public static void main(String[] args) {		
		FunctionLangrandzh function = new FunctionLangrandzh();
		function.setValuesToJSON("SomeX.json");

		System.out.println(function.f(-1.0));
	}
}
