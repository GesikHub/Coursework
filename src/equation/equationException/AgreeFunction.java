package equation.equationException;

public class AgreeFunction extends Exception {
	public AgreeFunction() {}
	
	@Override
	public String toString() {
		String str = "Функции совпадают";
		return str;
	}
}
