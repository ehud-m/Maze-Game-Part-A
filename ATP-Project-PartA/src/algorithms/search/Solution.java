package algorithms.search;

import java.util.ArrayList;

public class Solution {
    private ArrayList<AState> path;
    public Solution(ArrayList arr) {
        path=arr;
    }
    public ArrayList<AState> getSolutionPath() {
        return path;
    }
}
