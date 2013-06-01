package Writer;

import history.BuySellHistory;
import java.io.IOException;
import java.util.Calendar;

public class DataWriterHTML extends DataWriter {

	public DataWriterHTML(String outputname) {
		super(outputname);
	}

	@Override
	public void printStringtoData(String datatowrite) throws IOException{
		writeHTMLHeader(datatowrite);
		writeHTMLTail();
		outputbufferedwriter.flush();
	}
	
	public void printStringtoData(BuySellHistory history) throws IOException{
		String newline = "\r\n";
		String s = "";
		for (int i = 0; i < history.getAll().length; i++) {
			s +="<td>"+ history.getAll()[i].toString()+"</td>"+newline+"</tr><tr>"+newline;
		}
		printStringtoData(s);
	}
	private void writeHTMLHeader(String code) throws IOException{
	    String newline = "\r\n";
		outputbufferedwriter.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\">"+newline); 
		outputbufferedwriter.write("<html><head><title>Listing von "+Calendar.getInstance().getTime()+"</title>"+newline); 
		
		outputbufferedwriter.write("</html><body>"+newline);
	    outputbufferedwriter.write("<table border = \"1\">"+newline); 
	    outputbufferedwriter.write("<caption style=\"caption-side:bottom\">Assoziationen</caption>"+newline); 
	    
	    outputbufferedwriter.write("<tr>"+newline); 
	    outputbufferedwriter.write("<th>Data</th>"+newline); 
	    outputbufferedwriter.write("</tr><tr>"+newline);
	    outputbufferedwriter.write(code+newline);
	}
	
	private void writeHTMLTail() throws IOException{
		String newline = "\r\n";
	    outputbufferedwriter.write("</body > "+newline); 
	    outputbufferedwriter.write("</html> "+newline); 
	}
}
