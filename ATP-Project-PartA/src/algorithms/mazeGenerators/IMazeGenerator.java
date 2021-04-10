package algorithms.mazeGenerators;

public interface IMazeGenerator {

    Maze generate(int row,int col) throws Exception;
    long measureAlgorithmTimeMillis (int row,int col) throws Exception;

}
