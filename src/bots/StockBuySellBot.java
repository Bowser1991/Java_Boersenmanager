package bots;

import java.util.TimerTask;

import Exception.BotException;
import Exception.ShareException;
import Exception.WrongNameException;
import innerimpl.AccountManager;
import priceprovider.StockPriceProvider;
import priceprovider.GlobalTimer;

public class StockBuySellBot implements Bot {
	
	private GlobalTimer ticker = null;
	private AccountManager accountmanager = null;
	private String playerbotname = null;
	private StockPriceProvider provider = null;
	private MyTask task = null;
	
	public StockBuySellBot(AccountManager manager, StockPriceProvider priceinfo){
		accountmanager = manager;
		provider = priceinfo;
	}
	
	@Override
	public void start(String playername) throws BotException {
		playerbotname = playername;
			MyTask task = new MyTask();
			GlobalTimer.getTimer().addTask(task);
	}
	
	public void doAction(){
		for (int i = 0; i < provider.getAvailableShare().length; i++) {
			try {
				long[] pricehistory = provider.getAvailableShare()[i].getSharePriceHistory();
				long priceminvalue = pricehistory[0];
				for (int j = 1; j < pricehistory.length; j++) {
					if(priceminvalue > pricehistory[j]){
						priceminvalue = pricehistory[j];
					}
				}
				if(priceminvalue >= provider.getAvailableShare()[i].getActualSharePrice()){
					accountmanager.buyShare(playerbotname, provider.getAvailableShare()[i].name, 10);
				}
			} catch (Exception e) {

			}
			
			try{
				if(accountmanager.diverShareSell(provider.getAvailableShare()[i].name, playerbotname)){
					accountmanager.sellShare(playerbotname, provider.getAvailableShare()[i].name, 10);
				}
				}catch(Exception e){}
		}
		
		
	}
	@Override
	public void stop(String playername) throws BotException {
		if(!(task.equals(null))){
			task.cancel();
		}else{
			throw new BotException("Bot is not running");
		}
		
	}
	class MyTask extends TimerTask {
        @Override
        public void run() {
            StockBuySellBot.this.doAction();
        }
    }
}
