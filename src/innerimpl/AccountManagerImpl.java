package innerimpl;
import java.io.IOException;
import java.util.logging.Logger;

import bots.StockBuySellBot;
import priceprovider.*;
import asset.Asset;
import asset.Player;
import asset.Share;
import asset.ShareItem;
import Exception.AccountException;
import Exception.BotException;
import Exception.NotAddablePlayerException;
import Exception.ShareException;
import Exception.WrongNameException;
/**
 * 
 * @author daniel und manuel
 *
 */
public class AccountManagerImpl implements AccountManager {
	private Player[] allplayers;
	private StockPriceProvider provider;
	private boolean diverstatus = false; 
	private final static Logger logger = Logger.getLogger(AccountManagerImpl.class.getName());
	/**
	 * 
	 * @param provider
	 */
	public AccountManagerImpl(StockPriceProvider provider) {
		allplayers = new Player[1];
		this.provider = provider;
		
		try {
            logger.addHandler(new java.util.logging.FileHandler());
//            logger.addHandler(new java.util.logging.ConsoleHandler());
        } catch (SecurityException e) {
            logger.warning(e.toString());
        } catch (IOException e) {
            logger.warning(e.toString());
        }
	}
	/**
	 * 
	 */
	@Override
	public void setPlayerAccount(long accountworth, String playername) throws AccountException{
		Player player = searchInPlayer(playername);
		player.setAccountWorth(accountworth);
	}
	/**
	 * 
	 */
	public void addPlayer(Object player) throws NotAddablePlayerException {
		Player newplayer = null;
		if (player.getClass().getName().equals("java.lang.String")) {
			newplayer = new Player((String) player);
		} else {
			newplayer = (Player) player;
		}
		for (int i = 0; i < allplayers.length; i++) {
			if (allplayers[i] == null) {
				try {
					Player searchplayer = searchInPlayer(newplayer.name);
					if(searchplayer != null){
					   NotAddablePlayerException addable = new NotAddablePlayerException("player still exists");
					   logger.warning(addable.toString()+"Player still exists so can't be added");
					   throw addable;
					}
				} catch (WrongNameException e) {
					logger.info(e.toString()+ "player does not exist now he will be added");
					allplayers[i] = (newplayer);
					return;
				}				
			}
			// longer the array if no space free
			else if (i == allplayers.length - 1) {
				allplayers = longerArray(allplayers, 1);
				i = 0;
			}
		}
	}
	/**
	 * verlaengert ein zurueckgegebenes Array
	 * @param playerarray
	 * @param howmuchlonger
	 * @return
	 */
	private Player[] longerArray(Player[] playerarray, int howmuchlonger){
		Player[] longer = new Player[playerarray.length + howmuchlonger];
		for (int j = 0; j < playerarray.length; j++) {
			longer[j] = playerarray[j];
		}
		return longer;
	}
	/**
	 * 
	 */
	public void buyShare(String playername, String sharename, int amount)
			throws ShareException, AccountException {
		// search for the player called playername
		Player searchplayer = searchInPlayer(playername);

		// search for the share Sharename
		Share searchshare = provider.getShare(sharename);

		// what happens if price is higher than account status
		if (((searchshare.getActualSharePrice() * amount)) > (searchplayer.getCashAccount().getvalue())) {
			ShareException shareexecption = new ShareException("price is too high for cash account");
			logger.warning(sharename.toString()+"can't be sold price to high");
			throw shareexecption;
		}else{
			// finally buy the Share
			searchplayer.buyShare(searchshare, amount);
		}


	}
	/**
	 * sellShare(String playername, String sharename, int amount)
	 * Verkauft eine bestimmte Anzahl von Aktien
	 * wirft einen ShareException wenn nicht genug Aktion vorhanden sind
	 */
	public void sellShare(String playername, String sharename, int amount)
			throws ShareException, AccountException {
		// search for the player called playername
		Player searchplayer = searchInPlayer(playername);

		// search for the share Sharename
		Share searchshare = provider.getShare(sharename);

		// finally sell the Share
		searchplayer.sellShare(searchshare, amount);

	}
	
	/**
	 * getAssetworth(Asset asset)
	 * Gibt den Wert einen gewüschten Assets zurück
	 * @param asset
	 */
	
	public long getAssetworth(Asset asset) {
		return asset.getvalue();
	}

