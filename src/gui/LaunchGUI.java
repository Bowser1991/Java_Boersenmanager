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
	private Stage primarystage;
    private Label label;
	private TextField field;
	
	public LaunchGUI (Label label, TextField field){
		this.label = label;
		this.field = field;
	}
	
	@Override
	public void start(Stage primarystage) throws Exception {
		this.primarystage = primarystage;
		gridPane.setPadding(new Insets(25,25,25,25));
		gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(200);
        gridPane.setVgap(200);
		updateGUI();

	}
	
	private void updateGUI(){
		gridPane.add(field, 0, 0);
		gridPane.add(label, 0, 1);
		Scene scene = new Scene(gridPane,300,275);
		primarystage.setScene(scene);
		primarystage.show();
	}
}	
