package history;

import java.text.DateFormat;
import java.util.Calendar;

import enums.Messages;

import asset.Player;
import asset.Share;

public class CommandEntity {
    private String methodName;
    private Player player;
    private Share  share;
    private int amount;
    private Calendar calendar;
    private DateFormat simple = null;
    
    public CommandEntity(String methodName, Player player, Share share, int amount){
        this.methodName = methodName;
        this.player = player;
        this.share = share;
        this.amount = amount;
        calendar = Calendar.getInstance();
        simple = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM);
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
        return simple.format(calendar.getTime()) + " " + Messages.getString("Methodename") + methodName + ", " + Messages.getString("Sharename") + share.name + ", " +Messages.getString("Playername") + player.name + ", " + Messages.getString("Amount") + amount;
    }
}
