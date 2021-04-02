package maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import algorithms.search.MazeState;

public class Maze3DState extends AState {
    private Position3D p;

    public Maze3DState(Position3D p) {
        this.p = p;
        this.positionValue = 0;
        this.father = null;
        this.visited = false;
    }

    public Maze3DState(Position3D p,int positionValue) {
        this.p = p;
        this.positionValue = positionValue;
        this.father = null;
        this.visited = false;
    }

    public void setPositionValue(int val) {
        positionValue = val;
    }
    public Position3D getPosition() {
        return p;
    }
    public boolean equals(Object other) {
        if (!(other instanceof Maze3DState))
            return false;
        return ((Maze3DState)other).getPosition().equals(p);
    }

    public String toString() {
        return "{"+p.getDepthIndex()+","+(p.getRowIndex())+","+(p.getColumnIndex())+"}";
    }

    public boolean isVisited() {
        return visited;
    }

}
