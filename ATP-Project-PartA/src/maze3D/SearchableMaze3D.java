package maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.search.AState;
import algorithms.search.AStateComperator;
import algorithms.search.ISearchable;

import java.util.ArrayList;
import java.util.Comparator;

public class SearchableMaze3D implements ISearchable {

  //  private Maze3DState[][][] visited;
    private Maze3DState startState;
    private Maze3DState goalState;
    private Maze3D maze;

    public SearchableMaze3D(Maze3D maze) {
        if (maze == null)
            throw new NullPointerException("maze is null");
        int[][][] map = maze.getMap();
        if (map == null)
            throw new NullPointerException("Mull maze map");
        this.maze=maze;
        startState=new Maze3DState(maze.getStartPosition(),0);
        goalState= new Maze3DState(maze.getGoalPosition(),-1);

    }

    private boolean checkPositionMovable(Position3D move) {
        return maze.PositionInMaze(move) && maze.getPositionValue(move)==0;// && !visited[move.getRowIndex()][move.getColumnIndex()].isVisited();
    }

    private void addStraightState(int depthInc,int rowInc, int colInc, ArrayList<AState> lst, AState curr) {
        Maze3DState currentState = (Maze3DState) curr;
        Position3D curPos=currentState.getPosition();
        Position3D move=new Position3D(curPos.getDepthIndex()+depthInc,curPos.getRowIndex()+rowInc,curPos.getColumnIndex()+colInc);
        if (checkPositionMovable(move)) {
            Maze3DState state = new Maze3DState(move,currentState.getPositionValue()+10);
            state.setFather(currentState);
            lst.add(state);
        }
    }
    public Comparator<AState> getComperator() {
        return new AStateComperator();
    }

    public ArrayList<AState> getAllSuccessors(AState curr) {
        ArrayList<AState> states= new ArrayList<AState>();
        addStraightState(0,0,1,states,curr);
        addStraightState(0,0,-1,states,curr);
        addStraightState(0,1,0,states,curr);
        addStraightState(0,-1,0,states,curr);
        addStraightState(1,0,0,states,curr);
        addStraightState(-1,0,0,states,curr);
        return states;
    }
    @Override
    public Maze3DState getEnd() {
        return goalState;
    }

    @Override
    public Maze3DState getstart() {
        return startState;
    }
}
