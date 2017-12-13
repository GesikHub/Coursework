package equation;

import point.*;
import point.json.FunctionConverter;
import equation.equationException.AgreeFunction;
import equation.equationException.NoRootException;
import function.*;


public class Equations {
	private Function f1;
	private Function f2;
	private ArrayPoint roots;
	
	Equations() {
	}
	public Equations(Function f1, Function f2) {
		this.f1 = f1;
		this.f2 = f2;
		this.roots = new ArrayPoint();
	}
	
	private double pRoot(Double start, Double finish, Double eps) {
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
	
	public void root(Double start, Double finish, Double eps) throws AgreeFunction, NoRootException{
		Double h = 1.0;
		int i = 0;
		for(Double mstart = start, mfinish = start + h; mfinish <= finish; mstart += h, mfinish += h, i++) {
			Double root1 = pRoot(mstart, mfinish, eps);
			if(Math.abs(f1.f(root1) - f2.f(root1)) <= eps * 10)
				roots.addXY(root1, f1.f(root1) - f2.f(root1));
		}
		if(i / 2 < this.roots.count())
		{
			roots = new ArrayPoint();
			roots.addXY(null, null);
			throw new AgreeFunction();
		}
		if(this.roots.count() == 0)
			throw new NoRootException();
	}
	
	@Override
	public String toString() {
		String str = roots.count() + "\n";
		str += roots.toString();
		return str;
	}
	
	public ArrayPoint getRoots() {
		return this.roots;
	}
}