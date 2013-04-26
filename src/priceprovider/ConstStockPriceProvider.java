package priceprovider;
import asset.Share;


public class ConstStockPriceProvider extends StockPriceProvider {


    public ConstStockPriceProvider(Share[] availableShare) {
        super(availableShare);
    }

    protected void updateShareRate(Share share)
    {
        Share searchShare = getShare(share.name);
        searchShare.setActualSharePrice(share.getActualSharePrice());
    }


}
