package algorithms.search;

import java.util.ArrayList;

public interface ISearchable {
    ArrayList<AState> getAllPossibleStates();
    boolean isSolved();
    void changeState(AState state);
}
