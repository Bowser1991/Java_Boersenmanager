package Command;

import innerimpl.AccountManager;
import innerimpl.AccountManagerImpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import asset.Share;
import Exception.ShareException;
import Exception.WrongCommandException;
import Exception.WrongNameException;
import enums.*;

public class StockGameCommandProcessor {
	private static BufferedReader shellreader = new BufferedReader(
			new InputStreamReader(System.in));
	private static PrintWriter shellwriter = new PrintWriter(System.out);
	private static CommandDescriptor descriptor = new CommandDescriptor();

	static Share share1 = new Share("Merzedes", 14000l);
	static Share share2 = new Share("BMW", 19436l);
	static Share share3 = new Share("Opel", 28900l);
	static Share[] sharearray1 = { share1, share2, share3 };

	private static AccountManager accountmanager = new AccountManagerImpl(
			sharearray1);

	public static void process() throws WrongCommandException, IOException {
		CommandScanner commandscanner = new CommandScanner(
				StockGameCommandType.values(), descriptor);
		
		while (true) { 
			
			boolean flag = true;
			while(flag){
				try{
					commandscanner.checkCommandSyntax(shellreader);
					flag = false;
					}catch(WrongCommandException e){
						shellwriter.println("Invalid Command !");
						shellwriter.flush();
					}	
			}
			
			
			Object[] parameter = descriptor.getParams();
			StockGameCommandType commandType = (StockGameCommandType) descriptor
					.getCommandType();

			try {
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
					accountmanager.addPlayer((String) parameter[0]);
					String out = "";
					for (int i = 0; i < accountmanager.getAllPlayer().length; i++) {
						out += accountmanager.getAllPlayer()[i].name + ", ";
					}
					shellwriter.println(out);
					shellwriter.flush();
					break;
				case ACCOUNTWORTH:
					shellwriter.println("Spieler:"
							+ (String) parameter[0]
							+ " AccountWorth: "
							+ accountmanager
									.getAllAssetworth((String) parameter[0]));
					shellwriter.flush();
					break;
				case BUYSHARE:
					try {
						accountmanager.buyShare((String) parameter[0],
								(String) parameter[1], (int) parameter[2]);
					} catch (ShareException e) {
						shellwriter
								.println("buy could not be done, amount is too high or not enough money");
						shellwriter.flush();
						break;
					}
					break;
				case SELLSHARE:
					try {
						accountmanager.sellShare((String) parameter[0],
								(String) parameter[1], (int) parameter[2]);
					} catch (ShareException e) {
						shellwriter
								.println("buy could not be done, amount is too low");
						shellwriter.flush();
						break;
					}
					break;
				case SETACCOUNTWORTH:
					accountmanager.setPlayerAccount((long) parameter[0],
							(String) parameter[1]);
					shellwriter.println("Spieler:"
							+ (String) parameter[1]
							+ " AccountWorth: "
							+ accountmanager
									.getAllAssetworth((String) parameter[1]));
					shellwriter.flush();
					break;
				default:
					break;
				}
			} catch (WrongNameException e) {
				shellwriter.println("Invalid Playername !");
				shellwriter.flush();
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
