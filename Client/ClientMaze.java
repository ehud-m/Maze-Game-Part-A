package Client;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.AState;
import algorithms.search.BestFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class ClientMaze {
    private InetAddress serverIP;
    private int serverPort;
    private Maze maze;
    private Solution firstSol;

    public ClientMaze(InetAddress serverIP, int serverPort, Maze maze,Solution firstSol) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.maze=maze;
        this.firstSol=firstSol;
    }

    public void start(){
        try(Socket serverSocket = new Socket(serverIP, serverPort)){
            System.out.println("connected to server - IP = " + serverIP + ", Port = " + serverPort);
            applyStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void applyStrategy(InputStream inFromServer, OutputStream outToServer) {
        try{
            ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
            toServer.writeObject(maze);
            toServer.flush();
            ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
            Solution sol = (Solution) fromServer.readObject();
            ArrayList<AState> arr1 = sol.getSolutionPath();
            ArrayList<AState> arr2 = firstSol.getSolutionPath();
            boolean same = true;
            if (arr1.size()!=arr2.size())
                System.out.println("Not Same Length!!!");
            else {
                for (int i = 0; i < arr1.size(); i++) {
                    if (!arr1.get(i).equals(arr2.get(i))) {
                        same=false;
                        break;
                    }
                }
                System.out.println("True only if solutions are the same: "+same);
            }
            fromServer.close();
            toServer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
