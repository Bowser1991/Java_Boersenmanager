package bots;

import java.util.TimerTask;

import asset.Share;

import Exception.BotException;
import Exception.ShareException;
import Exception.WrongNameException;
import innerimpl.AccountManager;
import priceprovider.StockPriceProvider;
import priceprovider.GlobalTimer;

public class StockBuySellBot implements Bot {
	

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
			GlobalTimer.getTimer().addTask(task);	}
	
	private Share getMinPrice(Share[] searcharray){
		Share priceminvalue = searcharray[0];
		for (int i = 1; i < searcharray.length; i++) {
			for (int j = 0; j < searcharray[i].getSharePriceHistory().length; j++) {
				if(searcharray[i].getSharePriceHistory()[j] <= priceminvalue.getActualSharePrice());
				priceminvalue = searcharray[i];
			}
		}
		return priceminvalue;
	}
	
	public void doAction(){
		for (int i = 0; i < provider.getAvailableShare().length; i++) {
			try {
			
				Share priceminvalue = getMinPrice(provider.getAvailableShare());
				if(accountmanager.diverShareSell(priceminvalue.name, playerbotname)){
					accountmanager.buyShare(playerbotname, provider.getAvailableShare()[i].name, 1);
				}
			}catch (ShareException e){
				return;
			}catch (Exception e) {

			}
			
			try{
				if(accountmanager.diverShareSell(provider.getAvailableShare()[i].name, playerbotname)){
					accountmanager.sellShare(playerbotname, provider.getAvailableShare()[i].name, 1);
				}
				}catch(Exception e){}
		}
		
		
	}
	@Override
	public void stop(String playername) throws BotException {
			task.cancel();
		
	}
	class MyTask extends TimerTask {
        @Override
        public void run() {
            StockBuySellBot.this.doAction();
        }
    }
}
