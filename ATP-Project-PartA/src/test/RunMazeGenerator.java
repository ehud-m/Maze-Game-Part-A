package test;
import algorithms.mazeGenerators.*;
import maze3D.IMazeGenerator3D;
import maze3D.Maze3D;
import maze3D.MyMaze3DGenerator;
import maze3D.Position3D;

public class RunMazeGenerator {
    public static void main(String[] args) {
     //   testMazeGenerator(new EmptyMazeGenerator());
     //   testMazeGenerator(new SimpleMazeGenerator());
     //   testMazeGenerator(new MyMazeGenerator());
        testMazeGenerator(new MyMaze3DGenerator());
    }
    private static void testMazeGenerator(IMazeGenerator3D mazeGenerator) {
// prints the time it takes the algorithm to run System.out.println(String.format("Maze generation time(ms): %s",
        System.out.println(mazeGenerator.measureAlgorithmTimeMillis(500/*rows*/,500/*columns*/,500));
// generate another maze
     //   Maze3D maze = mazeGenerator.generate(5,5,5);
// prints the maze
   //     maze.print();
// get the maze entrance
     //   Position3D startPosition = maze.getStartPosition();
// print the start position
    //    System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
// prints the maze exit position
     //   System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }
}