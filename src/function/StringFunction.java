package function;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringFunction implements Function{
	private String func;
	
	public StringFunction(String func) {
		this.func = func;
	}

	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

	public double f(Double x) {
		File file = new File("main.py");
		try {
			Process process = new ProcessBuilder()
			        .command("python D:\\Labs\\Coursework\\src\\main.py ", func, x.toString() )
			        .start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(file.getPath());
	     return Double.valueOf(1);
	}

}
