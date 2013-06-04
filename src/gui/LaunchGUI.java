package gui;


import innerimpl.AccountManager;
import innerimpl.AccountManagerImpl;

import java.lang.reflect.Proxy;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import priceprovider.GlobalTimer;
import priceprovider.HistoricalStockPriceProvider;
import priceprovider.RandomStockPriceProvider;
import priceprovider.StockPriceInfo;
import priceprovider.StockPriceProvider;
import priceprovider.StockPriceProvider.MyTask;
import proxy.AccountManagerHandler;
import Command.StockGameGUIProcessor;
import asset.Player;
import asset.Share;
import bots.Bot;
import bots.StockBuySellBot;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public abstract class LaunchGUI extends Application {
	private BorderPane pane = new BorderPane();
	private Stage primarystage;
    private Label label = new Label();
	private TextField field = new TextField();
	private StockGameGUIProcessor commandprocessor;
	private AccountManager manager ;
	private StockPriceInfo sharepriceinfo = null;
	private StockPriceProvider provider;
	private EventHandler<KeyEvent> handler;
	private Scene scene;
	public LaunchGUI(){
		label.setText("hallo");
		field.setMinHeight(20);
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
		sharepriceinfo = provider;
		setKeyHandler();
		field.addEventHandler(KeyEvent.KEY_PRESSED, handler);
		scene = new Scene(pane,400,375);
	}
	@Override
	public void start(Stage primarystage) throws Exception {
		this.primarystage = primarystage;
		pane.setPadding(new Insets(25,25,25,25));
    	MyTask newtask = new MyTask();
    	GlobalTimer.getTimer().addTask(newtask);
		updateGUI();
        
	}
	/**
	 * 
	 */
	private void setKeyHandler(){
		handler = new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent action) {
					if(action.getCode() == KeyCode.ENTER){
						if(field.getText().contentEquals("help"))
							pane.setCenter(new Label(""));
					label.setText(createText());
					commandprocessor.process(field.getText());
					field.setText("");
					}
				}
			};
	}
	/**
	 * 
	 */
	private void updateGUI(){
		label.setText(createText());
		pane.setLeft(label);
		pane.setTop(field);
		primarystage.setScene(scene);
		primarystage.show();
		
	}
	/**
	 * 
	 * @return
	 */
    private String createText() {
        String output = getAvailableShares() + "<br>"+"is there a gain if sell: " + manager.getDiverStatus()+"<br>" +"<br>"+ getPlayer(); 
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormatter = DateFormat.getDateTimeInstance();
        output += dateFormatter.format(date);
        output = output.replaceAll("<br>", "\r\n");
        output = output.replaceAll("<pre>", "\t\t");
        output = output.replaceAll("</pre>", "\t\t");
        return output;
    }
    /**
     * 
     * @return
     */
    private String getAvailableShares() {
        Share[] bufferShare = sharepriceinfo.getAvailableShare();
        String s = "";
        for (int j = 0; j < bufferShare.length; j++) {
        	if(bufferShare[j].getExchange()!=null){
            s += bufferShare[j].name +"<pre>"+ (float)bufferShare[j].getActualSharePrice()/100+" "+bufferShare[j].getExchange()+"</pre>" + "<br>";
        	}else{
        		s += bufferShare[j].name +"<pre>"+ (float)bufferShare[j].getActualSharePrice()/100+"</pre>" + "<br>";
        	}
        }
        return s;
    }
    /**
     * 
     * @return
     */
    private String getPlayer() {
        Player[] bufferPlayer = manager.getAllPlayer();
        String s = "";
        int counter = 0;
        if(bufferPlayer[bufferPlayer.length -1] != null&&bufferPlayer[bufferPlayer.length -1].name.contentEquals("Bot")){
        	counter = 1;
        }
        for (int i = 0; i < bufferPlayer.length - counter;i++){
            if (bufferPlayer[i] != null){
                s += "Player name: " + bufferPlayer[i].name + "<br>" + bufferPlayer[i].getCashAccount().toString() + "<br>";
                s += bufferPlayer[i].getShareDeposit().toString() + "<br>";

            }
        }
        return s;
    }
    class MyTask extends TimerTask {
        @Override
        public void run() {
				LaunchGUI.this.updateGUI();
        }
    }  
}	
