package algorithms.search;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {


    public BestFirstSearch() {
        Alist = new ArrayList<>();
    }

    @Override
    public Solution solve(ISearchable s) {
        if (s == null)
            throw new NullPointerException("Searchable is null");
        queue = new PriorityQueue<AState>(11,s.getComperator());
        return super.solve(s);
    }

    protected void enqueue(AState state){
        if (state == null)
            throw new NullPointerException("state is null");
        if (isVisit(state)){
            numberOfNodeEvaluated++;
            ((PriorityQueue<AState>) queue).add(state);
            visit(state);
            return;
        }
        else if (s.getCurrentState().getPositionValue() < state.positionValue){
            visit(state);
            numberOfNodeEvaluated++;
            ((PriorityQueue<AState>) queue).remove(state);
            ((PriorityQueue<AState>) queue).add(state);
        }
    }

    protected AState dequeue(){
        return ((PriorityQueue<AState>)queue).poll();

    }


    protected boolean isEmpty(){
        boolean res = (((PriorityQueue<AState>)queue).isEmpty());
        return res;
    }


    @Override
    public String getName() {
        return "Best First Search";
    }

}
