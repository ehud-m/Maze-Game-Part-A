package Client;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.BestFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainClientSolveSame {
    public static void main(String[] args) {
        Maze maze = (new MyMazeGenerator()).generate(1000,1000);
        Solution sol = (new BestFirstSearch().solve(new SearchableMaze(maze)));
        try {
            for (int i = 0; i < 20; i++) {
                ClientMaze client = new ClientMaze(InetAddress.getLocalHost(), 5401,maze,sol);
                new Thread(()->{
                    client.start();
                }).start();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
