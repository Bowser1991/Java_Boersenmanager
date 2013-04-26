package Command;

import innerimpl.AccountManager;
import innerimpl.AccountManagerImpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import asset.Share;
import Exception.WrongCommandException;
import enums.*;

public class StockGameCommandProcessor {
	private static BufferedReader shellreader = new BufferedReader(new InputStreamReader(System.in));
	private static PrintWriter shellwriter = new PrintWriter(System.out);
	private static CommandDescriptor descriptor = new CommandDescriptor();

    static Share share1 = new Share("Merzedes", 14000l);   
    static Share share2 = new Share("BMW", 19436l);
    static Share share3 = new Share("Opel", 28900l);
    static Share[] sharearray1 = {share1, share2, share3};
   
	private static AccountManager accountmanager = new AccountManagerImpl(sharearray1);

	public static void process() throws WrongCommandException, IOException {
		CommandScanner commandscanner = new CommandScanner(StockGameCommandType.values(),descriptor);

		while (true) { // die Schleife über alle Kommandos, jeweils ein
						// Durchlauf pro Eingabezeile
			String s = shellreader.readLine();
			commandscanner.checkCommandSyntax(s);
			StockGameCommandType commandType = (StockGameCommandType) descriptor.getCommandType();

			switch (commandType) {
			case EXIT:
				shellwriter.println("System is about to close");
				shellwriter.flush();
				System.exit(0);
				break;
			case HELP:
				shellwriter.println(commandType.HELP.getHelpText());
				shellwriter.flush();
				break;
			case CREATEPLAYER:
				accountmanager.addPlayer((String)descriptor.getParams()[0]);
				String out = "";
				for (int i = 0; i < accountmanager.getAllPlayer().length; i++) {
					out += accountmanager.getAllPlayer()[i].name+ ", ";
				}
				shellwriter.println(out);
				shellwriter.flush();
				break;
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
