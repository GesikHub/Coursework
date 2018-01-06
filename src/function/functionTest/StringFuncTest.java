package function.functionTest;

import function.Function;
import function.StringFunction;

public class StringFuncTest {

	public static void main(String[] args) {
			Function func = new StringFunction("x+2");
			System.out.println(func.f(3.0));
	}

}
