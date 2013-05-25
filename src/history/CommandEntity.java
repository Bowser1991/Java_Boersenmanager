package history;

import java.util.Calendar;

import asset.Player;
import asset.Share;

public class CommandEntity {
    private String methodName;
    private Player player;
    private Share  share;
    private int amount;
    private Calendar calendar;
    
    public CommandEntity(String methodName, Player player, Share share, int amount){
        this.methodName = methodName;
        this.player = player;
        this.share = share;
        this.amount = amount;
        calendar = Calendar.getInstance();
    }

    public String getMethodName()
    {
        return methodName;
    }
    public Player getPlayer()
    {
        return player;
    }
    public Share getShare()
    {
        return share;
    }
    public int getAmount()
    {
        return amount;
    }
    public Calendar getCalendar(){
    	return calendar;
    }
    public String toString(){
        return calendar.getTime().toString() + " Methodname: " + methodName + ", Sharename: " + share.name + ", Playername: " + player.name + ", Amount: " + amount;
    }
}
