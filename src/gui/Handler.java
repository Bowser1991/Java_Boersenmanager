package gui;

import innerimpl.AccountManager;
import innerimpl.AccountManagerImpl;

import java.lang.reflect.Proxy;

import priceprovider.HistoricalStockPriceProvider;
import priceprovider.RandomStockPriceProvider;
import priceprovider.StockPriceProvider;
import proxy.AccountManagerHandler;
import Command.StockGameGUIProcessor;
import bots.Bot;
import bots.StockBuySellBot;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class Handler extends Application {
	private EventHandler<KeyEvent> handler;
	private EventHandler<MouseEvent> actionhandler;
	private LaunchGUI gui;
	private StockPriceProvider provider;
	private AccountManager manager ;
	private StockGameGUIProcessor commandprocessor;
	private Scene nscene;
	/**
	 * 
	 */
	@Override
	public void start(Stage primarystage) throws Exception {
		gui = new LaunchGUI() {};
		setKeyHandler();
		setMousHandler();
		nscene = gui.getScene();
		nscene.addEventHandler(KeyEvent.KEY_PRESSED, handler);
		nscene.addEventHandler(MouseEvent.MOUSE_MOVED, actionhandler);
		gui.setScene(nscene);
		try {
		    provider = new HistoricalStockPriceProvider();
		} catch (Exception e) {
			provider = new RandomStockPriceProvider();
		}
		manager = new AccountManagerImpl(provider);
		AccountManager proxy = (AccountManager) Proxy.newProxyInstance(
				AccountManager.class.getClassLoader(),
				new Class[] { AccountManager.class },
				new AccountManagerHandler(manager));
		Bot bot1 = new StockBuySellBot(proxy, provider);
		proxy.addPlayer(bot1);
		commandprocessor = new StockGameGUIProcessor(proxy);
		gui.start(primarystage);
	}
	/**
	 * 
	 */
	private void setMousHandler(){
		actionhandler = new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent action) {
					
						gui.setStockInfoLabel(gui.createText(manager, provider));
					
				}
		};		
			
	}
	/**
	 * 
	 */
	private void setKeyHandler(){
		handler = new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent action) {
					if(action.getEventType()==KeyEvent.KEY_PRESSED){
						gui.getField().getText();
						gui.setOutputlabel(commandprocessor.process(gui.getField().getText()));
						gui.setField("");
						gui.setStockInfoLabel(gui.createText(manager, provider));
					}
				}
		};		
			
	}
}
