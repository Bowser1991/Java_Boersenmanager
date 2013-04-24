
import asset.Asset;
import Exception.ShareException;
public interface AccountManager {
    
     void addPlayer(String name);
     void buyShare(String playername, String sharename, int amount) throws ShareException;
     void sellShare(String playername, String sharename, int amount) throws ShareException;
     long getAssetworth(Asset asset);
     long getAllAssetworth(String playername);
     Player[] getAllPlayer ();
//   long getShareprice(String name);
//   String getAvailableShares();
}

