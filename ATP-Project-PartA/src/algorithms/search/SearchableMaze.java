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
    private MazeState currentState;
    private Maze maze;

    /**
     * constructor
     * @param maze represents a Maze
     */
    public SearchableMaze(Maze maze) {
        this.maze=maze;
        startState=new MazeState((maze.getStartPosition()),0);
        changeState(startState);
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
     */
    private void addStraightState(int rowInc,int colInc,ArrayList<AState> lst) {
        Position cur=currentState.getPosition();
        Position move=new Position(cur.getRowIndex()+rowInc,cur.getColumnIndex()+colInc);
        if (checkPositionMovable(move)) {
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
     */
    private void addDiagonalState(int rowInc,int colInc, ArrayList<AState> lst) {
        Position cur=currentState.getPosition();
        Position diag=new Position(cur.getRowIndex()+rowInc,cur.getColumnIndex()+colInc);
        Position vert=new Position(cur.getRowIndex()+rowInc,cur.getColumnIndex());
        Position horiz=new Position(cur.getRowIndex(),cur.getColumnIndex()+colInc);
        if (checkPositionMovable(diag) && (checkPositionMovable(vert) || checkPositionMovable(horiz))) {
            MazeState state = new MazeState(diag,currentState.getPositionValue()+15);
            state.setFather(currentState);
            lst.add(state);
        }
    }

    /**
     * returns AState comparator
     * @return AState comparator
     */
    public Comparator<AState> getComperator(){
        return new AStateComperator();
    }

    /**
     * returns successors list of a given state
     * @param state represent a general picture of specific move in
     *      *      *            searchable problem
     * @return successors list of a given state
     */
    public ArrayList<AState> getAllSuccessors(AState state) {
        ArrayList<AState> states= new ArrayList<AState>();
        addStraightState(-1,0,states);
        addDiagonalState(-1,1,states);
        addStraightState(0,1,states);
        addDiagonalState(1,1,states);
        addStraightState(1,0,states);
        addDiagonalState(1,-1,states);
        addStraightState(0,-1,states);
        addDiagonalState(-1,-1,states);
        return states;
    }
    /**
     * switch between current state to given state
     * @param state
     */
    @Override
    public void changeState(AState state) {
        if (state == null)
            throw new NullPointerException("state is null");
        currentState=(MazeState)state;
        Position p = currentState.getPosition();
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