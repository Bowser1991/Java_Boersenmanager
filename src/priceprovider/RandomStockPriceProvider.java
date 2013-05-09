package priceprovider;

import java.util.Random;

import asset.Share;

public class RandomStockPriceProvider extends StockPriceProvider {
     public RandomStockPriceProvider(Share[] availableShare) {
            super(availableShare);
        }

        protected void updateShareRate(Share share)
        {
            Share searchShare = getShare(share.name);
            Random r = new Random ();
            long newSharePrice;
            newSharePrice = (r.nextLong()) % 300;
            if(searchShare.getActualSharePrice()+newSharePrice > 0){
                searchShare.setActualSharePrice(searchShare.getActualSharePrice()+newSharePrice);
            }
        }
}
