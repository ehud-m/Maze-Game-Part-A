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
        if (s.isSolved())
            return new Solution(getSolution(s));
        else
            return new Solution(new ArrayList<AState>());
    }

    private boolean DFSIt(ISearchable s) {
        Stack<AState> stack = new Stack<AState>();
        stack.push(s.getstart());
        while ( !stack.isEmpty()) {
            AState current=stack.pop();
            if (s.isVisit(current) && !s.getstart().equals(current))
                continue;
            s.changeState(current);
            if (s.isSolved())
                break;
            numberOfNodeEvaluated++;
            ArrayList<AState> moves=s.getAllSuccessors();
            for (AState neib:moves) {
                stack.push(neib);
            }
        }
        return s.isSolved();
    }

    private boolean DFS(ISearchable s,ArrayList<AState> sol) {
        if (s.isSolved())
            return true;
        AState current=s.getCurrentState();
        ArrayList<AState> moves=s.getAllSuccessors();
        for (AState neib:
                moves) {
            s.changeState(neib);
            numberOfNodeEvaluated++;
            sol.add(neib);
            if (DFS(s,sol))
                return true;
            else {
                s.changeState(current);
                sol.remove(sol.size()-1);
            }
        }
        return false;
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
