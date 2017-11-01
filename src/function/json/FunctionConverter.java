package function.json;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import function.DataFunction;
import function.FuctionLinearInterpolation;

public class FunctionConverter {
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private String fileIn;
	private String fileOut;
	
	public FunctionConverter() {
		fileIn = "dataIn.json";
		fileOut = "dataOut.json";
	}	
	FunctionConverter(String fileIn, String fileOut) {
		this.fileIn = fileIn;
		this.fileOut = fileOut;
	}
	
	public void getValuesFromJSON(DataFunction function, String fileOut) {
		 try (FileWriter writer = new FileWriter(fileOut)){
	            writer.write(gson.toJson(function));
	            writer.flush();
	            writer.close();
	        } catch (IOException ex) {
	        	System.out.println(ex.toString());
	        }
	}	
	public void getValuesFromJSON(DataFunction function) {
		 getValuesFromJSON(function, this.fileOut);
	}
	
	public DataFunction setValuesToJSON(String fileIn, Class classOfT) {
		try {
			 FileReader fromFile = new FileReader(fileIn);
			 BufferedReader buffer = new BufferedReader(fromFile);
			 String data = new String();
			 String temp = null;
			 while((temp = buffer.readLine()) != null) {
				 data += temp + "\n";
			 }
			 DataFunction function = (DataFunction) gson.fromJson(data, classOfT);
			 buffer.close();
			 return function;
			} catch(IOException ioException) {
				ioException.printStackTrace();
			} 
		return null;
	}
	public DataFunction setValuesToJSON(Class classOfT) {
		return setValuesToJSON(this.fileIn, classOfT);
	}
}
