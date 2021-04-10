package algorithms.search;
import java.util.Comparator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable{

    //private MazeState[][] visited;
    private MazeState startState;
    private MazeState goalState;
    private MazeState currentState;
    private Maze maze;

    public SearchableMaze(Maze maze) {
        this.maze=maze;
        startState=new MazeState((maze.getStartPosition()),0);
        changeState(startState);
        goalState = new MazeState((maze.getGoalPosition()),-1);
    }

    private boolean checkPositionMovable(Position move) {
        return maze.PositionInMaze(move) && maze.getPositionValue(move)==0;// && !visited[move.getRowIndex()][move.getColumnIndex()].isVisited();
    }

    private void addStraightState(int rowInc,int colInc,ArrayList<AState> lst) {
        Position cur=currentState.getPosition();
        Position move=new Position(cur.getRowIndex()+rowInc,cur.getColumnIndex()+colInc);
        if (checkPositionMovable(move)) {
            MazeState state = new MazeState(move,currentState.getPositionValue()+10);
            state.setFather(currentState);
            lst.add(state);
        }
    }
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
    public Comparator<AState> getComperator(){
        return new AStateComperator();
    }
    public ArrayList<AState> getAllSuccessors() {
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

    public MazeState getCurrentState() {
        return currentState;
    }
    @Override
    public void changeState(AState state) {
        if (state == null)
            throw new NullPointerException("state is null");
        currentState=(MazeState)state;
        Position p = currentState.getPosition();
    }
    @Override
    public MazeState getEnd() {
        return goalState;
    }

    @Override
    public MazeState getstart() {
        return startState;
    }
/*
    public void clear() {
        for (int j = 0; j < maze.getRows(); j++) {
            for (int k = 0; k < maze.getCols(); k++) {
                if (visited[j][k]!=null) {
                    visited[j][k].setFather(null);
                    visited[j][k].setVisited(false);
                    visited[j][k].setPositionValue(0);
                }
            }
        }
    }
*/

/*
    @Override
    public boolean isSolved() {
        return currentState.equals(goalState);
    }

    public boolean isSolved(AState state) {
        if (state == null)
            throw new NullPointerException("state is null");
        return ((MazeState)state).equals(goalState);
    }
/*
    @Override
    public boolean isVisit(AState state) {
        if (state == null)
            throw new NullPointerException("state is null");
        return (visited[((MazeState)state).getPosition().getRowIndex()][((MazeState)state).getPosition().getColumnIndex()].isVisited());
    }
    public void visit(AState state){
        if (state == null)
            throw new NullPointerException("state is null");
        visited[((MazeState)state).getPosition().getRowIndex()][((MazeState)state).getPosition().getColumnIndex()].setVisited(true);
        if (!state.equals(currentState)) {
            state.setFather(currentState);
            ((MazeState)state).setPositionValue((currentState.getPositionValue()+getNeibPrice(state)));
        }
    }

    public int getNeibPrice(AState state) {
        if (state == null)
            throw new NullPointerException("state is null");
        Position other = ((MazeState)state).getPosition();
        int rowVal = Math.abs(currentState.getPosition().getRowIndex()-other.getRowIndex());
        int colVal = Math.abs(currentState.getPosition().getColumnIndex()-other.getColumnIndex());
        if (rowVal > 1 || colVal > 1 )
            throw new IllegalArgumentException("state is not neighbour of current");
        if ( rowVal + colVal == 1 )
            return 10;
        else if ( rowVal + colVal == 2 )
            return 15;
        throw new IllegalArgumentException("sended current state");
    }
*/

/*
    public AState getFather(){
        if (currentState.getFather() != null)
            return (currentState.getFather());
        return null;
    }
*/
}