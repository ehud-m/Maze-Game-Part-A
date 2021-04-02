package algorithms.search;
import java.util.Comparator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable{
    private MazeState[][] visited;
    private MazeState startState;
    private MazeState goalState;
    private MazeState currentState;
    private Maze maze;

    public SearchableMaze(Maze maze) {
        visited=new MazeState[maze.getRows()][maze.getCols()];
        for (int i=0;i<visited.length;i++) {
            for (int j=0;j<visited.length;j++) {
                visited[i][j]=new MazeState(new Position(i,j),-1);
            }
        }
        this.maze=maze;
        startState=visited[maze.getStartPosition().getRowIndex()][maze.getStartPosition().getRowIndex()];
        changeState(startState);
        startState.setPositionValue(0);
        goalState=visited[maze.getGoalPosition().getRowIndex()][maze.getGoalPosition().getColumnIndex()];
        goalState.setPositionValue(-1);
    }

    private boolean checkPositionMovable(Position move) {
        return maze.PositionInMaze(move) && maze.getPositionValue(move)==0 ; // && !visited[move.getRowIndex()][move.getColumnIndex()].isVisited()
    }

    private void addStraightState(int rowInc,int colInc,ArrayList<AState> lst) {
        Position cur=currentState.getPosition();
        Position move=new Position(cur.getRowIndex()+rowInc,cur.getColumnIndex()+colInc);
        if (checkPositionMovable(move)) {
            MazeState state = visited[move.getRowIndex()][move.getColumnIndex()];
            lst.add(state);
        }
    }
    private void addDiagonalState(int rowInc,int colInc, ArrayList<AState> lst) {
        Position cur=currentState.getPosition();
        Position diag=new Position(cur.getRowIndex()+rowInc,cur.getColumnIndex()+colInc);
        Position vert=new Position(cur.getRowIndex()+rowInc,cur.getColumnIndex());
        Position horiz=new Position(cur.getRowIndex(),cur.getColumnIndex()+colInc);
        if (checkPositionMovable(diag) && (checkPositionMovable(vert) || checkPositionMovable(horiz))) {
            MazeState state = visited[diag.getRowIndex()][diag.getColumnIndex()];
            lst.add(state);
        }
    }
    public Comparator<AState> getComperator(){
        return new MazeStateComparator();
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
    public MazeState getEnd() {
        return goalState;
    }

    @Override
    public MazeState getstart() {
        return startState;
    }

    @Override
    public boolean isSolved() {
        return currentState.equals(goalState);
    }

    public boolean isSolved(AState state) {
        return ((MazeState)state).equals(goalState);
    }

    @Override
    public boolean isVisit(AState state) {
        return (visited[((MazeState)state).getPosition().getRowIndex()][((MazeState)state).getPosition().getColumnIndex()].isVisited());
    }
    public void visit(AState state){
        visited[((MazeState)state).getPosition().getRowIndex()][((MazeState)state).getPosition().getColumnIndex()].setVisited();
        state.setFather(currentState);
        ((MazeState) state).setPositionValue((currentState.getPositionValue()+getNeibPrice(state)));
    }

    public int getNeibPrice(AState state) {
        Position other = ((MazeState)state).getPosition();
        int rowVal = Math.abs(currentState.getPosition().getRowIndex()-other.getRowIndex());
        int colVal = Math.abs(currentState.getPosition().getColumnIndex()-other.getColumnIndex());
        if (rowVal > 1 || colVal > 1 )
            return -1;
        if ( rowVal + colVal == 1 )
            return 10;
        else if ( rowVal + colVal == 2 )
            return 15;

        return -1;
    }

    @Override
    public void changeState(AState state) {
        currentState=(MazeState)state;
        Position p = currentState.getPosition();
        visited[p.getRowIndex()][p.getColumnIndex()].setVisited();
    }

    public AState getFather(){
        if (currentState.getFather() != null)
            return (currentState.getFather());
        return null;
    }

    @Override
    public void setGoalState(AState state) {
        if (((MazeState)state).getPositionValue() < goalState.getPositionValue() || goalState.getPositionValue() == -1){
            goalState = ((MazeState) state);
        }
    }
}