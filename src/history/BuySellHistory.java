package history;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;


import asset.Player;
import asset.Share;

public class BuySellHistory {
    private ArrayList<CommandEntity> history = new ArrayList<CommandEntity>();

    /**
     * addHistory(String methodName, String shareName, String playerName, int amount)
     * Fügt zur Liste ein weiteres Element mit den jeweiligen Parametern hinzu           
     * @param methodName
     * @param shareName
     * @param playerName
     * @param amount
     */
    public void addHistory(String methodName, Share share,
            Player player, int amount)
    {
        history.add(new CommandEntity(methodName, player, share, amount));
    }
    /**
     * 
     * @param comparator
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
	@SuppressWarnings("unchecked")
	public void sort(Comparator<?> comparator) throws InstantiationException, IllegalAccessException{
    	Collections.sort(history, comparator.getClass().newInstance());
    }
	/**
	 * 
	 */
    @Override
    public String toString()
    {
        String s = "";
        for (Iterator<CommandEntity> i = history.iterator(); i.hasNext();) {
            s = s + i.next().toString() + "\n";
        }
        return s;
    }
    /**
     * 
     * @return
     */
    public CommandEntity[] getFirstLast(){
    	CommandEntity[] returnentity = new CommandEntity[2];
    	returnentity[0] = history.get(0);
    	returnentity[1] = history.get(history.size()-1);
    	return returnentity;
    }
    /**
     * 
     * @return
     */
    public Object[] getAll(){
    	return history.toArray();
    }
}
