package commperator;

import history.CommandEntity;

import java.util.Comparator;

/*
 * Sortiert nach Aktientyp (ShareName) und innerhalb des Aktientyps nach der Zeit
 */
public class ComparatorAllShareName implements Comparator<CommandEntity>{

    @Override
    public int compare(CommandEntity e1, CommandEntity e2)
    {
        return e1.getShare().name.compareTo(e2.getShare().name);
    }

}
