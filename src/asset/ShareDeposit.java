package asset;
import java.util.Enumeration;
import java.util.Hashtable;

import Exception.ShareException;


public class ShareDeposit extends Asset {
    /**
     * Hashtable zur Abspeicherung aller ShareItems
     */
    private Hashtable<String, ShareItem> allshareitems = new Hashtable<>();
    /**
     * ShareDeposit(String name).
     * Standard Konstruktor
     * @param name
     * Name des Deposits
     */
    public ShareDeposit(String name) {
        super(name);
        
    }
    /**
     * void addShareItem (ShareItem newshareitem).
     * @param newshareitem
     * fügt ein neues Share Item hinzu
     */
    private void addShareItem (ShareItem newshareitem){
    	if(!allshareitems.containsKey(newshareitem)){
        	allshareitems.put(newshareitem.name, newshareitem);
    	}
    }
    /**
     * ShareItem[] getAllShareItems().
     * gibt die Hashtable mit den gespeicherten ShareItems als array zurück
     * @return
     * Hashtable
     */
    public ShareItem[] getAllShareItems(){
    	Enumeration<ShareItem> bufferenum = allshareitems.elements();
    	ShareItem[] returnitem = new ShareItem[allshareitems.size()];
       	for (int i = 0; i < allshareitems.size(); i++) {
    		if(bufferenum.hasMoreElements()){
    		 returnitem[i] = bufferenum.nextElement();
    		}
		}
        return returnitem;
    }
    /**
     * String toString()
     * ueberschreibt die toString() von Asset
     * @return String
     * im Format: bufferenum.nextElement().toString()+"<br>"
     */
    @Override
    public String toString(){
    	Enumeration<ShareItem> bufferenum = allshareitems.elements();
    	String returnitem = "";
       	for (int i = 0; i < allshareitems.size(); i++) {
    		if(bufferenum.hasMoreElements()){
    		 returnitem += bufferenum.nextElement().toString()+"<br>";
    		}
       	}	
    	return returnitem;
    }
    /**
     * void buyShare(Share newShare, int amount)
     * kauft eine neue Aktie und legt diese im entspeic
     * @param newShare
     * Die neue Aktie
     * @param amount
     * Anzahl der zu kaufenden Aktien
     */
    public void buyShare(Share newShare, int amount){
    	 if (!allshareitems.containsKey(newShare.name)) {
        	 ShareItem newitem = new ShareItem(newShare.name);
             addShareItem(newitem);
         }
         allshareitems.get(newShare.name).buyShare(amount,newShare.getActualSharePrice() * amount);
    }
    /**     
     * sellShare(Share newShare, int amount).
     * @param newShare
     * Zu verkaufende Aktie
     * @param amount
     * Anzahl der zu verkaufenden Aktien
     * @throws ShareException
     * Wird geworfen wenn keine Aktie mit diesem Namen gefunden wird
     */
    public void sellShare(Share newShare, int amount) throws ShareException{
    	if (allshareitems.containsKey(newShare.name)) {
    		allshareitems.get(newShare.name).sellShare(amount, newShare.getActualSharePrice()*amount);
    	}else{
    		throw new ShareException("No Share with this name available"+newShare.name);
    	}
    }
    /**
     * long getvalue().
     * @return long
     * Gibt den gesamten Einkaufswert aller ShareItems aus
     */
    public long getvalue() {
    	Enumeration<ShareItem> bufferenum = allshareitems.elements();
    	long calcvalue = 0;
    	for (int i = 0; i < allshareitems.size(); i++) {
    		if(bufferenum.hasMoreElements()){
    		calcvalue += bufferenum.nextElement().getPurchasValue();
    		}
		}
    	return calcvalue;
    }
    /**
     * boolean equals(ShareDeposit deposit).
     * @param deposit
     * Das zu vergleichende Deposit
     * @return boolean
     * gibt an ob die beiden Deposit gleich sind
     */
    public boolean equals(ShareDeposit deposit){
        boolean b = true;
        if (!this.name.equals(deposit.name)){
            b = false;
            return b;
        }
        for (int i = 0; i < getAllShareItems().length; i++) {
            if (getAllShareItems()[i] == null){
                if (deposit.getAllShareItems()[i] != null)
                    b = false;
            }else {
                if (!getAllShareItems()[i].equals(deposit.getAllShareItems()[i]))
                    b = false;
            }
        }
        return b;
    }
    /**
     *  boolean equals(ShareItem[] item).
     * @param item
     * das zu vergleichede ShareItem Array
     * @return	boolean
     * gibt an ob die beide ShareItem Array gleich sind
     */
    public boolean equals(ShareItem[] item){
        boolean b = true;
        for (int i = 0; i < getAllShareItems().length; i++) {
            if (getAllShareItems()[i] == null){
                if (item[i] != null)
                    b = false;
            }else {
                if (!getAllShareItems()[i].equals(item[i]))
                    b = false;
            }              
        }
        return b;
    }
}
