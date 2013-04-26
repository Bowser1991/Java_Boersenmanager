package Command;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import Exception.WrongCommandException;
import enums.*;

public class StockGameCommandProcessor {
	private static BufferedReader shellreader = new BufferedReader(new InputStreamReader(System.in));
	private static PrintWriter shellwirter = new PrintWriter(System.out);
	private static CommandDescriptor descriptor = new CommandDescriptor();
	
	public static void process() throws WrongCommandException, IOException {
	    CommandScanner commandScanner;
		try {
			commandScanner = new CommandScanner(StockGameCommandType.values(), descriptor);
		} catch (WrongCommandException e) {
			commandScanner = new CommandScanner(StockGameCommandType.values(), descriptor);
			System.out.println("hallo");
		}
        
	    while (true) { // die Schleife über alle Kommandos, jeweils ein Durchlauf pro Eingabezeile
	        String s = shellreader.readLine();
	    	commandScanner.checkCommandSyntax(s);
	        Object[] params = descriptor.getParams();
	        StockGameCommandType commandType = (StockGameCommandType)descriptor.getCommandType();
	        
	        
	        switch (commandType) {
	           case EXIT: {
	               System.exit(0);
	           }
	           case HELP: {
	               
	           }
	           case CREATEPLAYER: {
	               
	           }
			case ACCOUNTWORTH:
				break;
			case BUYSHARE:
				break;
			case SELLSHARE:
				break;
			default:
				break;
	        }
	    }
	}
	
	public static void main(String[] args) {
	try {
		process();
	} catch (Exception e) {
		e.printStackTrace();
	}

	}

}
	
