package algorithms.search;

import java.util.ArrayList;

public class Solution {
    private ArrayList<AState> path;

    public Solution(ArrayList<AState> sol) {
        if (sol == null)
            throw new NullPointerException("sol List is null");
        path=sol;
    }
    public ArrayList<AState> getSolutionPath() {
        return path;
    }
}
