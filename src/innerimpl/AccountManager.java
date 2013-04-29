package innerimpl;

import asset.Asset;
import asset.Player;
import Exception.ShareException;
public interface AccountManager {
    
	/**
	 * 
	 * @param name
	 */
     void addPlayer(String name);
     
     /**
      * 
      * @param playername
      * @param sharename
      * @param amount
      * @throws ShareException
      */
     void buyShare(String playername, String sharename, int amount) throws ShareException;
     /**
      * 
      * @param playername
      * @param sharename
      * @param amount
      * @throws ShareException
      */
     void sellShare(String playername, String sharename, int amount) throws ShareException;
     /**
      * 
      * @param asset
      * @return
      */
     long getAssetworth(Asset asset);
     /**
      * 
      * @param playername
      * @return
      */
     long getAllAssetworth(String playername);
     /**
      * 
      * @return
      */
     Player[] getAllPlayer ();
     /**
      * 
      * @param accountworth
      * @param playername
      */
     public void setPlayerAccount(long accountworth, String playername);

}

