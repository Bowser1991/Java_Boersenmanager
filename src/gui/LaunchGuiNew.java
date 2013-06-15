package gui;

import java.util.ArrayList;
import java.util.List;

import asset.Player;
import asset.Share;
import Command.StockGameGUIProcessor;
import Exception.BotException;
import Exception.ShareException;
import innerimpl.AccountManager;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LaunchGuiNew extends Application {
	private Label outputlabel = new Label();
	private TabPane tabpane = new TabPane();
	private final static long STANDARD_ACCOUNT_WORTH = 100000;
	private AccountManager account = null;
	
	@Override
	public void start(Stage primarystage) throws Exception {

	}
	
	public void start(Stage primarystage, AccountManager account, StockGameGUIProcessor processor) throws Exception {
		this.account = account;
		primarystage.setHeight(500);
		primarystage.setWidth(700);
		BorderPane firstlevelpane = new BorderPane();
		firstlevelpane.setTop(setMenuBar(account));
		firstlevelpane.setLeft(setTableView(account));
		firstlevelpane.setCenter(tabpane);
		Scene scene = new Scene(firstlevelpane);
		primarystage.setScene(scene);
		primarystage.show();
	}
	
	public Tab setTab(Player player){
		Tab newtab = new Tab();
		newtab.setText(player.name);
		Label accountlabel = new Label(player.getCashAccount().toString());
		GridPane pan = new GridPane();
		pan.add(accountlabel, 0, 0);
		Label shareitemlabel = new Label(player.getShareDeposit().toString());
		pan.add(shareitemlabel, 0, 1);
		pan.add(setStartbotButton(account, player.name),0,2);
		newtab.setContent(pan);
		return newtab;
	}
	
	public TableView<Share> setTableView(final AccountManager account){
		TableView<Share> table = new TableView<Share>();

	    List<Share> namelist = new ArrayList<Share>();

		for (Share share : account.getPriceProvider().getAvailableShare()) {
			namelist.add(share);
		}
		ObservableList<Share> data = FXCollections.observableArrayList(namelist);
	    
		TableColumn<Share, String>  NameCol = new TableColumn<Share, String>("name");
		NameCol.setCellValueFactory(
			    new PropertyValueFactory<Share,String>("name")
			);
		NameCol.setMinWidth(100);
		TableColumn<Share, String> priceCol = new TableColumn<Share, String>("Preis");
		priceCol.setMinWidth(100);
		priceCol.setCellValueFactory(
			    new PropertyValueFactory<Share,String>("actualshareprice")
			);
		table.setItems(data);
		boolean sAll = table.getColumns().setAll(NameCol,priceCol);
		if(sAll == true){
			return table;
		}else{
			try {
				throw new ShareException("Share could not be convertet");
			} catch (ShareException e) {
				e.printStackTrace();
				return null;
			}
		}
		
	}
	public Button setStartbotButton(final AccountManager account, final String playername){
		Button startbot = new Button("Startbot");
		startbot.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				try {
					account.startBot(playername);
				} catch (BotException e) {
					outputlabel.setText("Action startbot could not be done");
					
				}
			}
		});
		return startbot;
	}
	public Button setStopbotButton(final AccountManager account, final String playername){
		Button startbot = new Button("Stopbot");
		startbot.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				try {
					account.stopBot(playername);
				} catch (BotException e) {
					outputlabel.setText("Action stpBot could not be done");
				}
			}
		});
		return startbot;
	}
		
	public MenuBar setMenuBar(final AccountManager account){
		MenuBar newmenu = new MenuBar();
		Menu menu = new Menu("Menu");
		MenuItem crp = new MenuItem("createPlayer");
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				System.exit(0);
			}
		});
		menu.getItems().addAll(crp,exit);
		crp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Stage menustage = new Stage();
				menustage.setTitle("Create Player");
				Label textlabel = new Label("type in the name of the player");
				final TextField field = new TextField();
				field.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {	
					@Override
					public void handle(KeyEvent action) {
						if(action.getCode() == KeyCode.ENTER){
								Player player = new Player(field.getText(), STANDARD_ACCOUNT_WORTH);
								account.addPlayer(player);
								tabpane.getTabs().add(setTab(player));
						}
					}
				} );
				GridPane menupane = new GridPane();
				menupane.add(textlabel, 0, 0);
				menupane.add(field, 0, 1);
				Scene menuscene = new Scene(menupane);
				menustage.setScene(menuscene);
				menustage.show();
			}
		});
		newmenu.getMenus().addAll(menu);
		return newmenu;
	}
}
