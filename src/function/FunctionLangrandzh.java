package function;

import point.ArrayPoint;;

public class FunctionLangrandzh extends DataFunction{
	
	public FunctionLangrandzh() {
		super();
		values = new ArrayPoint();
	}
	public FunctionLangrandzh(ArrayPoint point) {
		super(point);
	}
	
	@Override
	public double f(Double x) {
		return langrandzhInterpolation(x);
	}
	
	private double langrandzhInterpolation(Double x) {
		double polinom = 0, basicPolinom = 0;
		for (int i = 0; i < values.count(); i++)
		{
			basicPolinom = 1;
			for (int j = 0; j < values.count(); j++)
			{
				if (j == i) continue;
				basicPolinom *= (x - values.get(j).getX())/(values.get(i).getX() - values.get(j).getX());		
			}
			polinom += basicPolinom *values.get(i).getY();
		}
		results.addXY(x, polinom);
		values.addXY(x, polinom);
		return polinom;
	}
}
