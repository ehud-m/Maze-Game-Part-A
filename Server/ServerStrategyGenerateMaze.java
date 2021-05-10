package Server;

import java.io.*;
import java.util.Properties;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.*;

public class ServerStrategyGenerateMaze implements IServerStrategy{

    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            OutputStream scos;
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            int [] rowCol = (int []) fromClient.readObject();
            IMazeGenerator mazeGen;
            Maze maze;
            mazeGen = MazeGenFactory.MazeGen(Configurations.getConfigInstance().loadProp().getProperty("mazeGeneratingAlgorithm"));
            maze = mazeGen.generate(rowCol[0],rowCol[1]);


            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            //choosing compressor
            scos = CompressorFactory.getCompressor(byteStream);
            scos.write(maze.toByteArray());
            scos.flush();
            toClient.writeObject(byteStream.toByteArray());
            toClient.flush();
            fromClient.close();
            toClient.close();
            scos.close();

        } catch (IOException | ClassNotFoundException e) {

        }


    }
}
