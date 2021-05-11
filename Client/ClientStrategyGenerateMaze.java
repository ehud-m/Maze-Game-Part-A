package Client;

import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.AState;
import algorithms.search.BestFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;
import java.util.ArrayList;

public class ClientStrategyGenerateMaze implements IClientStrategy{
    private static volatile Object o = new Object();
    public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
        try {
            ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
            //IMazeGenerator gen = new MyMazeGenerator();
            int[] rowCol ={10,10};
            toServer.writeObject(rowCol);
            toServer.flush();
            ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
            byte[] compressedMaze = (byte[]) fromServer.readObject(); //read generated maze (compressed with
            //MyCompressor) from server

            ByteArrayInputStream byteIn = new ByteArrayInputStream(compressedMaze);
            InputStream is = new MyDecompressorInputStream(byteIn);
            byte[] decompressedMaze = new byte[3000000 /*CHANGE
SIZE ACCORDING TO YOU MAZE SIZE*/]; //allocating byte[] for the decompressed
            //maze -
            is.read(decompressedMaze); //Fill decompressedMaze
            Maze maze = new Maze (decompressedMaze);
            synchronized (o) {maze.print();
            System.out.println("Done");}
            //////////////////////////
            is.close();
            fromServer.close();
            toServer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
