package Writer;

import history.BuySellHistory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class DataWriter {
	
	protected String outputdataname;
	protected File outputdata;
	protected FileWriter outputwriter;
	protected BufferedWriter outputbufferedwriter;
	
	public DataWriter(String outputname){
		this.outputdataname = outputname;
		outputdata = new File(outputdataname+".html");
		try {
			outputwriter = new FileWriter(outputdata);
		} catch (IOException e) {
			e.printStackTrace();
		}
		outputbufferedwriter = new BufferedWriter(outputwriter);
	}
	public abstract void printStringtoData(String datatowrite) throws IOException;
	public abstract void printStringtoData(BuySellHistory history) throws IOException;
}
