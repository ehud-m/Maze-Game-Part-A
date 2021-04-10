package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {
    @Override


    public Solution solve(ISearchable s) {
        if (s == null)
            throw new NullPointerException("Searchable is null");
        this.s=s;
        goal = DFSIt(this.s);
        return new Solution(getSolution(goal));
    }

    private AState DFSIt(ISearchable s) {
        //dont need to throw, its cannot be null
        Stack<AState> stack = new Stack<AState>();
        stack.push(s.getstart());
        visit(s.getstart());
        while ( !stack.isEmpty()) {
            AState current=stack.pop();
            if (isVisit(current) && !s.getstart().equals(current))
                continue;
            s.changeState(current);
            //current.setVisited(true);
            visit(current);
            if (isSolved(current))
                return current;
            numberOfNodeEvaluated++;
            ArrayList<AState> moves=s.getAllSuccessors();
            for (AState neib:moves) {
                if(!isVisit(neib)) {
                    stack.push(neib);
               //     neib.setFather(current);
                }
            }
        }
        return null;
    }



    @Override
    public String getName() {
        return "Depth First Search";
    }

    @Override
    public long getNumberOfNodesEvaluated() {
        return numberOfNodeEvaluated;
    }
}
