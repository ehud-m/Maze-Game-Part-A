package algorithms.mazeGenerators;

public class MyMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int row, int col) {
        Maze maze = null;
        if (row <= 0 && col <= 0 ){
            System.out.println("Generate Prob");
            //throw exception
        }
        else{
            maze = new Maze(row,col);
            InitBoard(maze,1);
        }
        return null;
    }

    public MyMazeGenerator() {
        return;

    }
}
