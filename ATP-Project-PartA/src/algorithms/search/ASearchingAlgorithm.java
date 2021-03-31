package algorithms.search;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected ArrayList<AState> getSolution(ISearchable s){
        ArrayList<AState> lst = new ArrayList<AState>();
        lst.add(s.getCurrentState());
        // s.changeState(s.getEnd());
        while (s.getFather() != null){
            lst.add(s.getFather());
            s.changeState(s.getFather());
        }
        Collections.reverse(lst);
        return lst;
    }
}
