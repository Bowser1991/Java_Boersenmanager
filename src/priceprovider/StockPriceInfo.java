package priceprovider;
import asset.Share;


public interface StockPriceInfo {
    
//    boolean isShareListed (String sharename);
    long getShareprice(String name);
    Share [] getAvailableShare ();
    String getAvailableShares();
    
}
