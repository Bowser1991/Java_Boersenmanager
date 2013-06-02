package asset;
import history.BuySellHistory;
import Exception.AccountException;
import Exception.ShareException;
/**
 * 
 * @author daniel manuel
 *
 */
public class Player {
	/**
	 * name
	 * Name des Player Objekts
	 */
    public final String name;
    private CashAccount account;
    private ShareDeposit deposit;
    private BuySellHistory history = new BuySellHistory();
    /**
     * Player(String name).
     * Konstruktor erstellt Objekt mit Name
     * @param name
     * Name des neuen Objekts
     */
    public Player(String name){
        this.name = name;
        account = new CashAccount(name, 0l);
        deposit = new ShareDeposit(name);
    }
    /**
     * Player(String name, long cashaccountstatus).
     * Konstruktor erstellt Player mit Name und Kontostand
     * @param name
     * Name des Spielers
     * @param cashaccountstatus
     * Kontostand des Spielers
     */
    public Player(String name, long cashaccountstatus){
        this.name = name;
        this.account = new CashAccount(name, cashaccountstatus);
        deposit = new ShareDeposit(name);
    }
    /**
     * CashAccount getCashAccount()
     * @return CashAccount
     * Gibt das Konto Objekt des Spielers zurück
     */
    public CashAccount getCashAccount(){
        return account;
    }
    /**
     * buyShare(Share share, int amount)
     * Kauft eine beliebig Anzahl von Aktien
     * @param share
     * Zu kaufendes Share Objekt
     * @param amount
     * Anazahl der zu kaufenden Aktien
     * @throws AccountException
     * Wird geworfen wenn gültiger Kontostand verwendet wird
     */
    public void buyShare(Share share, int amount) throws AccountException{
        deposit.buyShare(share, amount);
        account.setAccountStatus((account.getvalue() - (share.getActualSharePrice()*amount)));
    }
    /**
     * sellShare(Share share, int amount).
     * Verkauft eine beliebige Anzahl Aktien
     * @param share
     * Zu verkaufendes Share Objekt
     * @param amount
     * Anazahl der Aktien
     * @throws ShareException
     * Wird geworfen wenn es keine Aktie von diesem Typ gibt
     * @throws AccountException
     * Wird geworfen wenn kein gültiger Kontostand verwendet wird
     */
    public void sellShare(Share share, int amount) throws ShareException, AccountException{
        deposit.sellShare(share, amount);
        account.setAccountStatus((account.getvalue() + (share.getActualSharePrice()*amount)));
    }
    /**
     * setAccountWorth(long newworth).
     * Setzt einen neuen Kontostand
     * @param newworth
     * Neuer Kontostand
     * @throws AccountException
     * Wird geworfen wenn kein gültiger Kontostand verwendet wird
     */
    public void setAccountWorth(long newworth) throws AccountException{
    	this.account.setAccountStatus(newworth);
    }
    /**
     * ShareDeposit getShareDeposit().
     * Gibt das AktienDepot eines Spielers zurück
     * @return ShareDeposit
     * Gibt das ShareDeposit Objekt zurück
     */
    public ShareDeposit getShareDeposit(){
        return deposit;
    }
    /**
     * boolean equals(Player player).
     * vergleicht den aktuellen Player mit einem anderen
     * @param player
     * zu vergleichender Spieler
     * @return boolean
     * Gibt einen Boolean zurück ob die Spieler gleich sind
     */
    public boolean equals(Player player) {
        boolean b = true;
        if (!this.name.equals(player.name)){
            b = false;
            return b;
        }
        if (!this.deposit.equals(player.getShareDeposit())){
            b = false;
            return b;
        }
        if (!this.account.equals(player.getCashAccount()))
            b = false;
        return b;
    }
    /**
     * BuySellHistory getBuySellHistory().
     * @return BuySellHistory
     * gibt die BuySell History zurück
     * 
     */
    public BuySellHistory getBuySellHistory(){
        return history;
    }
    /**
     * String toString()
     * Ueberschreibt die toSring() Methode von Asset
     * @return String
     * To String im Format 
     * "Player name: " + name + "<br>" +account.toString() + "<br>" + deposit.toString() + "<br>"
     */
    @Override
    public String toString(){
        return "Player name: " + name + "<br>" +account.toString() + "<br>" + deposit.toString() + "<br>";
    }
    
}
