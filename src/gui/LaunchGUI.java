package gui;

import innerimpl.AccountManager;
import innerimpl.AccountManagerImpl;

import java.lang.reflect.Proxy;
import java.util.Timer;
import java.util.TimerTask;

import enums.Messages;

import priceprovider.RandomStockPriceProvider;
import priceprovider.StockPriceInfo;
import priceprovider.StockPriceProvider;
import priceprovider.StockPriceViewer;
import priceprovider.YahooFinancePriceProvider;
import proxy.AccountManagerHandler;
import Command.StockGameCommandProcessor;
import bots.Bot;
import bots.StockBuySellBot;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LaunchGUI extends Application {
	private GridPane gridPane = new GridPane();
    private Label label = new Label(Messages.getString("welcomeText"));
    private TextField field = new TextField();
	private Stage primarystage;
    
	@Override
	public void start(Stage primarystage) throws Exception {
		this.primarystage = primarystage;
		gridPane.setPadding(new Insets(25,25,25,25));
		gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(200);
        gridPane.setVgap(200);
		this.start();
		StockPriceProvider provider;
		try {
			provider = new YahooFinancePriceProvider();
		} catch (Exception e) {
			provider = new RandomStockPriceProvider();
		}
		AccountManager manager = new AccountManagerImpl(provider);
		AccountManager proxy = (AccountManager) Proxy.newProxyInstance(
				AccountManager.class.getClassLoader(),
				new Class[] { AccountManager.class },
				new AccountManagerHandler(manager));
		Bot bot1 = new StockBuySellBot(proxy, provider);
		proxy.addPlayer(bot1);
		StockGameCommandProcessor commandprocessor = new StockGameCommandProcessor(
				proxy);
		StockPriceInfo priceinfo = new RandomStockPriceProvider();
		StockPriceViewer priceviewer = new StockPriceViewer(priceinfo, proxy);
		priceviewer.start();
		
		
		commandprocessor.process(label, field);
	}
	
	private void updateGUI(){
		gridPane.add(field, 0, 0);
		gridPane.add(label, 0, 1);
		Scene scene = new Scene(gridPane,300,275);
		primarystage.setScene(scene);
		primarystage.show();
	}
	
    public void start() {
        Timer ticker = new Timer(true); //as daemon
        ticker.scheduleAtFixedRate(new TickerTask(), 500, 1000);
    }

	private class TickerTask extends TimerTask {
		public void run() {
			updateGUI();
		}
	}
}
