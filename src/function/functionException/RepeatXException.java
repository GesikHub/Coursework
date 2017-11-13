package function.functionException;

public class RepeatXException extends Exception{
	private Double x;
	private int i;
	public RepeatXException(Double x, int i) {
		this.x = x;
		this.i = i;
	}
	
	@Override
	public String toString() {
		return "Повторяеться " + x; 
	}
	
	public int getIndex() {
		return i;
	}
}
