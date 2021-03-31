package algorithms.search;

import java.util.Comparator;

public abstract class AStateComperator extends AState implements Comparator<AState> {
    @Override
    public int compare(AState o1, AState o2) {
        return 0;
    }
}
