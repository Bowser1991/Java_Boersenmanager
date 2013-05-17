package history;

import java.util.Comparator;

/*
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
        if(e1.getTime() < e2.getTime())
            return 1;
        else if(e1.getTime() == e2.getTime())
            return 0;
        else
            return 1;
    }

}
