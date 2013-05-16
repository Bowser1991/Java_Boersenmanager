package priceprovider;

import java.util.Comparator;
import asset.*;

public class ShareComparator implements Comparator<Share>{

    @Override
    public int compare(Share shareOne, Share shareTwo)
    {
        return shareOne.name.compareTo(shareTwo.name);
    }

}
