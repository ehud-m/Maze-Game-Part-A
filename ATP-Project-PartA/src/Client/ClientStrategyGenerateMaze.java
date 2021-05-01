package Client;

import algorithms.mazeGenerators.Maze;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ClientStrategyGenerateMaze implements IClientStrategy {

    public void applyStrategy(InputStream inFromServer, OutputStream outToServer) {
        try {
            ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
            ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
            int [] rc = {10,10};
            toServer.writeObject(rc);
            toServer.flush();
            byte [] bMaze = (byte[])fromServer.readObject();
            Maze maze = new Maze(bMaze);
            maze.print();
            Thread.sleep(100);
            toServer.close();
            fromServer.close();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }
}
