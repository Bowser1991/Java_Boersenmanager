package Command;

import innerimpl.AccountManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import Exception.WrongCommandException;
import Exception.WrongNameException;
import enums.*;

public class StockGameCommandProcessor {
	private BufferedReader shellreader = new BufferedReader(
			new InputStreamReader(System.in));
	private PrintWriter shellwriter = new PrintWriter(System.out);
	private CommandDescriptor descriptor = new CommandDescriptor();
	private AccountManager accountmanager = null;
	
	public StockGameCommandProcessor(AccountManager manager ){
		accountmanager = manager;
	}
	
	public  void process() {
		CommandScanner commandscanner = new CommandScanner(StockGameCommandType.values(), descriptor);
		
		while (true) { 
			
			boolean flag = true;
			while(flag){
				try{
					commandscanner.checkCommandSyntax(shellreader);
					flag = false;
					}catch(WrongCommandException e){
						shellwriter.println("Invalid Command !");
						shellwriter.flush();
					}catch (IOException e) {
						shellwriter.println("IO Exception failure !");
						shellwriter.flush();
					}
			}
			StockGameCommandType commandType = (StockGameCommandType) descriptor.getCommandType();

			try {
				switch (commandType) {
				case EXIT:
					shellwriter.println("System is about to close");
					shellwriter.flush();
					System.exit(0);
					break;
				case HELP:
					for (int i = 0; i < StockGameCommandType.values().length; i++) {
						shellwriter.println(StockGameCommandType.values()[i].getName()+StockGameCommandType.values()[i].getHelpText());
					}
					shellwriter.flush();
					break;
				default:
					try{
						if(descriptor.getParams()[0].equals("help")){
							shellwriter.println(commandType.getHelpText());
							shellwriter.flush();
						}else{
						Method executemethod = accountmanager.getClass().getMethod(commandType.getImplMethods(), commandType.getParamTypes());
						executemethod.invoke(accountmanager, descriptor.getParams());
						}
					}catch(Exception e){
						shellwriter.println("Action :"+commandType.getImplMethods()+" could not be done");
						shellwriter.flush();
					}
					break;
				}
			}catch (WrongNameException e) {
				shellwriter.println("Invalid Playername !");
				shellwriter.flush();
			}
		}
	}
}
