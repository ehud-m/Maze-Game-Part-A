package algorithms.search;
import java.util.Comparator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
/**
 * represent searchable maze that can be solved by ASearchingAlgorithm
 */
public class SearchableMaze implements ISearchable{

    private MazeState startState;
    private MazeState goalState;
    private Maze maze;

    /**
     * constructor
     * @param maze represents a Maze
     */
    public SearchableMaze(Maze maze) {
        this.maze=maze;
        startState=new MazeState((maze.getStartPosition()),0);
        goalState = new MazeState((maze.getGoalPosition()),-1);
    }

    /**
     * checks if an Position in the maze is movable
     * @param move position that we want move to
     * @return true if move is valid position
     */
    private boolean checkPositionMovable(Position move) {
        return maze.PositionInMaze(move) && maze.getPositionValue(move)==0;// && !visited[move.getRowIndex()][move.getColumnIndex()].isVisited();
    }

    /**
     * add straight states to the successors list
     * @param rowInc incrementation of the rows
     * @param colInc incrementation of the columns
     * @param lst list to add the states to
     * @param curr The state to find it's successors
     */
    private void addStraightState(int rowInc,int colInc,ArrayList<AState> lst, AState curr) {
        MazeState currentState=(MazeState)curr;
        Position curPos=currentState.getPosition();
        Position move=new Position(curPos.getRowIndex()+rowInc,curPos.getColumnIndex()+colInc);
        //if the successor is movable, add it to the list of successors
        if (checkPositionMovable(move)) {
            //set the neighbours value according to the current state value
            MazeState state = new MazeState(move,currentState.getPositionValue()+10);
            state.setFather(currentState);
            lst.add(state);
        }
    }
    /**
     * add diagonal states to the successors list
     * @param rowInc incrementation of the rows
     * @param colInc incrementation of the columns
     * @param lst list to add the states to
     * @param curr The state to find it's successors
     */
    private void addDiagonalState(int rowInc,int colInc, ArrayList<AState> lst,AState curr) {
        MazeState currentState=(MazeState)curr;
        Position curPos=currentState.getPosition();
        Position diag=new Position(curPos.getRowIndex()+rowInc,curPos.getColumnIndex()+colInc);
        Position vert=new Position(curPos.getRowIndex()+rowInc,curPos.getColumnIndex());
        Position horiz=new Position(curPos.getRowIndex(),curPos.getColumnIndex()+colInc);
        //the diagonal position is movable only if theres a straight path to it from the current position
        if (checkPositionMovable(diag) && (checkPositionMovable(vert) || checkPositionMovable(horiz))) {
            MazeState state = new MazeState(diag,currentState.getPositionValue()+15);
            state.setFather(currentState);
            lst.add(state);
        }
    }
    /**
     * returns successors list of a given state
     * @param curr represent a general picture of specific move in
     *      *      *            searchable problem
     * @return successors list of a given state
     */
    public ArrayList<AState> getAllSuccessors(AState curr) {
        ArrayList<AState> states= new ArrayList<AState>();
        //check for all the possible 8 neighbours if they are movable from the current state
        addStraightState(-1,0,states,curr);
        addDiagonalState(-1,1,states,curr);
        addStraightState(0,1,states,curr);
        addDiagonalState(1,1,states,curr);
        addStraightState(1,0,states,curr);
        addDiagonalState(1,-1,states,curr);
        addStraightState(0,-1,states,curr);
        addDiagonalState(-1,-1,states,curr);
        return states;
    }
    /**
     * returns AState comparator
     * @return AState comparator
     */
    public Comparator<AState> getComperator() {
        return new AStateComperator();
    }
    /**
     * @return goal state for searching algorithm
     */
    @Override
    public MazeState getEnd() {
        return goalState;
    }

    /**
     * @return first state for searching algorithm
     */
    @Override
    public MazeState getstart() {
        return startState;
    }

}