package asset;
import Exception.ShareException;
public class ShareItem extends Asset {
    
    private int numberOfShares;
    private long purchasevalue;
    
    public ShareItem(String name) {
        super(name);
        numberOfShares = 0;
        purchasevalue = 0;
    }
    
    public String toString(){
        return "Share name: "+ name +" Number of Shares :"+Integer.toString(numberOfShares)+" Purchase Value : "+Long.toString(purchasevalue);
    }
    
    public void buyShare(int amountofshares, long purchasevalue){
        numberOfShares += amountofshares;
        this.purchasevalue += purchasevalue;
    }
    
    public void sellShare(int amountofshares, long purchasevalue ) throws ShareException{
        if (amountofshares > this.numberOfShares){
            throw new ShareException("not enough Shares to sell the wanted amount");
        }
        numberOfShares -= amountofshares;
        this.purchasevalue -= purchasevalue;
    }
    
    public int getNumberOfShares(){
        return numberOfShares;
    }

    public long getPurchasValue(){
        return purchasevalue;
    }
    
    public void setPurchaseValue(long price){           //was soll eigetnlich passieren wenn die gekauften Aktien auf einmal ein Minus erreichen geht das überhaupt ?
        purchasevalue += price;
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

