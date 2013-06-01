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
    
    public boolean equals(String name){
        return this.name.equals(name);
    }
    public String toString(){
        return "Share name: " + name + ", Actual share price: " + actualshareprice + "\r\n";
    }

	public String getFinanceName() {
		return financename;
	}

	public void setFinanceName(String financename) {
		this.financename = financename;
	}
	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
}

