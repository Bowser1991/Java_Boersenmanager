package asset;
import Exception.ShareException;
/**
 * 
 * @author daniel und manuel
 *
 */
public class ShareItem extends Asset {
    private int numberOfShares;
    private long purchasevalue;
    /**
     * 
     * @param name
     */
    public ShareItem(String name) {
        super(name);
        numberOfShares = 0;
        purchasevalue = 0;
    }
    /**
     * 
     * @param name
     * @param numberofshares
     * @param purchasevalue
     */
    public ShareItem(String name, int numberofshares, long purchasevalue){
        super(name);
        this.numberOfShares = numberofshares;
        this.purchasevalue = purchasevalue;
    }
    /**
     * 
     */
    public String toString(){
        return "Share name: "+ name +" Number of Shares :"+Integer.toString(numberOfShares)+" Purchase Value : "+Long.toString(purchasevalue);
    }
    /**
     * 
     * @param amountofshares
     * @param purchasevalue
     */
    public void buyShare(int amountofshares, long purchasevalue){
        numberOfShares += amountofshares;
        this.purchasevalue += purchasevalue;
    }
    /**
     * 
     * @param amountofshares
     * @param purchasevalue
     * @throws ShareException
     */
    public void sellShare(int amountofshares, long purchasevalue ) throws ShareException{
        if (amountofshares > this.numberOfShares){
            throw new ShareException("not enough Shares to sell the wanted amount");
        }
        numberOfShares -= amountofshares;
        setPurchaseValue(-purchasevalue);
    }
    
    public int getNumberOfShares(){
        return numberOfShares;
    }

    public long getPurchasValue(){
        return purchasevalue;
    }
    
    public void setPurchaseValue(long price){  
        
        if(purchasevalue + price < 0){
            purchasevalue = 0;          
        } else {
            purchasevalue += price;
        }
        
    }

    public long getvalue() {
        return purchasevalue;
    }
    public boolean equals(ShareItem item){
        boolean b = true;
        if (numberOfShares != item.getNumberOfShares())
            b = false;
        if (purchasevalue != item.getPurchasValue())
            b = false;
        return b;
    }
    public void setNumberOfShares(int numberofshares){
        this.numberOfShares = numberofshares;
    }
    
    
}

