package priceprovider;

import innerimpl.AccountManager;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;


import asset.Player;
import asset.Share;


@SuppressWarnings("serial")
public class StockPriceViewer extends JFrame {
    private StockPriceInfo sharepriceinfo = null;
    private AccountManager manager = null;
    private static final int TICK_PERIOD = 1000;
    private Timer ticker;
    private JLabel clockLabel;
    
    
    public StockPriceViewer(StockPriceInfo newinfo, AccountManager manager) {
        sharepriceinfo = newinfo;
        this.manager = manager;
        clockLabel = new JLabel("coming soon ...");
        add("Center", clockLabel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
    }
    
    private class TickerTask extends TimerTask {
        public void run() {
            String output = createText();
            clockLabel.setText(output);
            clockLabel.repaint();
        }      

        
        private String getAvailableShares() {
            Share[] bufferShare = sharepriceinfo.getAvailableShare();
            String s = " ";
            for (int j = 0; j < bufferShare.length; j++) {
                s = s+" " + bufferShare[j].name +" "+ bufferShare[j].getActualSharePrice() + "<br>";
            }
            return s;
        }
        private String getPlayer() {
            Player[] bufferPlayer = manager.getAllPlayer();
            String s = "";
            int counter = 0;
            if(bufferPlayer[bufferPlayer.length -1] != null&&bufferPlayer[bufferPlayer.length -1].name.contentEquals("Bot")){
            	counter = 1;
            }
            for (int i = 0; i < bufferPlayer.length - counter; ){
                if (bufferPlayer[i] != null){
                    s += "Player name: " + bufferPlayer[i].name + "<br>" + bufferPlayer[i].getCashAccount().toString() + "<br>";
                    s += bufferPlayer[i].getShareDeposit().toString() + "<br>";
                    i++;
                }
            }
            return s;
        }
        private String createText() {
            String output = "<html><body>" + getAvailableShares() + "<br>"+"is there a gain if sell: " + manager.getDiverStatus()+"<br>" +"<br>"+ getPlayer(); 
            Calendar cal = Calendar.getInstance();
            Date date = cal.getTime();
            DateFormat dateFormatter = DateFormat.getDateTimeInstance();
            output += dateFormatter.format(date) + "</body></html>";
            return output;
        }
    }
    public void start() {
        ticker = new Timer(true); //as daemon
        ticker.scheduleAtFixedRate(new TickerTask(), 500, TICK_PERIOD);
    }
}
