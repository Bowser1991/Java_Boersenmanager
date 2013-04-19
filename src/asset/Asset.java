package asset;

public abstract class Asset {
    public final String name;
    
    public Asset(String name){
        this.name = name;
    }
    
    public String toString(){
        return ("  Name: "+name);
    }
    
    public abstract long getvalue();
    
    public boolean equals(Object first, Object second){
        Asset firstAsset = (Asset)first;
        Asset secondAsset = (Asset)second;
                
        if (firstAsset.name.equals(secondAsset.name)) return true;
        else return false;
    }
    
    public Share[] longerArray(Share[] sharearray, int howmuchlonger){
        Share[] longer = new Share[sharearray.length + howmuchlonger];
        for (int j = 0; j < sharearray.length; j++) {
            longer[j] = sharearray[j];
        }
        return longer;
    }
    
}