	/**
	 * getAllAssetworth(String playername) 
	 * Gibt das gesamte Vermögen eines Spielers aus dabei werden die
	 * vorhanden Aktien und das Geldkonto mit einberechnet
	 * @param playername
	 * 
	 */
	public long getAllAssetworth(String playername) {
		Player player = searchInPlayer(playername);
		long accumulateworth = player.getCashAccount().getvalue();
		for (int j = 0; j < player.getShareDeposit().getAllShareItems().length; j++) {
			try {
				String nameofshare = player.getShareDeposit().getAllShareItems()[j].name;
				int numberofshares = player.getShareDeposit().getAllShareItems()[j].getNumberOfShares();
				Share searchshare = provider.getShare(nameofshare);
				accumulateworth += searchshare.getActualSharePrice() * numberofshares;
			} catch (Exception e) {
				logger.warning(e.toString() + "last worth of j:"+j);
				break;
			}
			
		}
		return accumulateworth;
	}
	/**
	 * sucht nach einem beliebigen Spieler anahand des namens
	 * @param searchstring
	 * @return Player
	 * @throws WrongNameException
	 */
	private Player searchInPlayer(String searchstring)
			throws WrongNameException {
		int i;
		for (i = 0; i < allplayers.length; i++) {
			if (allplayers[i] != null && allplayers[i].name.equalsIgnoreCase(searchstring)) {
				break;
			} else if (i == allplayers.length - 1) {
				// if player cant be found throw exception
				WrongNameException nameexception = new WrongNameException("playername could not been found");
				logger.warning(nameexception.toString());
				throw nameexception;
			}
		}
		return allplayers[i];
	}
	
	/** gibt alle Player zurück
	 * getAllAssetworth(String playername) 
	 * Gibt alle Spieler des Managers zurück
	 * @return Player[]
	 */
	public Player[] getAllPlayer (){
	    return allplayers;
	}
	
	/**
	 *  String getPlayer() 
	 *	gibt alle Player eines Managers als String zurück
	 *	Form: Playername: <name> <cashaccount to String Methode> <sharedeposit to String>
	 *	@return String
	 */
	
	public String getPlayer() {
        String s = "";
        for (int i = 0; i < allplayers.length; i++){
            if (allplayers[i] != null){
                s += "Player name: " + allplayers[i].name +  allplayers[i].getCashAccount().toString() + "\n\r";
                s += allplayers[i].getShareDeposit().toString() +  "\n\r";
            }
        }
        return s;
    }
	/**
	 * diverShareSell(String sharename , String playername)
	 * Vergleicht den Durchscnittseinkaufwert mit dem aktuellen Wert der Aktie und liefert true zurück, wenn der 
	 * Durchscnittswert >= dem aktuellen Wert ist. 
	 * @param sharename
	 * @param playername
	 * @return boolean
	 * @throws WrongNameException
	 */
	@Override
	public boolean diverShareSell(String sharename , String playername) throws WrongNameException{
		ShareItem[] buffershareitem = searchInPlayer(playername).getShareDeposit().getAllShareItems();
		long pricepershare = setPricepershare(sharename, buffershareitem);	
		if(pricepershare == 0 || pricepershare == -1)
		    return diverstatus = true;
		pricepershare -= provider.getShare(sharename).getActualSharePrice();
		if(pricepershare >= 0){
			diverstatus = true;
		}else{
			diverstatus = false;
		}			
		return diverstatus;
	}
	/**
	 * setPricepershare (String sharename, ShareItem[] buffershareitem)
	 * Durchsucht ein ShareItem[] mithilfe von sharename nach dem passenden und liefert den Durchschnittlichen Einkaufswert
	 * zurück.
	 * @param sharename
	 * @param buffershareitem
	 * @return long
	 */
	private long setPricepershare (String sharename, ShareItem[] buffershareitem){
	    long pricepershare = -1;
	    for (int i = 0; i < buffershareitem.length; i++) {
			if(buffershareitem[i] !=null && buffershareitem[i].name.equals(sharename) && buffershareitem[i].getNumberOfShares() != 0){
				pricepershare = buffershareitem[i].getPurchasValue() / buffershareitem[i].getNumberOfShares();
				if(i == buffershareitem.length){
					break;
				}
			}
			if(i == buffershareitem.length){
				WrongNameException nameexception = new WrongNameException("invalid name of share");
				logger.warning(nameexception.toString());
				throw nameexception;
			}
		}
	    return pricepershare;
	}
	/**
	 * gibt den diverstatus zurück
	 */
	@Override
	public boolean getDiverStatus() {
		return diverstatus;
	}
	/**
	 * startet den Bot
	 */
	@Override
	public void startBot(String playername) throws BotException {
		StockBuySellBot startbot = (StockBuySellBot) searchInPlayer("Bot");
		try {
			startbot.start(playername);
		} catch (BotException e) {
			logger.warning(e.toString());
		}

	}
	/**
	 * stopt den Bot
	 */
	@Override
	public void stopBot(String playername) throws BotException {
		StockBuySellBot endbot = (StockBuySellBot) searchInPlayer("Bot");
		try {
			endbot.stop(playername);
		} catch (BotException e) {
			logger.warning(e.toString());
		}
	}
}
