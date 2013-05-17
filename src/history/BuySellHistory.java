package history;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BuySellHistory {
    private List<CommandEntity> history = new LinkedList<CommandEntity>();
    
    public void addHistory(String methodName, String shareName, String playerName, int amount){
        history.add(new CommandEntity(methodName, playerName, shareName, amount));
    }
    public void sortByTime(){
        Collections.sort(history, new ComparatorTime());
    }
    public void sortByShareName(){
        Collections.sort(history, new ComparatorShareName());
    }
    public void sortByAllShareName(){
        Collections.sort(history, new ComparatorAllShareName());
    }
    public void sortByMethode(){
        Collections.sort(history, new ComparatorMethodName());
    }
    @Override
    public String toString(){
        String s = "";
        for(Iterator<CommandEntity> i = history.iterator(); i.hasNext();){
            s = s +i.next().toString() + "\n";
        }
        return s;
    }
}
