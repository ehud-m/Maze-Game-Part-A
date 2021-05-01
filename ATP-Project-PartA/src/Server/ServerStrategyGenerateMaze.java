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
            IMazeGenerator mazeGen = MazeGenFactory.MazeGenFactory(Configurations.getConfigInstance().loadProp().getProperty("mazeGeneratingAlgorithm"));
            Maze maze = mazeGen.generate(rowCol[0],rowCol[1]);
            SimpleCompressorOutputStream scos = new SimpleCompressorOutputStream(toClient);
            scos.write(maze.toByteArray());
            toClient.flush();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
