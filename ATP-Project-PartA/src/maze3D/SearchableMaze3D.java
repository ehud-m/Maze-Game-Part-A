package maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import algorithms.search.ISearchable;
import algorithms.search.MazeState;
import algorithms.search.MazeStateComparator;

import java.util.ArrayList;
import java.util.Comparator;

public class SearchableMaze3D implements ISearchable {

    private Maze3DState[][][] visited;
    private Maze3DState startState;
    private Maze3DState goalState;
    private Maze3DState currentState;
    private Maze3D maze;

    public void clear() {
        for (int i = 0; i < maze.getDepth(); i++) {
            for (int j = 0; j < maze.getRows(); j++) {
                for (int k = 0; k < maze.getCols(); k++) {
                    if (visited[i][j][k]!=null) {
                        visited[i][j][k].setFather(null);
                        visited[i][j][k].setVisited(false);
                        visited[i][j][k].setPositionValue(0);
                    }
                }
            }
        }
    }
    public SearchableMaze3D(Maze3D maze) {
        int[][][] map = maze.getMap();
        visited=new Maze3DState[maze.getDepth()][maze.getRows()][maze.getCols()];
        for (int i=0;i<maze.getDepth();i++) {
            for (int j=0;j<maze.getRows();j++) {
                for (int k = 0; k <maze.getCols() ; k++) {
                    if (map[i][j][k]==0)
                        visited[i][j][k] = new Maze3DState(new Position3D(i,j,k),-1);
                    else
                        visited[i][j][k]=null;
                }
            }
        }
        this.maze=maze;
        startState=visited[maze.getStartPosition().getDepthIndex()][maze.getStartPosition().getRowIndex()][maze.getStartPosition().getColumnIndex()];
        changeState(startState);
        startState.setPositionValue(0);
        goalState=visited[maze.getGoalPosition().getDepthIndex()][maze.getGoalPosition().getRowIndex()][maze.getGoalPosition().getColumnIndex()];
        goalState.setPositionValue(-1);
    }

    private boolean checkPositionMovable(Position3D move) {
        return maze.PositionInMaze(move) && maze.getPositionValue(move)==0;// && !visited[move.getRowIndex()][move.getColumnIndex()].isVisited();
    }

    private void addStraightState(int depthInc,int rowInc, int colInc, ArrayList<AState> lst) {
        Position3D cur=currentState.getPosition();
        Position3D move=new Position3D(cur.getDepthIndex()+depthInc,cur.getRowIndex()+rowInc,cur.getColumnIndex()+colInc);
        if (checkPositionMovable(move)) {
            Maze3DState state = visited[move.getDepthIndex()][move.getRowIndex()][move.getColumnIndex()];
            lst.add(state);
        }
    }

    public Comparator<AState> getComperator(){
        return new MazeStateComparator();
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
    public boolean isSolved() {
        return currentState.equals(goalState);
    }

    public boolean isSolved(AState state) {
        return ((Maze3DState)state).equals(goalState);
    }

    @Override
    public boolean isVisit(AState state) {
        return (visited[((Maze3DState)state).getPosition().getDepthIndex()][((Maze3DState)state).getPosition().getRowIndex()][((Maze3DState)state).getPosition().getColumnIndex()].isVisited());
    }
    public void visit(AState state){
        visited[((Maze3DState)state).getPosition().getDepthIndex()][((Maze3DState)state).getPosition().getRowIndex()][((Maze3DState)state).getPosition().getColumnIndex()].setVisited(true);
        if (!state.equals(currentState)) {
            state.setFather(currentState);
            ((Maze3DState)state).setPositionValue((currentState.getPositionValue()+getNeibPrice(state)));
        }
    }

    public int getNeibPrice(AState state) {
        return 10;
    }

    @Override
    public void changeState(AState state) {
        currentState=(Maze3DState)state;
        Position3D p = currentState.getPosition();

    }

    public AState getFather(){
        if (currentState.getFather() != null)
            return (currentState.getFather());
        return null;
    }
}
