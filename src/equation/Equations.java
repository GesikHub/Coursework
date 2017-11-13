package equation;

import point.*;
import point.json.FunctionConverter;
import equation.equationException.AgreeFunction;
import equation.equationException.NoRootException;
import function.*;


public class Equations {
	private DataFunction f1;
	private DataFunction f2;
	private ArrayPoint roots;
	private Double start;
	private Double finish;
	private Double eps = 0.0001;
	
	Equations() {
	}
	public Equations(DataFunction f1, DataFunction f2) {
		this.f1 = f1;
		this.f2 = f2;
		this.roots = new ArrayPoint();
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
	
	public void writeResult(String name) {
		FunctionConverter json = new FunctionConverter(); 
		json.getValuesFromJSON(this.roots, name);
	}
	
	public void root() throws AgreeFunction, NoRootException{
		Double h = 1.0;
		int i = 0;
		for(Double start = this.start, finish = this.start + h; finish <= this.finish; start += h, finish += h, i++) {
			Double root1 = root(start, finish);
        	System.out.println((f1.f(root1) - f2.f(root1)) + " " + root1);
			if(Math.abs(f1.f(root1) - f2.f(root1)) <= eps * 10)
				roots.addXY(root1, f1.f(root1) - f2.f(root1));
		}
		if(i / 2 < this.roots.count())
			throw new AgreeFunction();
		if(this.roots.count() == 0)
			throw new NoRootException();
	}
	
	@Override
	public String toString() {
		String str = roots.count() + "\n";
		str += roots.toString();
		return str;
	}
}