package maze3D;

import algorithms.mazeGenerators.Maze;

import java.util.Arrays;

public class AMaze3DGenerator implements IMazeGenerator3D {

    public long measureAlgorithmTimeMillis (int depth,int row,int col){
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

    protected void InitBoard(Maze3D maze3D, int number){
        int [][][] map3D = maze3D.getMap();
        //this for loop fills the array with zeros.
        for (int [][] map : map3D) {
            for(int [] rows: map)
                Arrays.fill(rows, number);
        }

    }

}
}
