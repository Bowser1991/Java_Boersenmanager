package launcher;

import innerimpl.AccountManager;
import innerimpl.AccountManagerImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.Locale;
import java.util.logging.LogManager;

import priceprovider.HistoricalStockPriceProvider;
import priceprovider.RandomStockPriceProvider;
import priceprovider.StockPriceInfo;
import priceprovider.StockPriceProvider;
import priceprovider.StockPriceViewer;
import priceprovider.YahooFinancePriceProvider;
import proxy.AccountManagerHandler;
import Command.StockGameCommandProcessor;
import Command.StockGameGUIProcessor;
import bots.Bot;
import bots.StockBuySellBot;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import enums.Messages;
import gui.LaunchGUI;

public class StockGameLauncher extends Application {
	private static Label label = new Label(Messages.getString("welcomeText"));
	private static TextField field = new TextField();
	private static StockGameGUIProcessor commandprocessor;
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
		StockPriceProvider provider;
		try {
//			provider = new YahooFinancePriceProvider();
		    provider = new HistoricalStockPriceProvider();
		} catch (Exception e) {
			provider = new RandomStockPriceProvider();
		}
		
		//StockPriceViewer priceviewer = new StockPriceViewer(provider, proxy);
		//priceviewer.start();
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		LaunchGUI gui = new LaunchGUI() {
		};
		gui.start(stage);
	}
	
}
