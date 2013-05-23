package history;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BuySellHistory {
    private List<CommandEntity> history = new LinkedList<CommandEntity>();

    /**
     * addHistory(String methodName, String shareName, String playerName, int amount)
     * Fügt zur Liste ein weiteres Element mit den jeweiligen Parametern hinzu           
     * @param methodName
     * @param shareName
     * @param playerName
     * @param amount
     */
    public void addHistory(String methodName, String shareName,
            String playerName, int amount)
    {
        history.add(new CommandEntity(methodName, playerName, shareName, amount));
    }

    /**
     * sortByTime()
     * Sortiert die Liste nach dem Zeitindex der einzelnen Elemente.
     */
    public void sortByTime()
    {
        Collections.sort(history, new ComparatorTime());
    }

    /**
     * sortByShareName()
     * Sortiert die Liste nach einem Aktienname.
     */
    public void sortByShareName()
    {
        Collections.sort(history, new ComparatorShareName());
    }

    /**
     * sortByAllShareName()
     * Sortiert die Liste nach dem Playernamen.
     */
    public void sortByAllShareName()
    {
        Collections.sort(history, new ComparatorAllShareName());
    }

    /**
     * sortByMethode()
     * Sortiert die Liste nach den Methodennamen.
     */
    public void sortByMethode()
    {
        Collections.sort(history, new ComparatorMethodName());
    }

    @Override
    public String toString()
    {
        String s = "";
        for (Iterator<CommandEntity> i = history.iterator(); i.hasNext();) {
            s = s + i.next().toString() + "\n";
        }
        return s;
    }
}
