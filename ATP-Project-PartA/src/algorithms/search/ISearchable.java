package algorithms.search;

import java.util.ArrayList;

public interface ISearchable {
    public ArrayList<AState> getAllPossibleStates();

    public AState getCurrentState();

    public AState getEnd();

    public AState getstart();

    public void changeState(AState state);

    public boolean isSolved();
    public boolean isSolved(AState state);
 //   public AState compare(AState state1,AState state2);

    public boolean isVisit(AState state);

    public void visit(AState state);

    public AState getFather();
}
