import asset.Share;
import Exception.WrongNameException;


public abstract class StockPriceProvider implements StockPriceInfo {
    
private Share[] availableShare;
    
    public StockPriceProvider (Share [] availableShare){
        this.availableShare = availableShare;
        TickerTask timer = TickerTask.getInstance();
        timer.getProviderInstance(this);
    }

    public boolean isShareListed(String sharename)
    {
        boolean b = false;
        for (int i = 0; i < availableShare.length; i++) {
            if (sharename.equals(availableShare[i].name)) {
                b = true;
                return b;
            }
        }
        return b;
    }

    public long getShareprice(String name)
    {
        Share searchshare = getShare(name);
        return searchshare.getActualSharePrice();
    }
    public Share[] getAvailableShare()
    {
        return availableShare;
    }
    public String getAvailableShares()
    {
        String s = "";
        for (int i = 0; i < availableShare.length; i++) {
            if (availableShare[i] != null){
                s += availableShare[i].toString();
            }
        }
        return s;
    }
    
    protected Share getShare(String searchstring)
            throws WrongNameException {
        int i;
        for (i = 0; i < availableShare.length; i++) {
            if (availableShare[i].name.equals(searchstring)) {
                return availableShare[i];
            } else if (i == availableShare.length - 1) {
                // if player cant be found throw exception
                throw new WrongNameException("playername could not been found");
            }
        }
        return availableShare[i];
    }
    protected abstract void  updateShareRate (Share share);
    
    protected void  updateShareRates (){
        for (int i = 0; i < availableShare.length; i++){
            if (availableShare[i] != null){
                updateShareRate(availableShare[i]);
            }
        }
    }
    public void startUpdate(){
        updateShareRates();
    }

}
