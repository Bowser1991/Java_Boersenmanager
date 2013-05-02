package asset;

public class Share {
    public final String name;
    private long actualshareprice;
    private long[] sharepricehistory = new long[10] ;
    
    public Share(String name, long actualshareprice) {              //Konstruktor
        this.name = name;
        this.actualshareprice = actualshareprice;
    }
    
    public long getActualSharePrice(){
        return actualshareprice;
    }
    
    public long[] getSharePriceHistory(){
    	return sharepricehistory;
    }
    
    public void setActualSharePrice(long newshareprice){            //speichert die Vergangenen Preise
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

    private long[] longerArray(long[] longarray, int howmuchlonger){
        long[] longer = new long[longarray.length + howmuchlonger];
        for (int j = 0; j < longarray.length; j++) {
            longer[j] = longarray[j];
        }
        return longer;
    }
    
    
    
}

