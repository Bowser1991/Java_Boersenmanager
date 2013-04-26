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
            newSharePrice = Math.abs(r.nextLong()) % 35000;
            searchShare.setActualSharePrice(newSharePrice);
        }
}
