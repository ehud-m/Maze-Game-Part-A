package algorithms.mazeGenerators;

import java.util.Arrays;

public class EmptyMazeGenerator extends AMazeGenerator {


    @Override
    public Maze generate(int row, int col) {
        Maze maze = null;
        if (row < 2 && col < 2 ){
            throw new IllegalArgumentException(String.format("cannot generate %d %d maze",row,col));
        }
        else{
            maze = new Maze(row,col);
            InitBoard(maze,0);
            Position start = new Position(row/2,0);
            Position end = new Position(row/2,col-1);
            maze.setStart(start);
            maze.setGoal(end);
        }
        return maze;
    }
}
