package equation.equationsTest;

import equation.Equations;
import equation.equationException.AgreeFunction;
import equation.equationException.NoRootException;
import function.DataFunction;
import function.FuctionLinearInterpolation;
import function.FunctionLangrandzh;

public class EquationsTest {
	public static void main(String[] args) {
		DataFunction function = new FunctionLangrandzh();
		function.setValuesToJSON("SeveralRoots.json");
		

		FuctionLinearInterpolation function1 = new FuctionLinearInterpolation();
		function1.setValuesToJSON("SeveralRoots2.json");
		
		Equations eq = new Equations(function, function1);
		try {
			eq.root(-3.0, 3.0, 0.001);
		} catch (AgreeFunction | NoRootException e) {
			e.printStackTrace();
		}
		System.out.println(eq.toString());
		eq.writeResult("data.json");
	}
}