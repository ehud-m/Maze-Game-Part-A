package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.search.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerStrategySolveSearchProblem implements IServerStrategy{
    public static final String tempDirectoryPath = System.getProperty("java.io.tmpdir");
    public static volatile AtomicInteger solutionCounter=new AtomicInteger(0);
    //Maps between a maze, to its file number - mazeNumber.txt
    public static volatile  ConcurrentHashMap<Maze,Integer> hasSolution = new ConcurrentHashMap<>();
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Maze maze = (Maze)fromClient.readObject();
            Solution sol;
            //if the maze was solved - find the maze's solution in the files created by the server
            if (solvedAlready(maze))
                sol = findSolution(maze);
                //solve the maze and save its solution
            else {
                SearchableMaze searchMaze = new SearchableMaze(maze);
                ////////add choice to searching algorithm

               ISearchingAlgorithm searcher = SearchingAlogrithmFactory.getSearchingAlgorithm();
               sol=searcher.solve(searchMaze);
               addSolution(maze,sol);
            }
            toClient.writeObject(sol);
            toClient.flush();
            toClient.close();
            fromClient.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private Solution findSolution(Maze maze) {
        //the number of the mazes solution file
        int solutionNum = hasSolution.get(maze);
        ArrayList<AState> arr = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(tempDirectoryPath+"\\maze"+solutionNum+".txt")));
            String str;
            while ((str=reader.readLine())!=null) {
                int seperatorIndex = str.indexOf(",");
                int x=Integer.parseInt(str.substring(1,seperatorIndex));
                int y=Integer.parseInt(str.substring(seperatorIndex+1,str.length()-1));
                arr.add(new MazeState(new Position(x,y),0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Solution(arr);
    }

    private boolean solvedAlready(Maze maze) {
        return hasSolution.containsKey(maze);
    }

    private void addSolution(Maze maze, Solution sol) {
        try {
            BufferedWriter out = new BufferedWriter(new PrintWriter(new FileOutputStream(tempDirectoryPath+"\\maze"+solutionCounter+".txt")));
            ArrayList<AState> arr = sol.getSolutionPath();
            for (int i = 0; i < arr.size(); i++) {
                out.write(arr.get(i).toString()+"\n");
            }
            /*File f = new File(tempDirectoryPath+"\\maze"+solutionCounter+".txt");
            f.createNewFile();
            FileWriter writer = new FileWriter(tempDirectoryPath+"\\maze"+solutionCounter+".txt");
            ArrayList<AState> arr = sol.getSolutionPath();
            for (int i = 0; i < arr.size(); i++) {
                writer.write(arr.get(i).toString()+"\n");
            }*/
            out.flush();

            hasSolution.put(maze,solutionCounter.getAndIncrement());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}