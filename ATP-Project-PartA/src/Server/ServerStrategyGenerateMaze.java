package Server;

import java.io.*;
import java.util.Properties;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.*;

public class ServerStrategyGenerateMaze implements IServerStrategy{


    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            int [] rowCol = (int []) fromClient.readObject();

            switch (Configurations.getConfigInstance().loadProp().getProperty("mazeGeneratingAlgorithm")){
                case "MyMazeGenerator":
                    MyMazeGenerator mazeGen = new MyMazeGenerator();
                    Maze maze = mazeGen.generate(rowCol[0],rowCol[1]);
                    toClient.writeObject(maze.toByteArray());
                    toClient.flush();
                case "EmptyMazeGenerator":
                    EmptyMazeGenerator eMaze = new EmptyMazeGenerator();
                    Maze emptyMaze = eMaze.generate(rowCol[0],rowCol[1]);
                    toClient.writeObject(emptyMaze.toByteArray());
                    toClient.flush();
                case "SimpleMazeGenerator":
                    SimpleMazeGenerator sMaze = new SimpleMazeGenerator();
                    Maze simpleMaze = sMaze.generate(rowCol[0],rowCol[1]);
                    toClient.writeObject(simpleMaze.toByteArray());
                    toClient.flush();

            }

            //SimpleCompressorOutputStream mcos = new SimpleCompressorOutputStream(toClient);




        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
