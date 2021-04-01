package algorithms.search;

import java.util.Comparator;

public class MazeStateComparator extends AStateComperator implements Comparator<AState>  {
    @Override
    public int compare(AState o1, AState o2) {
        if(((MazeState)o1).getPositionValue() > ((MazeState)o2).getPositionValue() ){
            return 1;
        }
        else if ( ((MazeState)o1).getPositionValue() < ((MazeState)o2).getPositionValue() )
            return -1;
        else if (((MazeState)o1).getDiscoveryTime() < ((MazeState)o2).getDiscoveryTime())
            return -1;
        else if (((MazeState)o1).getDiscoveryTime() > ((MazeState)o2).getDiscoveryTime())
            return 1;

        return 0;
    }
}
