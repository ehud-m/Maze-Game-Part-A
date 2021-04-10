package maze3D;

import algorithms.mazeGenerators.Maze;

import java.util.Arrays;

public abstract class AMaze3DGenerator implements IMazeGenerator3D {

    public long measureAlgorithmTimeMillis (int depth,int row,int col){
        if (depth < 1 || row < 1 || col < 1)
            throw new IllegalArgumentException("cant make 3D maze with this vars");
        long time = System.currentTimeMillis();
        generate(depth,row, col);
        long TotalTime = System.currentTimeMillis() - time;
        return TotalTime;
    }

    protected void InitBoard(Maze3D maze3D, int number){
        if (maze3D == null)
            throw new NullPointerException("maze arg is null");
        int [][][] map3D = maze3D.getMap();
        //this for loop fills the array with zeros.
        for (int [][] map : map3D) {
            for(int [] rows: map)
                Arrays.fill(rows, number);
        }

    }

}

