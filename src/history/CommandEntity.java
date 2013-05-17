package history;

import java.util.Calendar;

public class CommandEntity {
    private String methodName;
    private String playerName;
    private String shareName;
    private int amount;
    private Calendar calendar;
    private long time;
    
    public CommandEntity(String methodName, String playerName, String shareName, int amount){
        this.methodName = methodName;
        this.playerName = playerName;
        this.shareName = shareName;
        this.amount = amount;
        time = System.currentTimeMillis();
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
    }

    public String getMethodName()
    {
        return methodName;
    }
    public String getPlayerName()
    {
        return playerName;
    }
    public String getShareName()
    {
        return shareName;
    }
    public int getAmount()
    {
        return amount;
    }
    public long getTime(){
        return time;
    }
    public String toString(){
        return calendar.getTime().toString() + " Methodname: " + methodName + ", Sharename: " + shareName + ", Playername: " + playerName + ", Amount: " + amount;
    }
}
