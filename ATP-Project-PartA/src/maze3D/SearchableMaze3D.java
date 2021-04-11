package maze3D;

import algorithms.search.AState;
import algorithms.search.AStateComperator;
import algorithms.search.ISearchable;

import java.util.ArrayList;
import java.util.Comparator;

public class SearchableMaze3D implements ISearchable {

  //  private Maze3DState[][][] visited;
    private Maze3DState startState;
    private Maze3DState goalState;
    private Maze3DState currentState;
    private Maze3D maze;

    public SearchableMaze3D(Maze3D maze) {
        if (maze == null)
            throw new NullPointerException("maze is null");
        int[][][] map = maze.getMap();
        if (map == null)
            throw new NullPointerException("Mull maze map");
        this.maze=maze;
        startState=new Maze3DState(maze.getStartPosition(),0);
        changeState(startState);
        goalState= new Maze3DState(maze.getGoalPosition(),-1);

    }

    private boolean checkPositionMovable(Position3D move) {
        return maze.PositionInMaze(move) && maze.getPositionValue(move)==0;// && !visited[move.getRowIndex()][move.getColumnIndex()].isVisited();
    }

    private void addStraightState(int depthInc,int rowInc, int colInc, ArrayList<AState> lst) {
        Position3D cur=currentState.getPosition();
        Position3D move=new Position3D(cur.getDepthIndex()+depthInc,cur.getRowIndex()+rowInc,cur.getColumnIndex()+colInc);
        if (checkPositionMovable(move)) {
            Maze3DState state = new Maze3DState(move,currentState.getPositionValue()+10);
            state.setFather(currentState);
            lst.add(state);
        }
    }

    public Comparator<AState> getComperator(){
        return new AStateComperator();
    }
    public ArrayList<AState> getAllSuccessors() {
        ArrayList<AState> states= new ArrayList<AState>();
        addStraightState(0,0,1,states);
        addStraightState(0,0,-1,states);
        addStraightState(0,1,0,states);
        addStraightState(0,-1,0,states);
        addStraightState(1,0,0,states);
        addStraightState(-1,0,0,states);
        return states;
    }

    public Maze3DState getCurrentState() {
        return currentState;
    }

    @Override
    public Maze3DState getEnd() {
        return goalState;
    }

    @Override
    public Maze3DState getstart() {
        return startState;
    }


    @Override
    public void changeState(AState state) {
        if (state == null)
            throw new NullPointerException("Null state");
        currentState=(Maze3DState)state;
        Position3D p = currentState.getPosition();

    }


}
