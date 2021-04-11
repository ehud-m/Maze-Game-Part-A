package algorithms.search;

import java.util.ArrayList;
import java.util.Comparator;
public interface ISearchable {

    public ArrayList<AState> getAllSuccessors();
    public Comparator<AState> getComperator();
    public AState getEnd();
    public AState getstart();
    public void changeState(AState state);

}

