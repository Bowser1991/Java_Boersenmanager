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
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Handler extends Application {
	private EventHandler<KeyEvent> handler;
	private EventHandler<MouseEvent> actionhandler;
	private LaunchGUI gui = new LaunchGUI() {
    };
	private StockPriceProvider provider;
	private AccountManager manager ;
	private StockGameGUIProcessor commandprocessor;
	private Scene nscene;
	Thread thread;
	/**
	 * 
	 */
	@Override
	public void start(Stage primarystage) throws Exception {
//		gui = new LaunchGUI() {};
//		primarystage.setOnCloseRequest(closeWindow);
		setKeyHandler();
//		setMousHandler();
		nscene = gui.getScene();
		nscene.addEventHandler(KeyEvent.KEY_PRESSED, handler);
//		nscene.addEventHandler(MouseEvent.MOUSE_MOVED, actionhandler);
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
		primarystage.setOnShowing(window);
		gui.start(primarystage);
		primarystage.show();
	}
	/**
	 * 
	 */
//	private void setMousHandler(){
//		actionhandler = new EventHandler<MouseEvent>() {
//				@Override
//				public void handle(MouseEvent action) {
//					
//						gui.setStockInfoLabel(gui.createText(manager, provider));
//					
//				}
//		};		
//			
//	}
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
//						gui.setStockInfoLabel(gui.createText(manager, provider));
					}
				}
		};		
			
	}
	public EventHandler<WindowEvent> window = new EventHandler<WindowEvent>() {

        @Override
        public void handle(WindowEvent arg0)
        {
            update();
        }
    };
	private void update(){
        thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    @SuppressWarnings("rawtypes")
    public Task task = new Task() {

        @Override
        protected Object call() throws Exception
        {
            while(true){
                //zu aktualiesierender String einfügen
                gui.text.setText(gui.createText(manager, provider));
                try {      
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    ;
                }
            }  
        }
    };
}
