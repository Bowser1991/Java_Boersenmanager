package innerimpl;

import history.BuySellHistory;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Logger;

import commperator.ComparatorAllShareName;

import enums.HistorySortType;
import bots.StockBuySellBot;
import priceprovider.*;
import Writer.DataWriter;
import Writer.DataWriterHTML;
import asset.Asset;
import asset.Player;
import asset.Share;
import asset.ShareItem;
import Exception.AccountException;
import Exception.BotException;
import Exception.HistoryException;
import Exception.NotAddablePlayerException;
import Exception.ShareException;
import Exception.WrongNameException;

/**
 * 
 * @author daniel und manuel
 * 
 */
public class AccountManagerImpl implements AccountManager {
	private Map<String, Player> allplayers = new Hashtable<String, Player>();
	private StockPriceProvider provider;
	private boolean diverstatus = false;
	private final static Logger logger = Logger
			.getLogger(AccountManagerImpl.class.getName());

	/**
	 * 
	 * @param provider
	 */
	public AccountManagerImpl(StockPriceProvider provider) {
		this.provider = provider;

		try {
			logger.addHandler(new java.util.logging.FileHandler());
			// logger.addHandler(new java.util.logging.ConsoleHandler());
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
	public void setPlayerAccount(long accountworth, String playername)
			throws AccountException {
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
		if(allplayers.containsKey(newplayer.name)){
		    NotAddablePlayerException addable = new NotAddablePlayerException(
                    "player still exists");
            logger.warning(addable.toString()
                    + "Player still exists so can't be added");
            throw addable;
		} else {
		    allplayers.put(newplayer.name, newplayer);
		}
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
		if (((searchshare.getActualSharePrice() * amount)) > (searchplayer
				.getCashAccount().getvalue())) {
			ShareException shareexecption = new ShareException(
					"price is too high for cash account");
			logger.warning(sharename.toString() + "can't be sold price to high");
			throw shareexecption;
		} else {
			// finally buy the Share
			searchplayer.buyShare(searchshare, amount);
		}
		searchplayer.getBuySellHistory().addHistory("buy", searchshare, searchplayer, amount);

	}

	/**
	 * sellShare(String playername, String sharename, int amount) Verkauft eine
	 * bestimmte Anzahl von Aktien wirft einen ShareException wenn nicht genug
	 * Aktion vorhanden sind
	 */
	public void sellShare(String playername, String sharename, int amount)
			throws ShareException, AccountException {
		// search for the player called playername
		Player searchplayer = searchInPlayer(playername);

		// search for the share Sharename
		Share searchshare = provider.getShare(sharename);

		// finally sell the Share
		searchplayer.sellShare(searchshare, amount);
		
		searchplayer.getBuySellHistory().addHistory("sell", searchshare, searchplayer, amount);

	}

	/**
	 * getAssetworth(Asset asset) Gibt den Wert einen gewüschten Assets zurück
	 * 
	 * @param asset
	 */

	public long getAssetworth(Asset asset) {
		return asset.getvalue();
	}

	/**
	 * getAllAssetworth(String playername) Gibt das gesamte Vermögen eines
	 * Spielers aus dabei werden die vorhanden Aktien und das Geldkonto mit
	 * einberechnet
	 * 
	 * @param playername
	 * 
	 */
	public long getAllAssetworth(String playername) {
		Player player = searchInPlayer(playername);
		long accumulateworth = player.getCashAccount().getvalue();

		try {
			accumulateworth += searchInPlayer(playername).getShareDeposit()
					.getvalue();
		} catch (Exception e) {
			logger.warning(e.toString());
		}

		return accumulateworth;
	}

	/**
	 * sucht nach einem beliebigen Spieler anahand des namens
	 * 
	 * @param searchstring
	 * @return Player
	 * @throws WrongNameException
	 */
	private Player searchInPlayer(String searchstring)
			throws WrongNameException {
	    if(allplayers.containsKey(searchstring)){
	        return allplayers.get(searchstring);
	    } else {
	        WrongNameException nameexception = new WrongNameException("playername could not been found");
	        logger.warning(nameexception.toString());
	        throw nameexception;
	    }
	}

	/**
	 * gibt alle Player zurück getAllAssetworth(String playername) Gibt alle
	 * Spieler des Managers zurück
	 * 
	 * @return Player[]
	 */
	public Player[] getAllPlayer() {
	    int amount = allplayers.size();
	    Player[] p = new Player[amount];
	    int i = 0;
	    for(Map.Entry<String, Player> e : allplayers.entrySet()){
	        p[i] = e.getValue();
	        i++;
	    }
		return p;
	}

	/**
	 * String getPlayer() gibt alle Player eines Managers als String zurück
	 * Form: Playername: <name> <cashaccount to String Methode> <sharedeposit to
	 * String>
	 * 
	 * @return String
	 */

	public String getPlayer() {
		String s = "";
		for(Map.Entry<String, Player> e : allplayers.entrySet()){
		    s = s + "Player name: " + e.getKey() + e.getValue().getCashAccount().toString() + "\r\n" + e.getValue().getShareDeposit().toString() + "\r\n";
		}
		return s;
	}

	/**
	 * diverShareSell(String sharename , String playername) Vergleicht den
	 * Durchscnittseinkaufwert mit dem aktuellen Wert der Aktie und liefert true
	 * zurück, wenn der Durchscnittswert >= dem aktuellen Wert ist.
	 * 
	 * @param sharename
	 * @param playername
	 * @return boolean
	 * @throws WrongNameException
	 */
	@Override
	public boolean diverShareSell(String sharename, String playername)
			throws WrongNameException {
		ShareItem[] buffershareitem = searchInPlayer(playername).getShareDeposit().getAllShareItems();
		long pricepershare = setPricepershare(sharename, buffershareitem);
		if (pricepershare == 0 || pricepershare == -1)
			return diverstatus = true;
		pricepershare -= provider.getShare(sharename).getActualSharePrice();
		if (pricepershare >= 0) {
			diverstatus = true;
		} else {
			diverstatus = false;
		}
		return diverstatus;
	}
	/**
	 * setPricepershare (String sharename, ShareItem[] buffershareitem)
	 * Durchsucht ein ShareItem[] mithilfe von sharename nach dem passenden und
	 * liefert den Durchschnittlichen Einkaufswert zurück.
	 * 
	 * @param sharename
	 * @param buffershareitem
	 * @return long
	 */
	private long setPricepershare(String sharename, ShareItem[] buffershareitem) {
		long pricepershare = -1;
		for (int i = 0; i < buffershareitem.length; i++) {
			if (buffershareitem[i] != null && buffershareitem[i].name.equals(sharename)&& buffershareitem[i].getNumberOfShares() != 0) {
				pricepershare = buffershareitem[i].getPurchasValue()
						/ buffershareitem[i].getNumberOfShares();
				if (i == buffershareitem.length) {
					break;
				}
			}
			if (i == buffershareitem.length) {
				WrongNameException nameexception = new WrongNameException(
						"invalid name of share");
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
	/**
	 * getHistory(String playerName, String param)
	 * Liefert die History sortiert nach
	 *     -Aktienname
	 *     -Methodenname
	 *     -Aufrufzeit
	 * zurück.
	 * @throws HistoryException 
	 */
	public String getSortedHistory(String playerName, String param, String mimetype) throws HistoryException{
	    BuySellHistory history = searchInPlayer(playerName).getBuySellHistory();
	    try {
			history.sort(HistorySortType.getCompareType(param));
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	    if(mimetype.equalsIgnoreCase("html")){
		    DataWriter writer = new DataWriterHTML("history");
		    try {
				writer.printStringtoData(history);
			} catch (IOException e) {
				throw new HistoryException("failure in modul sorting history");
			}
	    }else if(mimetype.equalsIgnoreCase("plain")){
		    System.out.println(history.toString());
	    }
	    return history.toString();
	}
	/**
	 * @throws HistoryException 
	 * 
	 */
	public String getShareHistory(String playerName, String sharename) throws HistoryException{
	    BuySellHistory history = searchInPlayer(playerName).getBuySellHistory();
        String s = "";      
        try {
			history.sort(new ComparatorAllShareName());
		}  catch (InstantiationException | IllegalAccessException e) {
			throw new HistoryException("failure in modul sorting history");
		}
        String[] buffer = history.toString().split("\n");
        for (int i = 0; i < buffer.length; i++) {
            if(buffer[i].contains(sharename)){
                s = s + buffer[i] + "\n";
            }
        }
        System.out.println(s);
        return s;
	}
}
