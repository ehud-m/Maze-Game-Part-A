package algorithms.search;

import java.util.ArrayList;
import java.util.Comparator;
public interface ISearchable {

    public ArrayList<AState> getAllSuccessors();
    void clear();
    public AState getCurrentState();
    public Comparator<AState> getComperator();
    public AState getEnd();
    public AState getstart();
    public void changeState(AState state);
    public boolean isSolved();
    public boolean isVisit(AState state);
    public void visit(AState state);
    public AState getFather();
    public int getNeibPrice(AState state);

}

