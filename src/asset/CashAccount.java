package asset;

import Exception.AccountException;
/**
 * @author daniel und manuel
 *
 */
public class CashAccount extends Asset {
    /**
     * accountStatus.
     * Gibt den Kontostand eines Kontos an
     */
	private long accountStatus;
	/**
	 * CashAccount(String name,long firstaccountstatus).
	 * Legt ein neues Objekt an
	 * @param name
	 * Name des Kontos
	 * @param firstaccountstatus
	 * Erster Kontostand des Kontos
	 */
    public CashAccount(String name,long firstaccountstatus) {
        super(name);
        accountStatus = firstaccountstatus;
    }
    /**
     * deposit(Share newshare).
     * Kontostand erniedrigen beim Kauf einer Aktie
     * @param newshare
     * Aktie die gekauft werden soll
     */
    public void deposit(Share newshare) {
        accountStatus = accountStatus - newshare.getActualSharePrice();
    }   
    /**
     * String toString().
     * Ueberschreibt die ToString methode
     * @return String
     * Ausgabe " Accountstatus:"<Wert> in Cent
     */
    @Override
    public String toString(){
        return " Accountstatus: "+Long.toString(accountStatus);
    }
    /**
     * withdraw(Share newshare).
     * Verkauf einer Aktie
     * @param newshare
     * Zu verkaufende Aktie
     */
    public void withdraw(Share newshare) {
        accountStatus = accountStatus + newshare.getActualSharePrice();
    }
    /**
     * setAccountStatus(long newaccount).
     * Setzt einen neuen Kontostand
     * @param newaccount
     * Neuer Kontostand
     * @throws AccountException
     * Wird geworfen wenn Accountstatus zu klein ist
     */
    public void setAccountStatus (long newaccount) throws AccountException {
        if (newaccount >= 0) {
    	this.accountStatus = newaccount;
        }else{
        	throw new AccountException("Accountstatus cant be set new accountstatus < 0");
        }
    }
    /**
     * getvalue().
     * gibt den Aktuellen Kontostand zurück
     * @return Long
     * Aktueller Kontostand in Cent als long
     */
    public long getvalue() {
        return accountStatus;
    }
    /**
     * equals(CashAccount account).
     * Vergleicht das Aktuelle Objekt mit dem uebergebenen
     * @param account
     * Uebergebener Account
     * @return boolean
     * Boolean der angibt ob die Objekte gleiche Werte haben
     */
    public boolean equals(CashAccount account) {
        boolean b = true;
        if (!this.name.equals(account.name)){
            b = false;
            return b;
        }      
        if (this.accountStatus!=account.getvalue())
            b = false;
        return b;
    }
}
