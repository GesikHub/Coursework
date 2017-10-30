package function.functionException;

import java.lang.Exception;

public class LinerInterpolationException extends Exception{
	private Double x;
	
	public LinerInterpolationException(Double x) {
		this.x =x;
	}
	
	@Override
	public String toString() {
		return "Нельзя обчислить точку с x = " + x; 
	}
}
