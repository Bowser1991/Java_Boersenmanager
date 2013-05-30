package priceprovider;

import java.io.IOException;

import Network.StockApi;
import asset.Share;

public class YahooFinancePriceProvider extends StockPriceProvider {
	@Override
	protected void updateShareRate(Share share) {
		StockApi networkShare = new StockApi();
		String[] buffershare = new String[1];
		buffershare[0] = share.getFinanceName(); 
		Share searchShare = getShare(share.name);
	    try {
			searchShare.setActualSharePrice(networkShare.startRateUpdate(buffershare)[0].getActualSharePrice());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
