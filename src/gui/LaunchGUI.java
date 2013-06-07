package gui;


import innerimpl.AccountManager;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


import priceprovider.StockPriceProvider;
import asset.Player;
import asset.Share;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public abstract class LaunchGUI extends Application {
	private BorderPane pane = new BorderPane();
    private GridPane stockinfolabel = new GridPane();
    private Label outputlabel = new Label();
	private TextField field = new TextField();
	private Scene scene;
	Text text = new Text();

	public LaunchGUI(){
		field.setMinHeight(20);
		scene = new Scene(pane,400,375);
	}
	@Override
	public void start(Stage primarystage) throws Exception {
		pane.setPadding(new Insets(25,25,25,25));
		outputlabel.setText("");
		stockinfolabel.add(text, 0, 0);
		pane.setLeft(stockinfolabel);
		pane.setTop(field);
		pane.setCenter(outputlabel);
		primarystage.setScene(scene);
		primarystage.show();
	}
	/**
	 * 
	 * @return
	 */
	public Scene getScene() {
		return scene;
	}
	/**
	 * 
	 * @param scene
	 */
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	/**
	 * 
	 * @return
	 */
	public GridPane getStockInfoLabel() {
		return stockinfolabel;
	}
	/**
	 * 
	 * @return
	 */
	public TextField getField() {
		return field;
	}
	/**
	 * 
	 * @param field
	 */
	public void setField(String field) {
		this.field.setText(field);
	}
	/**
	 * 
	 * @return
	 */
	public Label getOutputlabel() {
		return outputlabel;
	}
	/**
	 * 
	 * @param outputlabel
	 */
	public void setOutputlabel(String outputlabel) {
		this.outputlabel.setText(outputlabel);
	}
	/**
	 * 
	 * @return
	 */
    public String createText(AccountManager manager, StockPriceProvider provider) {
        String output = getAvailableShares(provider) + "<br>"+"is there a gain if sell: " + manager.getDiverStatus()+"<br>" +"<br>"+ getPlayer(manager); 
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
    private String getAvailableShares(StockPriceProvider provider) {
        Share[] bufferShare = provider.getAvailableShare();
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
    private String getPlayer(AccountManager manager) {
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
    
}	
