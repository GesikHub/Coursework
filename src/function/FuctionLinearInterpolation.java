package function;

import point.ArrayPoint;
import point.Point;
import function.functionException.LinerInterpolationException;
import javafx.collections.ObservableList;

public class FuctionLinearInterpolation extends DataFunction{
	
	public FuctionLinearInterpolation() {
		super();
	}
	public FuctionLinearInterpolation(ArrayPoint point) {
		super(point);
		getValues().sort();
		for(int i = 0; i < getValues().count(); i++) {
			getResults().addPoint(getValues().get(i));
		}
	}
	public FuctionLinearInterpolation(ArrayPoint point, ArrayPoint point1) {
		super(point, point1);
	}
	
	@Override
	public double f(Double x) {
		int index = this.getResults().index(x);
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
			return getResults().get(index).getY();
		}
		
	}
	
	private double linearInterpolation(Double x) throws LinerInterpolationException, Exception{
		Point point = new Point(x, 0.0);
		getResults().addPoint(point);
		getResults().sort();
		if(getResults().index(x) == 0 || getResults().index(x) == getResults().count() - 1)
			throw new LinerInterpolationException(x);
		int index = getResults().index(x);
		Double y; 
		y = getResults().get(index - 1).getY() + 
				(((getResults().get(index + 1).getY() - getResults().get(index - 1).getY()) / (getResults().get(index + 1).getX() - getResults().get(index - 1).getX())) * 
				(x - getResults().get(index - 1).getX()));
		getResults().get(index).setY(y);
		return y;
	}
}