package priceprovider;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TimerTask;

import asset.Share;
import Exception.WrongNameException;


public abstract class StockPriceProvider implements StockPriceInfo {
    
//private Share[] availableShare;
private List <Share> availableShare;
    
    public StockPriceProvider (Share [] availableShare){
        this.availableShare = Arrays.asList(availableShare);
        Comparator <Share> comperator = new ShareComparator();
        Collections.sort(this.availableShare, comperator);
        startUpdate();
    }

    public boolean isShareListed(String sharename)
    {        
        return availableShare.contains(sharename);
        
        
        
        
//        for (int i = 0; i < availableShare.length; i++) {
//            if (sharename.equals(availableShare[i].name)) {
//                b = true;
//                return b;
//            }
//        }
//        return b;
    }

    public long getShareprice(String name)
    {
        Share searchshare = getShare(name);
        return searchshare.getActualSharePrice();
    }
    public Share[] getAvailableShare()
    {
        return (Share[]) availableShare.toArray(new Share[availableShare.size()]);
    }
    public String getAvailableShares()
    {
        String s = "";
        for (int i = 0; i < availableShare.size(); i++) {
            s += availableShare.get(i);
        }
        return s;
        
//        Share[] a = getAvailableShare();
//        
//        String s = "";
//        for (int i = 0; i < a.length; i++) {
//            if (a[i] != null){
//                s += a[i].toString();
//            }
//        }
//        return s;
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
        
//        int i;
//        for (i = 0; i < availableShare.length; i++) {
//            if (availableShare[i].name.equals(searchstring)) {
//                return availableShare[i];
//            } else if (i == availableShare.length - 1) {
//                // if player cant be found throw exception
//                throw new WrongNameException("playername could not been found");
//            }
//        }
//        return availableShare[i];
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
