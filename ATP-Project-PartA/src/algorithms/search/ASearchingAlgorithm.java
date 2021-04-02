package algorithms.search;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected long numberOfNodeEvaluated;
    public ASearchingAlgorithm() {
        numberOfNodeEvaluated=0;
    }
    protected ArrayList<AState> getSolution(ISearchable s){
        ArrayList<AState> lst = new ArrayList<AState>();
        s.changeState(s.getEnd());
        lst.add(s.getEnd());
        // s.changeState(s.getEnd());
        while (s.getFather() != null){
            lst.add(s.getFather());
            s.changeState(s.getFather());
        }
        Collections.reverse(lst);
        s.clear();
        return lst;
    }

}
