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
        if (s == null)
            throw new NullPointerException("Searchable is null");
        ArrayList<AState> lst = new ArrayList<AState>();
        s.changeState(s.getEnd());
        lst.add(s.getEnd());
        if (s.getFather() == null)
            throw new IllegalArgumentException("maze Without Solution");
        while (s.getFather() != null){
            lst.add(s.getFather());
            s.changeState(s.getFather());
        }
        Collections.reverse(lst);
        s.clear();
        return lst;
    }

}
