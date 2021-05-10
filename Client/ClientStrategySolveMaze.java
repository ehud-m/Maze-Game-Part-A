package Client;

import algorithms.mazeGenerators.*;
import algorithms.search.AState;
import algorithms.search.BestFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ClientStrategySolveMaze implements IClientStrategy{
    @Override
    public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
        try {
            ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
            IMazeGenerator gen = new MyMazeGenerator();
            Maze maze = gen.generate(10,10);

            toServer.writeObject(maze);
            toServer.flush();
            ObjectInputStream fromServer = new ObjectInputStream(inFromServer);

            Solution sol = (Solution) fromServer.readObject();
            //System.out.println("Received Solution: " + sol.toString());

            ///solve by my self
            SearchableMaze search = new SearchableMaze(new Maze(maze.toByteArray()));
            BestFirstSearch bfs = new BestFirstSearch();
            Solution sol2 = bfs.solve(search);
            //System.out.println("Solution i found: "+sol2.toString());

            ArrayList<AState> arr1 = sol.getSolutionPath();
            ArrayList<AState> arr2 = sol2.getSolutionPath();
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
