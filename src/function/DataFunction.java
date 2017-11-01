package function;

import point.ArrayPoint;
import function.json.FunctionConverter;


public abstract class DataFunction implements Function{
	private ArrayPoint values;
	private ArrayPoint results;
	
	public DataFunction() {
		values = new ArrayPoint();
		results = new ArrayPoint();
	}
	public DataFunction(ArrayPoint point) {
		values = point;
		results = new ArrayPoint();
		values.sort();
	}
	public DataFunction(ArrayPoint point, ArrayPoint point1) {
		values = point;
		results = point1;
		values.sort();
	}

	public ArrayPoint getValues() {
		return this.values;
	}	
	public ArrayPoint getResults() {
		return this.results;
	}
	
	public void setValues(ArrayPoint point) {
		this.values = point;
	}
	public void setResults(ArrayPoint point) {
		this.results = point;
	}
	
	public void getValuesFromJSON(String name) {
		FunctionConverter json = new FunctionConverter(); 
		json.getValuesFromJSON(this, name);
	}
	
	public void setValuesToJSON(String name) {
		FunctionConverter json = new FunctionConverter(); 
		DataFunction function = json.setValuesToJSON(name, this.getClass());
		values = function.getValues();
		results = function.getResults();
	}
}
