package commperator;

import history.CommandEntity;

import java.util.Comparator;

/*
 * Sortiert nach Methodenname
 */
public class ComparatorMethodName implements Comparator<CommandEntity>{

    @Override
    public int compare(CommandEntity e1, CommandEntity e2)
    {
        return e1.getMethodName().compareTo(e2.getMethodName());
    }
    
}
