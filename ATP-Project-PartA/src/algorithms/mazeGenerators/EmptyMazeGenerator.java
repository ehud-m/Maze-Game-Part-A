package algorithms.mazeGenerators;

import java.util.Arrays;

public class EmptyMazeGenerator extends AMazeGenerator {


    @Override
    public Maze generate(int row, int col) {
        Maze maze = null;
        if (row <= 0 && col <= 0 ){
            System.out.println("Generate Prob");
            //throw exception
        }
        else{
            maze = new Maze(row,col);
            int [][] map = maze.getMap();
            //this for loop fills the array with zeros.
            for (int [] rows : map) {
                Arrays.fill(rows, 0);
            }

            Position start = new Position(row/2,0);
            Position end = new Position(row/2,col-1);
            maze.setStart(start);
            maze.setGoal(end);
        }
        return maze;
    }
}
