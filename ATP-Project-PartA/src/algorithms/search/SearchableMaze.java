package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable{
    private boolean[][] visited;
    private MazeState startState;
    private MazeState goalState;
    private MazeState currentState;
    private Maze maze;

    public SearchableMaze(Maze maze) {
        visited=new boolean[maze.getRows()][maze.getCols()];
        for (int i=0;i<visited.length;i++) {
            for (int j=0;j<visited.length;j++) {
                visited[i][j]=false;
            }
        }
        this.maze=maze;
        startState=new MazeState(maze.getStartPosition());
        changeState(startState);
        goalState=new MazeState(maze.getGoalPosition());
    }

    private boolean checkPositionMovable(Position move) {
        return maze.PositionInMaze(move) && maze.getPositionValue(move)==0 && !visited[move.getRowIndex()][move.getColumnIndex()];
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
        return (visited[((MazeState)state).getPosition().getRowIndex()][((MazeState)state).getPosition().getColumnIndex()]);
    }
    public void visit(AState state){
        visited[((MazeState)state).getPosition().getRowIndex()][((MazeState)state).getPosition().getColumnIndex()] = true;
        ((MazeState)state).setFather(currentState);
    }

    @Override
    public void changeState(AState state) {
        currentState=(MazeState)state;
        Position p = currentState.getPosition();
        visited[p.getRowIndex()][p.getColumnIndex()]=true;
    }

    public MazeState getFather(){
        if (currentState.getFather() != null)
            return (currentState.getFather());
        return null;
    }






}
