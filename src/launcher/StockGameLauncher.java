package launcher;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.LogManager;

import javafx.application.Application;
import javafx.stage.Stage;

import enums.Messages;
import gui.Handler;

public class StockGameLauncher extends Application {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// unter Run -> Run Configurations -> unter Arguments: en für Englische
		// Sprache eingeben oder nichts für Deutsch
		if (args.length != 0) {
			Messages.setresourceBoundle(args[0]);
		} else {
			Messages.setresourceBoundle("");
		}
		System.out.println("Default: " + Locale.getDefault());
		try {
			FileInputStream configFile = new FileInputStream(
					System.getProperty("user.dir")
							+ System.getProperty("file.separator")
							+ "bin\\properties\\logging_properties");
			LogManager.getLogManager().readConfiguration(configFile);
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		StockPriceProvider provider;
//		try {
//			provider = new YahooFinancePriceProvider();
//		    provider = new HistoricalStockPriceProvider();
//		} catch (Exception e) {
//			provider = new RandomStockPriceProvider();
//		}
		
		//StockPriceViewer priceviewer = new StockPriceViewer(provider, proxy);
		//priceviewer.start();
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Handler handler = new Handler();
		handler.start(stage);
	}
	
}
