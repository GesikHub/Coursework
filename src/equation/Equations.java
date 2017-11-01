package equation;

import point.*;
import equation.equationException.AgreeFunction;
import equation.equationException.NoRootException;
import function.*;


public class Equations {
	private FuctionLinearInterpolation f1;
	private FunctionLangrandzh f2;
	private ArrayPoint roots;
	private Double start;
	private Double finish;
	private Double eps = 0.000001;
	
	Equations() {
	}
	public Equations(FuctionLinearInterpolation f1, FunctionLangrandzh f2) {
		this.f1 = f1;
		this.f2 = f2;
		this.roots = new ArrayPoint();
		domain();
	}
	
	private void domain() {
		this.start = f1.getValues().get(0).getX();
		this.finish = f1.getValues().get(f1.getValues().count() - 1).getX();
	}
	
	public double root(Double start, Double finish) {
		double c = 0;
		do
	    {
	        c = (start + finish) / 2;
	        if((f1.f(c) - f2.f(c)) * (f1.f(finish) - f2.f(finish)) < 0.0) {
	        	 start = c;
	        }
	        else {
	        	finish = c;	
	        }
	    }  while (finish - start > eps);
	    return c;
	}
	
	public void root() throws AgreeFunction, NoRootException{
		Double step = Math.abs(Math.abs(this.finish) - Math.abs(this.start));
		Double h = Math.pow(step, 10) * eps;
		int i = 0;
		for(Double start = this.start, finish = start + h; finish <= this.finish; start += h, finish += h, i++) {
			Double root = root(start, finish);
			if(!(f1.f(root) - f2.f(root) > eps))
				roots.addXY(root, f1.f(root));
		}
		if(i / 2 < this.roots.count())
			throw new AgreeFunction();
		if(this.roots.count() == 0)
			throw new NoRootException();
	}
	
	public FuctionLinearInterpolation getF1() {
		return f1;
	}
	public void setF1(FuctionLinearInterpolation f1) {
		this.f1 = f1;
	}
	public FunctionLangrandzh getF2() {
		return f2;
	}
	public void setF2(FunctionLangrandzh f2) {
		this.f2 = f2;
	}
	
	@Override
	public String toString() {
		String str = roots.count() + "\n";
		str += roots.toString();
		return str;
	}
}
