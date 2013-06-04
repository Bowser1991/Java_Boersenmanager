package priceprovider;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;

public class GlobalTimer{
    public final static GlobalTimer timer = new GlobalTimer();
    private Timer timerimpl = new Timer();

    /**
* GlobalTimer()
*
* constructor ist private fuer Singleton implementierung
*/
    private GlobalTimer() {
    }

    /**
* getTimer()
*
* instanziiert beim ersten aufruf den Timer, bei weiteren aufrufen wird der bereits instanziierte timer zurueckgegeben
*/
    public static GlobalTimer getTimer() {
        return timer;
    }

    /**
* addTast(TimerTask task)
*/
    public void addTask(TimerTask task) {
        timerimpl.scheduleAtFixedRate(task, 2000, 4000);
    }
    
    public void addTask(TimerTask task, int updateRate) {
        timerimpl.scheduleAtFixedRate(task, 2000, updateRate);
    }
    
    /**
* cleanUp()
*/
    public void cleanUp() {
     timerimpl.purge();
    }
}
