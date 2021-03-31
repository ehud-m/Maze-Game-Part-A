package algorithms.search;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {


    public BestFirstSearch() {

        Alist = new ArrayList<>();
      //  numberOfNodeEvaluated = 0;
    }

    @Override
    public Solution solve(ISearchable s) {
        queue = new PriorityQueue<AState>(11,s.getComperator());
        return super.solve(s);
    }

    protected void enqueue(AState state){
        ((PriorityQueue<AState>)queue).add(state);
    }

    protected AState dequeue(){

        return ((PriorityQueue<AState>)queue).poll();

    }

    protected boolean isEmpty(){
        boolean res = (((PriorityQueue<AState>)queue).isEmpty());
        return res;
    }
    protected boolean isVisit(ISearchable s,AState state){
        return false;
    }

    @Override
    public String getName() {
        return "Best First Search";
    }
}
