package algorithms.mazeGenerators;

import java.util.Arrays;

public abstract class AMazeGenerator implements IMazeGenerator{

        public long measureAlgorithmTimeMillis (int row,int col) throws Exception {
        long TotalTime = 0;
        if (row > 1 && col > 1 ){
            long time = System.currentTimeMillis();
            generate(row, col);
            TotalTime = System.currentTimeMillis() - time;
        }
        else
            throw new IllegalArgumentException("number not in range");

        return TotalTime;

    }

    protected void InitBoard(Maze maze,int number){
        if (maze == null)
            throw new NullPointerException("maze null pointer");
        int [][] map = maze.getMap();
        //this for loop fills the array with zeros.
        for (int [] rows : map) {
            Arrays.fill(rows, number);
        }

    }

}
