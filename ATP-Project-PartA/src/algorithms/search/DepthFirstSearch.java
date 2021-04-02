package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {
    @Override
    /*public Solution solve(ISearchable s) {
        ArrayList<AState> sol=new ArrayList<AState>();
        sol.add(s.getstart());
        DFS (s,sol);
        return new Solution(sol);
    }*/

    public Solution solve(ISearchable s) {
        DFSIt(s);
        return new Solution(getSolution(s));
        /*if (s.isSolved())
            return new Solution(getSolution(s));
        else
            return new Solution(new ArrayList<AState>());*/
    }

    private boolean DFSIt(ISearchable s) {
        Stack<AState> stack = new Stack<AState>();
        stack.push(s.getstart());
        s.visit(s.getstart());
        while ( !stack.isEmpty()) {
            AState current=stack.pop();
            if (s.isVisit(current) && !s.getstart().equals(current))
                continue;
            s.changeState(current);
            current.setVisited();
            if (s.isSolved())
                break;
            numberOfNodeEvaluated++;
            ArrayList<AState> moves=s.getAllSuccessors();
            for (AState neib:moves) {
                if(!s.isVisit(neib)) {
                    stack.push(neib);
                    neib.setFather(current);
                }
            }
        }
        return s.isSolved();
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
