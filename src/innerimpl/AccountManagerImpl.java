package innerimpl;
import priceprovider.*;
import asset.Asset;
import asset.Player;
import asset.Share;
import Exception.NotAddablePlayerException;
import Exception.ShareException;
import Exception.WrongNameException;

public class AccountManagerImpl implements AccountManager {
	private Player[] allplayers;
	private StockPriceProvider provider;

	public AccountManagerImpl(Share[] shares) {
		allplayers = new Player[1];
		provider = new RandomStockPriceProvider(shares);
	}

	/**
	 * 
	 * 	Die Methode setPlayerAccount setzt einen neuen Kontostand
	 *  Anhand des übergebenen Namen 
	 * 
	 */
	
	
	@Override
	public void setPlayerAccount(long accountworth, String playername){
		Player player = searchInPlayer(playername);
		player.setAccountWorth(accountworth);
	}

	/*
	 * 
	 * fügt einen neuen Spieler hinzu
	 */
	
	public void addPlayer(String name) throws NotAddablePlayerException {
		Player newplayer = new Player(name);
		// saves the player in an free space
		for (int i = 0; i < allplayers.length; i++) {
			if (allplayers[i] == null) {
				try{
				Player searchplayer = searchInPlayer(name);
				}catch(WrongNameException e){
					allplayers[i] = (newplayer);
					return;
				}
				throw new NotAddablePlayerException("player still exists");
			}
			// longer the array if no space free
			else if (i == allplayers.length - 1) {
				allplayers = longerArray(allplayers, 1);
				i = 0;
			}
		}
	}
	
	
	private Player[] longerArray(Player[] playerarray, int howmuchlonger){
		Player[] longer = new Player[playerarray.length + howmuchlonger];
		for (int j = 0; j < playerarray.length; j++) {
			longer[j] = playerarray[j];
		}
		return longer;
	}
	
	/*
	 * buyShare(String playername, String sharename, int amount)
	 * Fügt einen neuen Spielerhinzu und belastet dabei das Konto
	 * wirft eine ShareException wenn nicht genug Geld vorhanden ist
	 * 
	 * 
	 */

	public void buyShare(String playername, String sharename, int amount)
			throws ShareException {
		// search for the player called playername
		Player searchplayer = searchInPlayer(playername);

		// search for the share Sharename
		Share searchshare = provider.getShare(sharename);

		// what happens if price is higher than account status
		if ((searchshare.getActualSharePrice() * (long) amount) > (searchplayer
				.getCashAccount().getAccountStatus())) {
			throw new ShareException("price is too high for cash account");
		}

		// finally buy the Share
		searchplayer.buyShare(searchshare, amount);
	}

	
	/*
	 * sellShare(String playername, String sharename, int amount)
	 * Verkauft eine bestimmte Anzahl von Aktien
	 * wirft einen ShareException wenn nicht genug Aktion vorhanden sind
	 * 
	 * 
	 */
	
	public void sellShare(String playername, String sharename, int amount)
			throws ShareException {

		// search for the player called playername
		Player searchplayer = searchInPlayer(playername);

		// search for the share Sharename
		Share searchshare = provider.getShare(sharename);

		// finally sell the Share
		searchplayer.sellShare(searchshare, amount);

	}
	
	/*
	 * getAssetworth(Asset asset)
	 * Gibt den Wert einen gewüschten Assets zurück
	 * 
	 */
	
	public long getAssetworth(Asset asset) {
		return asset.getvalue();
	}

	/*
	 * getAllAssetworth(String playername) 
	 * Gibt das gesamte Vermögen eines Spielers aus dabei werden die
	 * vorhanden Aktien und das Geldkonto mit einberechnet
	 * 
	 * 
	 */
	
	public long getAllAssetworth(String playername) {
		Player player = searchInPlayer(playername);
		long accumulateworth = player.getCashAccount().getAccountStatus();
		for (int j = 0; j < player.getShareDeposit().getAllShareItems().length; j++) {
			try {
				String nameofshare = player.getShareDeposit().getAllShareItems()[j].name;
				int numberofshares = player.getShareDeposit().getAllShareItems()[j].getNumberOfShares();
				Share searchshare = provider.getShare(nameofshare);
				accumulateworth += searchshare.getActualSharePrice() * numberofshares;
			} catch (Exception e) {
				break;
			}
			
		}
		return accumulateworth;
	}
	
	
	private Player searchInPlayer(String searchstring)
			throws WrongNameException {
		int i;
		for (i = 0; i < allplayers.length; i++) {
			if (allplayers[i] != null && allplayers[i].name.equalsIgnoreCase(searchstring)) {
				break;
			} else if (i == allplayers.length - 1) {
				// if player cant be found throw exception
				throw new WrongNameException("playername could not been found");
			}
		}
		return allplayers[i];
	}
	
	/*
	 * getAllAssetworth(String playername) 
	 * Gibt alle Spieler des Managers zurück
	 * 
	 */
	
	public Player[] getAllPlayer (){
	    return allplayers;
	}
	
	/*
	 *  String getPlayer() 
	 *	gibt alle Player eines Managers als String zurück
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


}
