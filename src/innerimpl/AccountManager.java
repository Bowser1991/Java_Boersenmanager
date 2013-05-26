package innerimpl;

import asset.Asset;
import asset.Player;
import Exception.AccountException;
import Exception.BotException;
import Exception.HistoryException;
import Exception.ShareException;
import Exception.WrongCommandException;
import Exception.WrongNameException;
/**
 * 
 * @author daniel und manuel
 *
 */
public interface AccountManager {
	/**
	 * addPlayer(Object player) 
	 * fuegt einen neuen Spieler hinzu
	 * @param player
	 * entweder als String Spielername oder gleich als Object Spieler
	 */
     public void addPlayer(Object player);
     /**
      * buyShare(String playername, String sharename, int amount)
      * kauft eine Aktie anhand Spielername Aktiename und Anzahl
      * @param playername
      * Name des Spielers
      * @param sharename
      * Name der Aktie
      * @param amount
      * Anzahl der zu kaufendne Aktien
      * @throws ShareException
      * @throws AccountException 
      */
     public void buyShare(String playername, String sharename, int amount) throws ShareException, AccountException;
     /**
      * sellShare(String playername, String sharename, int amount)
      * verkauft eine Aktie anhand Spielername Aktiename und Anzahl
      * @param playername
      * Name des Spielers
      * @param sharename
      * Name der Aktie
      * @param amount
      * Anzahl der zu verkaufenden Aktien
      * @throws ShareException
     * @throws AccountException 
      */
     public void sellShare(String playername, String sharename, int amount) throws ShareException, AccountException;
     /**
      * getAssetworth(Asset asset)
      * gibt den Gesamtwert eines Assets zurück
      * @param asset
      * Das zu übergenbende Asset
      * @return
      * Wert als Cent Betrag
      */
     public long getAssetworth(Asset asset);
     /**
      * getAllAssetworth(String playername)
      * gibt den Gesamwert aller Konnten, Aktiendepots usw. anhand des Spielernamen zurueck
      * @param playername
      * Name des Spielers
      * @return
      * Gesamtwert in Cent
      */
     public long getAllAssetworth(String playername);
     /**
      * Player[] getAllPlayer ()
      * gibt alle Spieler in einem Array zurueck
      * @return
      * alle Spieler in einem Accountmanager
      */
     public Player[] getAllPlayer ();
     /**
      * setPlayerAccount(long accountworth, String playername)
      * setzt einen Konto wert fuer einen Spieler
      * @param accountworth
      * Neuer Wert des Kontos
      * @param playername
      * Name des Spielers
      * @throws AccountException 
      * wirft eine Exception wenn ein wert kleiner 0 verwendet wird
      */
     public void setPlayerAccount(long accountworth, String playername) throws AccountException;
     /**
 	 * diverShareSell(String sharename , String playername)
 	 * Nimmt den Gesamtwert eines Share Items und teilt diesen durch die
 	 * anzahl der im ShareItem liegenden Shares. Dieser Wert wird dann mit
 	 * dem aktuellen wert der Firma verglichen und als long zurück gegeben.
 	 * @param sharename
 	 * Name der Aktie
 	 * @param playername
 	 * Name des Spielers
 	 * @return boolean
 	 * Ist der Verkauf rentabel
 	 * @throws WrongNameException
 	 * wenn der Spieler nicht gefunden werden kann
 	 */
 	 public boolean diverShareSell(String sharename , String playername) throws WrongNameException;
 	 /**
 	  * getDiverStatus()
 	  * gibt den Status des letzten Vergleichs zurück
 	  * @return boolean
 	  * Vergleichsstatus 
 	  */
 	 public boolean getDiverStatus();
 	 /**
 	  * startBot(String playername)
 	  * startet den Bot anahand des Spielername
 	  * @param playername
 	  * Name des Spielers
 	  * @throws BotException
 	  * wenn bereits ein Bot läuft
 	  */
 	 public void startBot(String playername) throws BotException;
 	 /**
 	  * stopBot(String playername)
 	  * @param playername
 	  * Name des Spielers
 	  * @throws BotException
 	  * wenn bisher noch kein Bot läuft
 	  */
	 public void stopBot(String playername) throws BotException;
	 /**
	  * 
	  * @param playerName
	  * @param param
	  * @return
	  * @throws WrongCommandException
	  */
	 public String getSortedHistory(String playerName, String sharename, String mimetype) throws HistoryException;
	 /**
	  * 
	  * @param playerName
	  * @param sharename
	  * @return
	  * @throws WrongCommandException
	  */
	 public String getShareHistory(String playerName, String sharename) throws HistoryException;
}

