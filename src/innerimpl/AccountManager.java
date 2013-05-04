package innerimpl;

import asset.Asset;
import asset.Player;
import Exception.AccountException;
import Exception.BotException;
import Exception.ShareException;
import Exception.WrongNameException;
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
     * @throws AccountException 
      */
     void buyShare(String playername, String sharename, int amount) throws ShareException, AccountException;
     /**
      * 
      * @param playername
      * @param sharename
      * @param amount
      * @throws ShareException
     * @throws AccountException 
      */
     void sellShare(String playername, String sharename, int amount) throws ShareException, AccountException;
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
     * @throws AccountException 
      */
     public void setPlayerAccount(long accountworth, String playername) throws AccountException;
     /**
 	 * diverShareSell(String sharename , String playername)
 	 * Nimmt den Gesamtwert eines Share Items und teilt diesen durch die
 	 * anzahl der im ShareItem liegenden Shares. Dieser Wert wird dann mit
 	 * dem aktuellen wert der Firma verglichen und als long zur�ck gegeben.
 	 * @param sharename
 	 * @param playername
 	 * @return long
 	 * @throws WrongNameException
 	 */
 	 public boolean diverShareSell(String sharename , String playername) throws WrongNameException;
 	 /**
 	  * 
 	  * @return
 	  */
 	 public boolean getDiverStatus();
 	 /**
 	  * 
 	  * @param playername
 	  * @throws BotException
 	  */
 	 public void startBot(String playername) throws BotException;
 	 /**
 	  * 
 	  * @param playername
 	  * @throws BotException
 	  */
	 public void stopBot(String playername) throws BotException;
}

