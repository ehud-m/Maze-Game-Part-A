package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{
    private Position p;
    public MazeState(Position p) {
        this.p = p;
    }

    public Position getPosition() {
        return p;
    }

    public boolean equals(Object other) {
        if (!(other instanceof MazeState))
            return false;
        return ((MazeState)other).getPosition().equals(p);
    }
}
