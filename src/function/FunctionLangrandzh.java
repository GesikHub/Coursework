package function;

import point.ArrayPoint;
import point.Point;;

public class FunctionLangrandzh extends DataFunction{
	
	public FunctionLangrandzh() {
		super();
	}
	public FunctionLangrandzh(ArrayPoint point) {
		super(point);
	}
	public  FunctionLangrandzh(ArrayPoint point, ArrayPoint point1) {
		super(point, point1);
	}
	
	@Override
	public double f(Double x) {
			return langrandzhInterpolation(x);
	}
	
	private double langrandzhInterpolation(Double x) {
		double polinom = 0, basicPolinom = 0;
		for (int i = 0; i < getValues().count(); i++)
		{
			basicPolinom = 1;
			for (int j = 0; j < getValues().count(); j++)
			{
				if (j == i) continue;
				basicPolinom *= (x - getValues().get(j).getX())/(getValues().get(i).getX() - getValues().get(j).getX());		
			}
			polinom += basicPolinom * getValues().get(i).getY();
		}
		getResults().addXY(x, polinom);
		return polinom;
	}
}
