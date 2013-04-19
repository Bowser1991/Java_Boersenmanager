package asset;
import Exception.ShareException;


public class ShareDeposit extends Asset {
    
    private ShareItem[] allshareitems;
    
    public ShareDeposit(String name) {
        super(name);
        allshareitems = new ShareItem[2];
    }
    
    private void addShareItem (ShareItem newshareitem){
        //geht jeden Wert in allshare durch sobald ein leeres Objekt gefunden wird speicher er dort den letzten Preis
        for(int i = 0; i < allshareitems.length;i++){                       
            if(allshareitems[i] == null){
                allshareitems[i] = newshareitem;
                break;
            }
            //verlängert das Array wenn kein Platz zum speichern ist
            else if(i == allshareitems.length - 1){ 
                allshareitems = longerArray(allshareitems, 1);
                i = 0;
            }
        }
    }
    
    public ShareItem[] getAllShareItems(){
        return allshareitems;
    }
    
    public String toString(){
        //Speichern all toString der ShareItems int output
        String output = "";                             
        for(int i = 0; i < allshareitems.length;i++){
            if(allshareitems[i]==null){}
            else{
            output += allshareitems[i].toString();
            }
        }
        //Ausgabe output
        return output;
    }

    
    
    
    public void buyShare(Share newShare, int amount){
        for(int i = 0; i < allshareitems.length;i++){                       
            if (allshareitems[i] != null) {
                if (newShare.name.equals(allshareitems[i].name)) {
                    allshareitems[i].buyShare(amount,
                            newShare.getActualSharePrice() * amount);
                    return;
                }
            }
            if(i==allshareitems.length-1){
                ShareItem newitem = new ShareItem(newShare.name);
                addShareItem(newitem);
                i=-1;
            }
        }
    }
         
    public void sellShare(Share newShare, int amount) throws ShareException{
        for(int i = 0; i < allshareitems.length;i++){                       
            if(newShare.name.equals(allshareitems[i].name)){                                
                allshareitems[i].sellShare(amount, newShare.getActualSharePrice()*amount);                  
                return;
            }
        }
        throw new ShareException("No Share with this name available"+newShare.name);
    }
    
    private ShareItem[] longerArray(ShareItem[] sharearray, int howmuchlonger){
        ShareItem[] longer = new ShareItem[sharearray.length + howmuchlonger];
        for (int j = 0; j < sharearray.length; j++) {
            longer[j] = sharearray[j];
        }
        return longer;
    }

    public long getvalue() {
        long priceamount = 0l;
        for(int i = 0; i < allshareitems.length;i++){
            if(allshareitems[i].getPurchasValue()>=0){
                priceamount += allshareitems[i].getPurchasValue();
            }
        }
        return priceamount;
    }
}
