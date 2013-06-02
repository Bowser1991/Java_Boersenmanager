package asset;
/**
 * 
 * @author daniel und manuel
 *
 */
public class Share {
    public final String name;
    private String financename;
    private String exchange;
	private long actualshareprice;
    private long[] sharepricehistory = new long[10] ;
    /**
     * Share(String name, long actualshareprice).
     * Konsruktor mit Name und aktuellem Preis
     * @param name
     * @param actualshareprice
     */
    public Share(String name, long actualshareprice) {              
        this.name = name;
        this.actualshareprice = actualshareprice;
    }
    
    /**
     * long getActualSharePrice().
     * @return
     * gibt den Aktuellen SharePrice der Aktie zurück
     */
    public long getActualSharePrice(){
        return actualshareprice;
    }
    
    /**
     * long[] getSharePriceHistory()
     * @return
     * gibt die Preis Historie zurück
     */
    public long[] getSharePriceHistory() {
    	return sharepricehistory;
    }
    
    /**
     * setActualSharePrice(long newshareprice)
     * Setzt einen neuen Preis bei einer Aktie
     * @param newshareprice
     * der neue Preis der Aktie
     */
    public void setActualSharePrice(long newshareprice) {            //speichert die Vergangenen Preise
        for(int i = 0; i < sharepricehistory.length;i++){           //geht jeden Wert in sharePricehistory sobald ein leeres Objekt gefunden wird speicher er dort den letzten Preis
            if(sharepricehistory[i] == 0){
                sharepricehistory[i] = actualshareprice;
                break;
            }
            else if(i == sharepricehistory.length - 1){             //verlängert das Array wenn kein Platz zum speichern ist
               sharepricehistory = longerArray(sharepricehistory, 1);
            }
        }
        actualshareprice = newshareprice;
    }
    /**
     * long[] longerArray(long[] longarray, int howmuchlonger).
     * verlängert ein Array
     * @param longarray
     * Zu verlängerndes Array
     * @param howmuchlonger
     * Wie viel Länger
     * @return long[]
     * Gibt das verlängerte Array zurück
     */
    private long[] longerArray(long[] longarray, int howmuchlonger){
        long[] longer = new long[longarray.length + howmuchlonger];
        for (int j = 0; j < longarray.length; j++) {
            longer[j] = longarray[j];
        }
        return longer;
    }
    /**
     * boolean equals(String name).
     * @param name
     * Name als String
     * @return boolean
     * gibt an ob die beiden Strings gleich sind
     */
    public boolean equals(String name){
        return this.name.equals(name);
    }
    /**
     * String toString().
     * Uberschreibt die toString() von Object
     * @return String
     * Im Format: Share name: " + name + ", Actual share price: " + actualshareprice + "\r\n
     */
    @Override
    public String toString(){
        return "Share name: " + name + ", Actual share price: " + actualshareprice + "\r\n";
    }
    /**
     * String getFinanceName().
     * @return String
     * gibt den den Financ Name zurück also für Google inc GOOG
     */
	public String getFinanceName() {
		return financename;
	}
	/**
	 * void setFinanceName(String financename)
	 * @param financename
	 * Name der auf der Online Seite gebraucht wird Google Inc = GOOG
	 */
	public void setFinanceName(String financename) {
		this.financename = financename;
	}
	/**
	 * String getExchange().
	 * @return String
	 * gibt die Waerung an in welcher der Preis abgelegt ist
	 */
	public String getExchange() {
		return exchange;
	}
	/**
	 * void setExchange(String exchange).
	 * @param exchange
	 * Waerung in welcher der SharePrice abgelegt wird
	 */
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
}

