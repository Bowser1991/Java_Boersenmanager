package Command;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import enums.*;

public class StockGameCommandProcessor {
	private BufferedReader shellReader = new BufferedReader(new InputStreamReader(System.in));
	private PrintWriter shellWriter = new PrintWriter(System.out);
	
	public void process() {
	    CommandScanner commandScanner = new CommandScanner(StockGameCommandType.values(), shellReader);
        
	    while (true) { // die Schleife über alle Kommandos, jeweils ein Durchlauf pro Eingabezeile
	        CommandDescriptor command = new CommandDescriptor();
	        commandScanner.fillInCommandDesc(command);       

	        Object[] params = command.getParams();

	        StockGameCommandType commandType = (StockGameCommandType)command.getCommandType();
	        switch (commandType) {
	           case EXIT: {
	               
	           }
	           case HELP: {
	               
	           }
	           case CREATEPLAYER: {
	               
	           }
	        }
	    }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
	
