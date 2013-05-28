package priceprovider;
import asset.Share;


public class ConstStockPriceProvider extends StockPriceProvider {
    protected void updateShareRate(Share share)
    {
        Share searchShare = getShare(share.name);
        searchShare.setActualSharePrice(share.getActualSharePrice());
    }


}
