package innerimpl;
import priceprovider.*;
import asset.Asset;
import asset.Player;
import asset.Share;
import asset.ShareItem;
import Exception.NotAddablePlayerException;
import Exception.ShareException;
import Exception.WrongNameException;

public class AccountManagerImpl implements AccountManager {
	private Player[] allplayers;
	private StockPriceProvider provider;
	private boolean diverstatus = false;
	
	public AccountManagerImpl(Share[] shares) {
		allplayers = new Player[1];
		provider = new RandomStockPriceProvider(shares);
	}

	@Override
	public void setPlayerAccount(long accountworth, String playername){
		Player player = searchInPlayer(playername);
		player.setAccountWorth(accountworth);
	}

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
	 * Gibt den Wert einen gew�schten Assets zur�ck
	 * 
	 */
	
	public long getAssetworth(Asset asset) {
		return asset.getvalue();
	}

	/*
	 * getAllAssetworth(String playername) 
	 * Gibt das gesamte Verm�gen eines Spielers aus dabei werden die
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
	 * Gibt alle Spieler des Managers zur�ck
	 * 
	 */
	
	public Player[] getAllPlayer (){
	    return allplayers;
	}
	
	/*
	 *  String getPlayer() 
	 *	gibt alle Player eines Managers als String zur�ck
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
	 * Nimmt den Gesamtwert eines Share Items und teilt diesen durch die
	 * anzahl der im ShareItem liegenden Shares. Dieser Wert wird dann mit
	 * dem aktuellen wert der Firma verglichen und als long zur�ck gegeben.
	 * @param sharename
	 * @param playername
	 * @return long
	 * @throws WrongNameException
	 */
	
	@Override
	public boolean diverShareSell(String sharename , String playername) throws WrongNameException{
		ShareItem[] buffershareitem = searchInPlayer(playername).getShareDeposit().getAllShareItems();
		long pricepershare = 0l;
		for (int i = 0; i < buffershareitem.length; i++) {
			if(buffershareitem[i].name.equals(sharename)){
				pricepershare = buffershareitem[i].getPurchasValue() / buffershareitem[i].getNumberOfShares();
				break;
			}
			if(i == buffershareitem.length - 1){
				throw new WrongNameException();
			}
		}
		pricepershare -= provider.getShare(sharename).getActualSharePrice();
		if(pricepershare >= 0){
			diverstatus = true;
		}else{
			diverstatus = false;
		}
			
		return getDiverStatus();
	}

	@Override
	public boolean getDiverStatus() {
		return diverstatus;
	}

}
