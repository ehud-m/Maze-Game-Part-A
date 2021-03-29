package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AmazeGenerator {


    @Override
    public Maze generate(int row, int col) {
        if (row <= 0 && col <= 0 ){
            System.out.println("Generate Prob");
            //throw exception
        }
        else{
            Maze map = new Maze(row,col);
            Maze

        }
    }
}
