package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm
{

    protected ArrayList<AState> Alist;
    protected Object queue;
    protected AState curr;

    public BreadthFirstSearch() {
        queue = new LinkedList<AState>();
        Alist = new ArrayList<>();
    }

    @Override
    public Solution solve(ISearchable s) {
        if (s == null)
            throw new NullPointerException("Searchable is null");
        this.s=s;
        goal = BFS();
      //  return null;
        return (new Solution(getSolution(goal)));
    }

    protected void enqueue(AState state) {
        if (state == null)
            throw new NullPointerException("state is null");
        if (!isVisit(state) ){
            visit(state);
            numberOfNodeEvaluated++;
            ((LinkedList<AState>) queue).add(state);
        }
    }
    protected AState BFS(){
        curr = s.getstart();
        numberOfNodeEvaluated++;
        enqueue(curr);
        while (!isEmpty()){
            curr = dequeue();
            s.changeState(curr);
            if (isSolved(curr))
                return curr;
            Alist = s.getAllSuccessors();
            for (AState state: Alist ) {
                enqueue(state);
            }
        }
        return null;
    }

    protected AState dequeue(){
        return ((LinkedList<AState>)queue).poll();
    }

    protected boolean isEmpty(){
        boolean res = (((Queue<AState>)queue).isEmpty());
        return res;
    }

    @Override
    public String getName() {
        return "Breadth First Search";
    }

    @Override
    public long getNumberOfNodesEvaluated() {
        return numberOfNodeEvaluated;
    }


}

