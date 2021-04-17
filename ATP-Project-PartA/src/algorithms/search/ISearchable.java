package algorithms.search;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * This interface represents a Searchable problem
 */
public interface ISearchable {
    /**
     * returns List of suitable successors.
     * @param state represent a general picture of specific move in
     *      *      *            searchable problem
     * @return List of suitable successors
     */
    public ArrayList<AState> getAllSuccessors(AState state);

    /**
     * return's AState comparator.
     * @return AState comparator
     */
    public Comparator<AState> getComperator();

    /**
     * @return goal state for searching algorithm
     */
    public AState getEnd();

    /**
     * @return first state for searching algorithm
     */
    public AState getstart();

    /**
     * switch between current state to given state
     * @param state
     */
    public void changeState(AState state);

}

