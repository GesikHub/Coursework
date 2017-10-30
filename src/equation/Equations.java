package equation;

import point.*;
import function.*;


public class Equations {
	private FuctionLinearInterpolation f1;
	private FunctionLangrandzh f2;
	private Double start;
	private Double finish;
	private Double eps = 0.000001;
	
	Equations() {
	}
	Equations(FuctionLinearInterpolation f1, FunctionLangrandzh f2) {
		this.f1 = new FuctionLinearInterpolation(f1.getValues());
		this.f2 = new FunctionLangrandzh(f2.getValues());
		domain();
	}
	
	private void domain() {
		if(f1.getValues().get(0).getX() >= f2.getValues().get(0).getX()) {
			this.start = f1.getValues().get(0).getX();
		}
		else {
			this.start = f2.getValues().get(0).getX();
		}
		
		if(f1.getValues().get(f1.getValues().count() - 1).getX() <= f2.getValues().get(f2.getValues().count() - 1).getX()) {
			this.finish = f1.getValues().get(f1.getValues().count() - 1).getX();
		}	
		else {
			this.finish = f2.getValues().get(f1.getValues().count() - 1).getX();
		}
	}
	
	public double root() {
		double c = 0;
		do
	    {
	        c = (start + finish) / 2;
	        if(f1.f(c) - f2.f(c) >= 0.0) {
	            if(f1.f(c) - f2.f(c) == 0.0)
	            	break;
	            finish = c;
	        }
	        else {
	            if(f1.f(c) - f2.f(c) == 0.0)
	            	break;
	            start = c;
	            
	        }
	    }  while (Math.abs(finish) - Math.abs(start) > eps);
	    return c;
	}
	
	public static void main(String[] args) {
		ArrayPoint points = new ArrayPoint();
		points.addXY(-3.0, 7.0);
		points.addXY(2.0, -3.0);
		FunctionLangrandzh function = new FunctionLangrandzh(points);
		System.out.println(function.getValues().count());
		
		ArrayPoint points1 = new ArrayPoint();	
		points1.addXY(-2.0, -1.0);
		points1.addXY(0.0, 3.0);
		points1.addXY(1.0, 5.0);
		FuctionLinearInterpolation function1 = new FuctionLinearInterpolation(points1);
		System.out.println(function1.getValues().count());
		
		Equations eq = new Equations(function1, function);
		System.out.println(eq.finish);
		System.out.println(eq.start);
		System.out.println(eq.root());
		System.out.println(function.f(eq.root()));
		System.out.println(function1.f(eq.root()));
	}
}
