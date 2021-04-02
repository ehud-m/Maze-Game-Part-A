package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{
    private Position p;

    public MazeState(Position p) {
        this.p = p;
        this.positionValue = 0;
        this.father = null;
        this.visited = false;
    }

    public MazeState(Position p,int positionValue) {
        this.p = p;
        this.positionValue = positionValue;
        this.father = null;
        this.visited = false;
    }



    public void setPositionValue(int val) {
        positionValue = val;
    }



    public Position getPosition() {
        return p;
    }

    public boolean equals(Object other) {
        if (!(other instanceof MazeState))
            return false;
        return ((MazeState)other).getPosition().equals(p);
    }

    public String toString() {
        return "{"+(p.getRowIndex())+","+(p.getColumnIndex())+"}";
    }

    public boolean isVisited() {
        return visited;
    }


}
