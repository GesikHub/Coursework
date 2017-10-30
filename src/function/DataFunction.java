package function;

import point.ArrayPoint;

public abstract class DataFunction implements Function{
	ArrayPoint values;
	ArrayPoint results;
	
	public DataFunction() {
		values = new ArrayPoint();
		results = new ArrayPoint();
	}
	public DataFunction(ArrayPoint point) {
		values = point;
		results = new ArrayPoint();
		values.sort();
	}
	
	public ArrayPoint getValues() {
		return this.values;
	}
	
	public void setValues(ArrayPoint point) {
		this.values = point;
	}
}
