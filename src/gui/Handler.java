package gui;

import innerimpl.AccountManager;
import innerimpl.AccountManagerImpl;

import java.lang.reflect.Proxy;

import priceprovider.HistoricalStockPriceProvider;
import priceprovider.RandomStockPriceProvider;
import priceprovider.StockPriceProvider;
import proxy.AccountManagerHandler;
import Command.StockGameGUIProcessor;
import bots.Bot;
import bots.StockBuySellBot;
import javafx.application.Application;
import javafx.stage.Stage;


public class Handler extends Application {
	private LaunchGuiNew gui = new LaunchGuiNew();
	private StockPriceProvider provider;
	private AccountManager manager ;
	private StockGameGUIProcessor commandprocessor;
	Thread thread;
	/**
	 * 
	 */
	@Override
	public void start(Stage primarystage) throws Exception {
		
		try {
		    provider = new HistoricalStockPriceProvider();
		} catch (Exception e) {
			provider = new RandomStockPriceProvider();
		}
		manager = new AccountManagerImpl(provider);
		AccountManager proxy = (AccountManager) Proxy.newProxyInstance(
				AccountManager.class.getClassLoader(),
				new Class[] { AccountManager.class },
				new AccountManagerHandler(manager));
		Bot bot1 = new StockBuySellBot(proxy, provider);
		proxy.addPlayer(bot1);
		commandprocessor = new StockGameGUIProcessor(proxy);
		gui.start(primarystage,proxy,commandprocessor);
		primarystage.show();
	}
}
