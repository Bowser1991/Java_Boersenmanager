package priceprovider;

import java.util.Timer;
import java.util.TimerTask;


public class TickerTask {
    private final static int UPDATETIME = 500;
    private static TickerTask task = null;
    private StockPriceProvider provider = null;
   
    public static TickerTask getInstance (){
        if(task == null){
            task = new TickerTask();
            return task;
        }else{
        return task;
        }
    }
    
    private TickerTask(){
        startTiming();
    }
   
    public void getProviderInstance(StockPriceProvider getprovider){
        provider = getprovider;
    }
    
    public void run(){
        provider.startUpdate();
    }
    
   private void startTiming() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                TickerTask.this.run();
            }
        }, 2000, UPDATETIME);
    }

}