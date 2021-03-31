package algorithms.search;

import sun.misc.Queue;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm
{
    //private AState pai;
    protected ArrayList<AState> Alist;
    protected Object queue;
    protected AState curr;

    private long numberOfNodeEvaluated;



    public BreadthFirstSearch() {
        queue = new Queue<AState>();
        Alist = new ArrayList<>();
        numberOfNodeEvaluated = 0;
    }

    @Override
    public Solution solve(ISearchable s) {
        BFS(s);
        getSolution(s);
        return (new Solution(Alist));
    }

    protected void enqueue(AState state){
        ((Queue<AState>)queue).enqueue(state);
    }

    protected AState dequeue(){
        try {
            return ((Queue<AState>)queue).dequeue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected boolean isEmpty(){
        boolean res = (((Queue<AState>)queue).isEmpty());
        return res;
    }

    protected void BFS(ISearchable s){
        int flag = 0;
        curr = s.getstart();
        enqueue(curr);
        while (!isEmpty()){
            curr = dequeue();
            numberOfNodeEvaluated++;
            s.changeState(curr);
            Alist = s.getAllPossibleStates();
            for (AState state: Alist ) {
                if (!s.isVisit(state)) {
                    s.visit(state);
                    numberOfNodeEvaluated++;
                    if (s.isSolved(state)) {
                        flag = 1;
                        s.changeState(state);
                        break;
                    }
                    enqueue(state);
                }
            }
            if (flag == 1)
                break;
        }
    }

    private void getSolution(ISearchable s){
        Alist.clear();
        Alist.add(s.getCurrentState());
        // s.changeState(s.getEnd());
        while (s.getFather() != null){
            Alist.add(s.getFather());
            s.changeState(s.getFather());
        }
        Collections.reverse(Alist);
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

