package algorithms.mazeGenerators;

import java.util.Arrays;

public abstract class AMazeGenerator implements IMazeGenerator{

        public long measureAlgorithmTimeMillis (int row,int col){
        long TotalTime = 0;
        if (row > 0 && col >0 ){
            long time = System.currentTimeMillis();
            generate(row, col);
            TotalTime = System.currentTimeMillis() - time;
        }
        else
            System.out.println("Number not in range");
            //throw Exception()

        return TotalTime;

    }

    protected void InitBoard(Maze maze,int number){
        int [][] map = maze.getMap();
        //this for loop fills the array with zeros.
        for (int [] rows : map) {
            Arrays.fill(rows, number);
        }

    }

}
