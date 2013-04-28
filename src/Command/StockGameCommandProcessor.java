package Command;

import innerimpl.AccountManager;
import innerimpl.AccountManagerImpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import asset.Share;
import Exception.ShareException;
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
					}catch (IOException e) {
						shellwriter.println("IO Exception failure !");
						shellwriter.flush();
					}
				
			}
			
			
			Object[] parameter = descriptor.getParams();
			StockGameCommandType commandType = (StockGameCommandType) descriptor.getCommandType();
			Method[] actuallmethods = accountmanager.getClass().getDeclaredMethods();
			
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
//				case CREATEPLAYER:
//					accountmanager.addPlayer((String) parameter[0]);
//					String out = "";
//					for (int i = 0; i < accountmanager.getAllPlayer().length; i++) {
//						out += accountmanager.getAllPlayer()[i].name + ", ";
//					}
//					shellwriter.println(out);
//					shellwriter.flush();
//					break;
//				case ACCOUNTWORTH:
//					shellwriter.println("Spieler:"
//							+ (String) parameter[0]
//							+ " AccountWorth: "
//							+ accountmanager
//									.getAllAssetworth((String) parameter[0]));
//					shellwriter.flush();
//					break;
//				case BUYSHARE:
//					try {
//						accountmanager.buyShare((String) parameter[0],
//								(String) parameter[1], (int) parameter[2]);
//					} catch (ShareException e) {
//						shellwriter
//								.println("buy could not be done, amount is too high or not enough money");
//						shellwriter.flush();
//						break;
//					}
//					break;
//				case SELLSHARE:
//					try {
//						accountmanager.sellShare((String) parameter[0],
//								(String) parameter[1], (int) parameter[2]);
//					} catch (ShareException e) {
//						shellwriter
//								.println("buy could not be done, amount is too low");
//						shellwriter.flush();
//						break;
//					}
//					break;
//				case SETACCOUNTWORTH:
//					accountmanager.setPlayerAccount((long) parameter[0],
//							(String) parameter[1]);
//					shellwriter.println("Spieler:"
//							+ (String) parameter[1]
//							+ " AccountWorth: "
//							+ accountmanager
//									.getAllAssetworth((String) parameter[1]));
//					shellwriter.flush();
//					break;
				default:
					try{
						Method executemethod = accountmanager.getClass().getMethod(commandType.getImplMethods(), commandType.getParamTypes());
						executemethod.invoke(accountmanager, descriptor.getParams());
					}catch(NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
						e.printStackTrace();
					}
					break;
				}
			} catch (WrongNameException e) {
				shellwriter.println("Invalid Playername !");
				shellwriter.flush();
			}
			

			
			
		}
	}
}
