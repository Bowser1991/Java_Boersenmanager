package launcher;

import bots.Bot;
import bots.StockBuySellBot;
import innerimpl.AccountManager;
import innerimpl.AccountManagerImpl;
import priceprovider.RandomStockPriceProvider;
import priceprovider.StockPriceInfo;
import priceprovider.StockPriceProvider;
import priceprovider.StockPriceViewer;
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
		Bot bot1 = new StockBuySellBot(manager, provider);
		manager.addPlayer(bot1);
		StockGameCommandProcessor commandprocessor = new StockGameCommandProcessor(manager);
		StockPriceInfo priceinfo = new RandomStockPriceProvider(sharearray1);
		StockPriceViewer priceviewer = new StockPriceViewer(priceinfo, manager);
		priceviewer.start();
		commandprocessor.process();
		
		
		
		
		
	}

}
