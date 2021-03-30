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
}
