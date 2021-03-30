package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable{
    private MazeState startState;
    private MazeState goalState;
    private MazeState currentState;
    private Maze maze;

    public SearchableMaze(Maze maze) {
        this.maze=maze;
        currentState=new MazeState(maze.getStartPosition());
        startState=currentState;
        goalState=new MazeState(maze.getGoalPosition());
    }

    private boolean checkPositionMovable(Position move) {
        return maze.PositionInMaze(move) && maze.getPositionValue(move)==0;
    }

    private void addStraightState(int rowInc,int colInc,ArrayList<AState> lst) {
        Position cur=currentState.getPosition();
        Position move=new Position(cur.getRowIndex()+rowInc,cur.getColumnIndex()+colInc);
        if (checkPositionMovable(move))
            lst.add(new MazeState(move));
    }
    private void addDiagonalState(int rowInc,int colInc, ArrayList<AState> lst) {
        Position cur=currentState.getPosition();
        Position diag=new Position(cur.getRowIndex()+rowInc,cur.getColumnIndex()+colInc);
        Position vert=new Position(cur.getRowIndex()+rowInc,cur.getColumnIndex());
        Position horiz=new Position(cur.getRowIndex(),cur.getColumnIndex()+colInc);
        if (checkPositionMovable(diag) && (checkPositionMovable(vert) || checkPositionMovable(horiz)))
            lst.add(new MazeState(diag));
    }
    public ArrayList<AState> getAllPossibleStates() {
        ArrayList<AState> states= new ArrayList<AState>();
        addStraightState(0,1,states);
        addStraightState(0,-1,states);
        addStraightState(1,0,states);
        addStraightState(-1,0,states);
        addDiagonalState(1,1,states);
        addDiagonalState(-1,-1,states);
        addDiagonalState(-1,1,states);
        addDiagonalState(1,-1,states);
        return states;
    }
}
