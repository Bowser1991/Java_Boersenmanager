import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo  {
    private int counter;
    private static TimerDemo demo = new TimerDemo();
    
    private TimerDemo (){
       
    }
    
    public TimerDemo getInstance (){
        return demo;
    }
    
    private void modifyUserObject() {
        System.out.println("modifyUserObject: " + ++counter);
    }
    
    private void startTiming() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                TimerDemo.this.modifyUserObject();
            }
        }, 2000, 1000);
    }

//    public static void main(String[] args)  {
//        new TimerDemo().startTiming();
//    }
}