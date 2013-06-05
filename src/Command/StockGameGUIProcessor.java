package Command;

import innerimpl.AccountManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.lang.reflect.Method;

import Exception.WrongCommandException;
import Exception.WrongNameException;
import enums.HistorySortType;
import enums.StockGameCommandType;

public class StockGameGUIProcessor {
	private CommandDescriptor descriptor = new CommandDescriptor();
	private AccountManager accountmanager = null;
	private String commandstring = "";
	
	public StockGameGUIProcessor(AccountManager manager) {
		accountmanager = manager;
	}

	@SuppressWarnings("static-access")
	public String process(String commandstring) {
		this.commandstring = commandstring;
		if (checkCommand() == false) {
			return "Invalid command";
		}
		StockGameCommandType commandType = (StockGameCommandType) descriptor
				.getCommandType();

		try {
			switch (commandType) {
			case EXIT:
				// shellwriter.println("System is about to close");
				// shellwriter.flush();
				System.exit(0);
				// break;
			case HELP:
				String sout = "";
				for (int i = 0; i < StockGameCommandType.values().length; i++) {
					sout += StockGameCommandType.values()[i].getName()+StockGameCommandType.values()[i].getHelpText()+"\r\n"; 
					// shellwriter.println(StockGameCommandType.values()[i].getName()+StockGameCommandType.values()[i].getHelpText());
				}
				return sout;
				// shellwriter.flush();
				
			default:
				try {
					if (descriptor.getParams()[0].equals("help")) {
						// shellwriter.println(commandType.getHelpText());
						// shellwriter.flush();
						if (commandType == commandType.HISTORY) {
							String s = "<possible parameter for history>";
							for (int i = 0; i < HistorySortType.values().length; i++) {
								s += " "
										+ HistorySortType.values()[i]
												.getMethodName() + ",";
							}
							// shellwriter.println(s);
							// shellwriter.flush();
							return s;
						}
					} else {
						Method executemethod = accountmanager.getClass()
								.getMethod(commandType.getImplMethods(),
										commandType.getParamTypes());
						executemethod.invoke(accountmanager,
								descriptor.getParams());
					}
				} catch (Exception e) {
					// shellwriter.println("Action :"+commandType.getImplMethods()+" could not be done");
					// shellwriter.flush();
					return "Action :"+commandType.getImplMethods()+" could not be done";
				}
				break;
			}
		} catch (WrongNameException e) {
			// shellwriter.println("Invalid Playername !");
			// shellwriter.flush();
			return "Invalid Playername!";
		}
		return "";

	}

	private boolean checkCommand() {
		try {
			CommandScanner commandscanner = new CommandScanner(
					StockGameCommandType.values(), descriptor);
			BufferedReader reader = new BufferedReader(new StringReader(commandstring));
			commandscanner.checkCommandSyntax(reader);
			return true;
		} catch (WrongCommandException e) {
			// shellwriter.println("Invalid Command");
			// shellwriter.flush();
			return false;
		} catch (IOException e) {
			// shellwriter.println("IO Exception failure !");
			// shellwriter.flush();
			return false;
		}
	}
}
