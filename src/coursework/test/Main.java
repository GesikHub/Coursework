package coursework.test;

import equation.Equations;
import equation.equationException.AgreeFunction;
import equation.equationException.NoRootException;
import function.DataFunction;
import function.FuctionLinearInterpolation;
import function.FunctionLangrandzh;
import function.functionException.RepeatXException;

//public class Main {

	/*public static void main(String[] args) {
		DataFunction function2 = new FunctionLangrandzh();
		function2.setValuesToJSON("SomeX.json");
		while(true) {
			try {
					function2.checkRepeat();
					break;
			} catch (RepeatXException e) {
				function2.getValues().get(e.getIndex()).setX(-5.0);;
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		FuctionLinearInterpolation function3 = new FuctionLinearInterpolation();
		function2.setValuesToJSON("SeveralRoots2.json");
		while(true) {
			try {
					function2.checkRepeat();
					break;
			} catch (RepeatXException e) {
				function2.getValues().get(e.getIndex());
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		DataFunction function4 = new FunctionLangrandzh();
		function4.setValuesToJSON("NoRoots.json");
		while(true) {
			try {
					function4.checkRepeat();
					break;
			} catch (RepeatXException e) {
				function4.getValues().get(e.getIndex()).setX(-5.0);;
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		FuctionLinearInterpolation function5 = new FuctionLinearInterpolation();
		function5.setValuesToJSON("SeveralRoots2.json");
		while(true) {
			try {
					function5.checkRepeat();
					break;
			} catch (RepeatXException e) {
				function5.getValues().get(e.getIndex());
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		Equations eq1 = new Equations(function4, function5);
		try {
			eq1.root();
		} catch (AgreeFunction | NoRootException e) {
			e.printStackTrace();
		}
		
		DataFunction function6 = new FunctionLangrandzh();
		function6.setValuesToJSON("MuchRoots.json");
		while(true) {
			try {
					function6.checkRepeat();
					break;
			} catch (RepeatXException e) {
				function6.getValues().get(e.getIndex()).setX(-5.0);;
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		FuctionLinearInterpolation function7 = new FuctionLinearInterpolation();
		function5.setValuesToJSON("SeveralRoots2.json");
		while(true) {
			try {
					function7.checkRepeat();
					break;
			} catch (RepeatXException e) {
				function7.getValues().get(e.getIndex());
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		Equations eq2 = new Equations(function6, function5);
		try {
			eq2.root();
		} catch (AgreeFunction | NoRootException e) {
			e.printStackTrace();
		}
		
		DataFunction function = new FunctionLangrandzh();
		function.setValuesToJSON("SeveralRoots.json");
		while(true) {
			try {
					function.checkRepeat();
					break;
			} catch (RepeatXException e) {
				function.getValues().get(e.getIndex()).setX(-5.0);;
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		FuctionLinearInterpolation function1 = new FuctionLinearInterpolation();
		function1.setValuesToJSON("SeveralRoots2.json");
		while(true) {
			try {
					function1.checkRepeat();
					break;
			} catch (RepeatXException e) {
				function1.getValues().get(e.getIndex());
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		Equations eq = new Equations(function, function1);
		try {
			eq.root();
		} catch (AgreeFunction | NoRootException e) {
			e.printStackTrace();
		}
		System.out.println(eq.toString());
		eq.writeResult("data.json");

	}

}*/
