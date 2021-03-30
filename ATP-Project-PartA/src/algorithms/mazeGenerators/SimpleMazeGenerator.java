package algorithms.mazeGenerators;

import java.util.Random;
public class SimpleMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int row, int col) {
        Maze maze = new Maze(row,col);
        InitBoard(maze,2)
        createRoad(maze);
        randomCells(maze);
        return maze;
    }

    private void createRoad(Maze maze) {
        Position start = new Position(0,maze.getCols()/4);
        Position end= new Position(maze.getRows()-1,maze.getCols()/4*3);
        maze.setStart(start);
        maze.setGoal(end);
        int[][] map = maze.getMap();
        int i=0, j = maze.getCols()/4;
        map[i][j]=0;
        while (i!=maze.getRows()-1 || j!=maze.getCols()/4*3) {
            if (i<maze.getRows()-1) {
                i++;
                map[i][j]=0;
            }
            if (j<maze.getCols()/4*3) {
                j++;
                map[i][j]=0;
            }
        }
    }

    private void randomCells(Maze maze) {
        Random rand=new Random();
        int[][] map = maze.getMap();
        for(int i=0;i<map.length;i++) {
            for (int j=0;j<map[0].length;j++)
                if (map[i][j]==2)
                    map[i][j]=rand.nextInt(2);
        }
    }
}
