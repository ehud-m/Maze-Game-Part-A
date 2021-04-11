package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {

    private HashMap <AState,Integer> valueOfPos;

    public BestFirstSearch() {
        valueOfPos = new HashMap<>();
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
        if (!isVisit(state)){
            numberOfNodeEvaluated++;
            if (!valueOfPos.containsKey(state)) {
                ((PriorityQueue<AState>) queue).add(state);
                valueOfPos.put(state,state.getPositionValue());
            }
            else{
                int oldState = valueOfPos.get(state);
                if (state.getPositionValue() < oldState){
                    ((PriorityQueue<AState>) queue).remove(state);
                    ((PriorityQueue<AState>) queue).add(state);
                    valueOfPos.put(state,state.getPositionValue());
                }

            }

            //visit(state);
            //return;
        }
 /*       else if (s.getCurrentState().getPositionValue() < state.positionValue){
            visit(state);
            numberOfNodeEvaluated++;
            ((PriorityQueue<AState>) queue).remove(state);
            ((PriorityQueue<AState>) queue).add(state);
        }*/
    }

    protected AState dequeue(){
        AState state = ((PriorityQueue<AState>)queue).poll();
        visit(state);
        return state;
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
