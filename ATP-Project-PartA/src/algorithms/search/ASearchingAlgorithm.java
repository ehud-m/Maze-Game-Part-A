package algorithms.search;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {

    protected long numberOfNodeEvaluated;
    protected HashSet<AState> visited;
    protected ISearchable s;
    protected AState goal;

    public ASearchingAlgorithm() {
        visited = new HashSet<>();
        numberOfNodeEvaluated=0;
    }


    protected void visit(AState state){
        if (state == null)
            throw new NullPointerException("state is null");
        visited.add(state);
    }
    protected boolean isVisit(AState state){
        if (state == null)
            throw new NullPointerException("state is null");
        return visited.contains(state);
    }

    protected boolean isSolved(AState state){
        if (state == null)
            throw new NullPointerException("state is null");
        return state.equals(s.getEnd());
    }

    protected ArrayList<AState> getSolution(AState goal){
        if (goal == null)
            throw new NullPointerException("Searchable is null");
        ArrayList<AState> lst = new ArrayList<AState>();
        s.changeState(s.getEnd());
        lst.add(s.getEnd());
        if (goal.getFather() == null)
            throw new IllegalArgumentException("maze Without Solution");
        while (goal.getFather() != null){
            lst.add(goal.getFather());
            s.changeState(goal.getFather());
            goal=goal.getFather();
        }
        Collections.reverse(lst);
        //s.clear();
        return lst;
    }

}
