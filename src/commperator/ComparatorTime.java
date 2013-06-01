package commperator;

import history.CommandEntity;

import java.util.Comparator;
/**
 * Sortiert die Methodenaufrufe nach der Zeit.
 */
public class ComparatorTime implements Comparator<CommandEntity>{

    /*
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     * 
     */
    @Override
    public int compare(CommandEntity e1, CommandEntity e2)
    {
        if(e1.getCalendar().getTime().getTime() < e2.getCalendar().getTime().getTime())
            return -1;
        else if(e1.getCalendar().getTime() == e2.getCalendar().getTime())
            return 0;
        else
            return 1;
    }

}
