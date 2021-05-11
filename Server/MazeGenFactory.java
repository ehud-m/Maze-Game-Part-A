package Server;

import algorithms.mazeGenerators.*;

public class MazeGenFactory {

    public static IMazeGenerator MazeGen(String s) {
        switch (s) {
            case "MyMazeGenerator":
                return new MyMazeGenerator();
            case "EmptyMazeGenerator":
                return new EmptyMazeGenerator();
            default:
                return new SimpleMazeGenerator();


        }
    }
}
