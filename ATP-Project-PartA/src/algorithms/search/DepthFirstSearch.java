package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;
/**
 * this class implements a Depth First Search algorithm by
 * using stack for any searchable problem
 *
 * Depth first search finding a path to the goal state
 */
public class DepthFirstSearch extends ASearchingAlgorithm {
    @Override

    /**
     * returns a solution path
     * @param s A searchable problem
     * @return path of the first solution path was found
     */
    public Solution solve(ISearchable s) {
        if (s == null)
            throw new NullPointerException("Searchable is null");
        this.s=s;
        goal = DFSIt();
        return new Solution(getSolution(goal));
    }
    /**
     * return's the AState that the algorithm is searching
     * implements DFS.
     * @return AState that the algorithm is searching
     */
    private AState DFSIt() {
        //A stack which is used for the DFS algorithm
        Stack<AState> stack = new Stack<AState>();
        stack.push(s.getstart());
        while ( !stack.isEmpty()) {
            AState current=stack.pop();
            //if this state was already visited - continue to the next state
            if (isVisit(current))
                continue;
            visit(current);
            if (isSolved(current))
                return current;
            numberOfNodeEvaluated++;
            //for every successor - if it wasn't visited yet - add it to the stack
            ArrayList<AState> moves=s.getAllSuccessors(current);
            for (AState neib:moves) {
                if(!isVisit(neib)) {
                    stack.push(neib);
                }
            }
        }
        return null;
    }
    /**
     * return the name of the searching algorithm
     * @return the name of the searching algorithm
     */
    @Override
    public String getName() {
        return "Depth First Search";
    }
}
