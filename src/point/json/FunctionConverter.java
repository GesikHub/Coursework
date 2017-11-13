package point.json;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import function.DataFunction;
import function.FuctionLinearInterpolation;
import point.ArrayPoint;

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
	
	public void getValuesFromJSON(ArrayPoint points, String fileOut) {
		 try (FileWriter writer = new FileWriter(fileOut)){
	            writer.write(gson.toJson(points));
	            writer.flush();
	            writer.close();
	        } catch (IOException ex) {
	        	System.out.println(ex.toString());
	        }
	}	
	public void getValuesFromJSON(ArrayPoint points) {
		 getValuesFromJSON(points, this.fileOut);
	}
	
	public ArrayPoint setValuesToJSON(String fileIn) {
		try {
			 FileReader fromFile = new FileReader(fileIn);
			 BufferedReader buffer = new BufferedReader(fromFile);
			 String data = new String();
			 String temp = null;
			 while((temp = buffer.readLine()) != null) {
				 data += temp;
			 }
			 ArrayPoint function = (ArrayPoint) gson.fromJson(data, ArrayPoint.class);
			 buffer.close();
			 return function;
			} catch(IOException ioException) {
				ioException.printStackTrace();
			} 
		return null;
	}
	public ArrayPoint setValuesToJSON() {
		return setValuesToJSON(this.fileIn);
	}
}
