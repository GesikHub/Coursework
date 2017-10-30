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
	
	@Override
	public double f(Double x) {
		int index = this.values.index(new Point(x, 0.0));
		if(index == -1) {
			try {
				return  linearInterpolation(x);
			} catch(LinerInterpolationException e) {
				System.out.println(e.toString());
				return 0;
			} catch(Exception e) {
				e.printStackTrace();
				return 0;
			}
		}
		else {
			return values.get(index).getY();
		}
	}
	
	private double linearInterpolation(Double x) throws LinerInterpolationException, Exception{
		Point point = new Point(x, 0.0);
		values.addPoint(point);
		if(values.index(point) == 0 || values.index(point) == values.count() - 1)
			throw new LinerInterpolationException(x);
		int index = values.index(new Point(x, 0.0));
		Double y; 
		y = values.get(index - 1).getY() + 
				(((values.get(index + 1).getY() - values.get(index - 1).getY()) / (values.get(index + 1).getX() - values.get(index - 1).getX())) * 
				(x - values.get(index - 1).getX()));
		values.get(index).setY(y);
		results.addXY(x, y);
		return y;
	}
}