package algorithms.search;

import java.util.Comparator;

public abstract class AStateComperator implements Comparator<AState> {

    @Override
    public abstract int compare(AState o1, AState o2);


}
