package equation.equationException;;

public class NoRootException extends Exception {
	public NoRootException() {}
	
	@Override
	public String toString() {
		String str = "Корней нет";
		return str;
	}
}
