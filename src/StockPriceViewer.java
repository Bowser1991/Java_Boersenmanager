import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;

import asset.Share;


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

        
        public String getAvailableShares() {
            Share[] bufferShare = sharepriceinfo.getAvailableShare();
            String s = " ";
            for (int j = 0; j < bufferShare.length; j++) {
                s = s+" " + bufferShare[j].name +" "+ bufferShare[j].getActualSharePrice() + "<br>";
            }
            return s;
        }
        public String getPlayer() {
            Player[] bufferPlayer = manager.getAllPlayer();
            String s = "";
            for (int i = 0; i < bufferPlayer.length; i++){
                if (bufferPlayer[i] != null){
                    s += "Player name: " + bufferPlayer[i].name + "<br>" + bufferPlayer[i].getCashAccount().toString() + "<br>";
                    s += bufferPlayer[i].getShareDeposit().toString() + "<br>" + "<br>";
                    
                }
            }
            return s;
        }
        
        
        private String createText() {
            
            String output ="<html><body>" + getAvailableShares() + "<br>"+ getPlayer(); 
            Calendar cal = Calendar.getInstance();
            Date date = cal.getTime();
            DateFormat dateFormatter = DateFormat.getDateTimeInstance();
            output += dateFormatter.format(date) + "</body></html>";
            return output;
        }
    }
    public void start() {
        ticker = new Timer(true); //as daemon
        ticker.scheduleAtFixedRate(new TickerTask(), 1000, TICK_PERIOD);
    }
}