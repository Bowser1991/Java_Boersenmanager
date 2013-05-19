package priceprovider;
import asset.Share;


public interface StockPriceInfo {
    
    long getShareprice(String name);
    Share [] getAvailableShare ();
    String getAvailableShares();
    
}
