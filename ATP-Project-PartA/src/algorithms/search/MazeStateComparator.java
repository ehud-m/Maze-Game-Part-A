package algorithms.search;

import java.util.Comparator;

public class MazeStateComparator extends AStateComperator implements Comparator<AState>  {
    @Override
    public int compare(AState o1, AState o2) {
        if (o1 == null || o2 == null)
            throw new NullPointerException("null Astate");
        if(o1.getPositionValue() > o2.getPositionValue() ){
            return 1;
        }
        else if ( o1.getPositionValue() < o2.getPositionValue() )
            return -1;

        return 0;
    }
}
