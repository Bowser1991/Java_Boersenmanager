package launcher;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.logging.LogManager;

import enums.Messages;

import bots.Bot;
import bots.StockBuySellBot;
import innerimpl.AccountManager;
import innerimpl.AccountManagerImpl;
import priceprovider.RandomStockPriceProvider;
import priceprovider.StockPriceInfo;
import priceprovider.StockPriceProvider;
import priceprovider.StockPriceViewer;
import priceprovider.YahooFinancePriceProvider;
import proxy.AccountManagerHandler;
import Command.StockGameCommandProcessor;

public class StockGameLauncher {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    
	    //unter Run -> Run Configurations -> unter Arguments: en für Englische Sprache eingeben oder nichts für Deutsch
		if(args.length != 0){
		    Messages.setresourceBoundle(args[0]);
		} else {
		    Messages.setresourceBoundle("");
		}
        try {
        	FileInputStream configFile = new FileInputStream("logging.properties");
			LogManager.getLogManager().readConfiguration(configFile);
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        StockPriceProvider provider;
        try {
			provider = new YahooFinancePriceProvider();
		} catch (Exception e) {
			provider = new RandomStockPriceProvider();
		}
		
		AccountManager manager = new AccountManagerImpl(provider);
		AccountManager proxy = (AccountManager)Proxy.newProxyInstance(AccountManager.class.getClassLoader(), new Class [] {AccountManager.class}, new AccountManagerHandler(manager));
		Bot bot1 = new StockBuySellBot(	proxy, provider);
		proxy.addPlayer(bot1);
		StockGameCommandProcessor commandprocessor = new StockGameCommandProcessor(proxy);
		StockPriceInfo priceinfo = new RandomStockPriceProvider();
		StockPriceViewer priceviewer = new StockPriceViewer(priceinfo, proxy);
		priceviewer.start();
		commandprocessor.process();
		
		
		
		
		
	}

}
