package launcher;

import java.lang.reflect.Proxy;

import bots.Bot;
import bots.StockBuySellBot;
import innerimpl.AccountManager;
import innerimpl.AccountManagerImpl;
import priceprovider.RandomStockPriceProvider;
import priceprovider.StockPriceInfo;
import priceprovider.StockPriceProvider;
import priceprovider.StockPriceViewer;
import proxy.AccountManagerHandler;
import asset.Share;
import Command.StockGameCommandProcessor;

public class StockGameLauncher {
	static Share share1 = new Share("Merzedes", 14000l);
	static Share share2 = new Share("BMW", 19436l);
	static Share share3 = new Share("Opel", 28900l);
	static Share[] sharearray1 = { share1, share2, share3 };
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StockPriceProvider provider = new RandomStockPriceProvider(sharearray1);
		AccountManager manager = new AccountManagerImpl(provider);
		AccountManager proxy = (AccountManager)Proxy.newProxyInstance(AccountManager.class.getClassLoader(), new Class [] {AccountManager.class}, new AccountManagerHandler(manager));
		Bot bot1 = new StockBuySellBot(	proxy, provider);
		proxy.addPlayer(bot1);
		StockGameCommandProcessor commandprocessor = new StockGameCommandProcessor(proxy);
		StockPriceInfo priceinfo = new RandomStockPriceProvider(sharearray1);
		StockPriceViewer priceviewer = new StockPriceViewer(priceinfo, proxy);
		priceviewer.start();
		commandprocessor.process();
		
		
		
		
		
	}

}
