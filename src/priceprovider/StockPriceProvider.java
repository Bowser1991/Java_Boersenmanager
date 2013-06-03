package priceprovider;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;

import asset.Share;
import Exception.WrongNameException;
import Network.StockApi;


public abstract class StockPriceProvider implements StockPriceInfo {
    
    protected List <Share> availableShare = new LinkedList<Share>();
    
    public StockPriceProvider() {
        Share[] buffershare = new Share[0];
        if(this instanceof YahooFinancePriceProvider){
            buffershare = createShares();
        }else if(this instanceof HistoricalStockPriceProvider){
            buffershare = createDefaulShares();
        }
    	
    	for (int i = 0; i < buffershare.length; i++) {
			availableShare.add(buffershare[i]);
		}
        Comparator<Share> comperator = new ShareComparator();
        Collections.sort(this.availableShare, comperator);
        startUpdate();
    }
    
    private Share[] createDefaulShares(){
        String[] sharenames = {"GOOG","AAPL","MSFT","BMW.DE","INTC","RWE.DE"};
        Share[] sharebuffer = new Share[sharenames.length];
        for(int i = 0; i < sharebuffer.length; i++){
            sharebuffer[i] = new Share(sharenames[i], 0);
        }
        return sharebuffer;
    }
    
    private Share[] createShares(){
    	String[] sharenames = {"GOOG","AAPL","MSFT","BMW.DE","INTC","RWE.DE"};
    	StockApi newstock = new StockApi();
    	try {
			return newstock.startRateUpdate(sharenames);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public boolean isShareListed(String sharename){
        return availableShare.contains(sharename);
    }

    public long getShareprice(String name){
        Share searchshare = getShare(name);
        return searchshare.getActualSharePrice();
    }
    public Share[] getAvailableShare(){
        return (Share[]) availableShare.toArray(new Share[availableShare.size()]);
    }
    public String getAvailableShares(){
        String s = "";
        for (int i = 0; i < availableShare.size(); i++) {
            s += availableShare.get(i);
        }
        return s;
    }
    
    public Share getShare(String searchstring)
            throws WrongNameException {
        if (isShareListed(searchstring)){
            throw new WrongNameException("playername could not been found");
        }
        int i;
        for (i = 0; i < availableShare.size(); i++) {
            if(availableShare.get(i).name.equals(searchstring)){
                break;
            }
        }
        return availableShare.get(i);
    }
    protected abstract void  updateShareRate (Share share);
    
    protected void  updateShareRates (){
        for (int i = 0; i < availableShare.size(); i++){
            if (availableShare.get(i) != null){
                updateShareRate(availableShare.get(i));
            }
        }
    }
    public void startUpdate(){
    	MyTask newtask = new MyTask();
    	GlobalTimer.getTimer().addTask(newtask);
    }
    class MyTask extends TimerTask {
        @Override
        public void run() {
				StockPriceProvider.this.updateShareRates();
        }
    }
}
