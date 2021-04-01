package algorithms.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {

;
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
    protected void checkSuccessors() {
        for (AState successor: Alist ) {
            if (s.getFather() == successor)
                Alist.remove(successor);

        }
    }

    protected boolean isEmpty(){
        boolean res = (((PriorityQueue<AState>)queue).isEmpty());
        return res;
    }
  /*  protected boolean isVisit(AState state){
        return false;
    }*/
    protected boolean Solved(int flag) {
        return false;
    }
    protected void visitForBreadth( AState state){  }

    protected void visitForBest(AState state) { s.visit(state); }

    @Override
    public String getName() {
        return "Best First Search";
    }
 /*   protected void clean(AState state) {
        Iterator i = ((PriorityQueue<AState>)queue).iterator();
        while (i.hasNext()) {
            if (((AState) i).equals(state)) {
                Iterator temp = i;
                i.next();
                ((PriorityQueue<AState>)queue).remove(temp);
            }
        }
    }*/

}
