package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.search.*;
import IO.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class ServerStrategySolveSearchProblem implements IServerStrategy{
    public static final String tempDirectoryPath = System.getProperty("java.io.tmpdir");
    private static volatile Object o = new Object();
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Maze maze;
            maze =(Maze) fromClient.readObject();
            Solution sol = findSolution(maze);
            //if the maze was solved - find the maze's solution in the files created by the server
            if (sol == null){
                SearchableMaze searchMaze = new SearchableMaze(maze);
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
            System.out.println(e.getStackTrace());
        }
    }

    private Solution findSolution(Maze maze) {
        try {
            long hashCode = maze.hashCode();
            String fileName = ""+hashCode;
            int fileNameCounter = 0;
            String name = tempDirectoryPath+"\\maze"+fileName+"-"+fileNameCounter+".txt";
            File hash = new File(name);

            while (hash.exists()) {
                synchronized (o) {
                    ObjectInputStream in = new ObjectInputStream(new FileInputStream(name));
                    ByteArrayInputStream byteIn = new ByteArrayInputStream((byte[]) in.readObject());
                    MyDecompressorInputStream decompress = new MyDecompressorInputStream(byteIn);
                    byte[] decompressedMaze = new byte[3000000];
                    decompress.read(decompressedMaze);
                    if (maze.equals(new Maze(decompressedMaze))) {
                        Solution sol = (Solution) in.readObject();
                        in.close();
                        decompress.close();
                        return sol;
                    }
                    in.close();
                    decompress.close();
                }
                fileNameCounter++;
                name = tempDirectoryPath+"\\maze"+fileName+"-"+fileNameCounter+".txt";
                hash = new File(name);
            }

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return null;
    }

    private void addSolution(Maze maze, Solution sol) {
        try {
            ByteArrayOutputStream byteStream;
            ObjectOutputStream out;
            long hashCode = maze.hashCode();
            String fileName = ""+hashCode;
            int fileNameCounter = 0;
            String name = tempDirectoryPath+"\\maze"+fileName+"-"+fileNameCounter+".txt";
            synchronized (o) {
                File hash = new File(name);
                while (hash.exists()) {
                    fileNameCounter++;
                    name = tempDirectoryPath + "\\maze" + fileName + "-" + fileNameCounter + ".txt";
                    hash = new File(name);
                }
                out = new ObjectOutputStream(new FileOutputStream(name));

                byteStream = new ByteArrayOutputStream();
                //choosing compressor
                MyCompressorOutputStream scos = new MyCompressorOutputStream(byteStream);
                scos.write(maze.toByteArray());
                scos.flush();
                scos.close();
                out.writeObject(byteStream.toByteArray());
                out.flush();

                out.writeObject(sol);
            }
            out.flush();
            out.close();
            byteStream.close();

        }
        catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }
}