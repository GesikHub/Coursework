package function;

import point.ArrayPoint;
import point.Point;
import function.functionException.LinerInterpolationException;

public class FuctionLinearInterpolation extends DataFunction{
	
	public FuctionLinearInterpolation() {
		super();
	}
	public FuctionLinearInterpolation(ArrayPoint point) {
		super(point);
	}
	public FuctionLinearInterpolation(ArrayPoint point, ArrayPoint point1) {
		super(point, point1);
	}
	
	
	@Override
	public double f(Double x) {
		int index = this.getValues().index(new Point(x, 0.0));
		if(index == -1) {
			try {
				return  linearInterpolation(x);
			} catch(LinerInterpolationException e) {
				e.printStackTrace();
				return 0;
			} catch(Exception e) {
				e.printStackTrace();
				return 0;
			}
		}
		else {
			return getValues().get(index).getY();
		}
		
	}
	
	private double linearInterpolation(Double x) throws LinerInterpolationException, Exception{
		Point point = new Point(x, 0.0);
		getValues().addPoint(point);
		if(getValues().index(point) == 0 || getValues().index(point) == getValues().count() - 1)
			throw new LinerInterpolationException(x);
		int index = getValues().index(new Point(x, 0.0));
		Double y; 
		y = getValues().get(index - 1).getY() + 
				(((getValues().get(index + 1).getY() - getValues().get(index - 1).getY()) / (getValues().get(index + 1).getX() - getValues().get(index - 1).getX())) * 
				(x - getValues().get(index - 1).getX()));
		getValues().get(index).setY(y);
		getResults().addXY(x, y);
		return y;
	}
}