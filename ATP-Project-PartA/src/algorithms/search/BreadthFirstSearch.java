package algorithms.search;

import sun.misc.Queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class BreadthFirstSearch extends ASearchingAlgorithm
{
    //private AState pai;
    private ArrayList<AState> Alist;
    private LinkedList<AState> queue;
    private AState curr;
    private long numberOfNodeEvaluated;



    public BreadthFirstSearch() {

        Alist = new ArrayList<>();
        numberOfNodeEvaluated = 0;
    }

    @Override
    public Solution solve(ISearchable s) {
        queue = new LinkedList<AState>();
        BFS(s);
        getSolution(s);
        return (new Solution(Alist));
    }

    private void BFS(ISearchable s){
        int flag = 0;
        curr = s.getstart();
        queue.addLast(curr);
        while (!queue.isEmpty()){
            curr = queue.pollFirst();
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
                    queue.addLast(state);
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

