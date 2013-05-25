package priceprovider;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;

import asset.Share;
import Exception.WrongNameException;


public abstract class StockPriceProvider implements StockPriceInfo {
    
    private List <Share> availableShare = new LinkedList<Share>();
    
    public StockPriceProvider(Share[] availableShare) {
        for (int i = 0; i < availableShare.length; i++) {
            this.availableShare.add(availableShare[i]);
        }
        Comparator<Share> comperator = new ShareComparator();
        Collections.sort(this.availableShare, comperator);
        startUpdate();
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
