package test;
import algorithms.mazeGenerators.*;
import algorithms.search.*;
import maze3D.Maze3D;
import maze3D.MyMaze3DGenerator;
import maze3D.Position3D;
import maze3D.SearchableMaze3D;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class RunSearchOnMaze {

    private static int counter;
    public static void main(String[] args) {
        counter = 0;
/*
        MyMazeGenerator mg = new MyMazeGenerator();
        try {
            Maze maze = mg.generate(1000,1000);
      //      maze.print();
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            solveProblem(searchableMaze, new BreadthFirstSearch());
            solveProblem(searchableMaze, new BestFirstSearch());
            solveProblem(searchableMaze, new DepthFirstSearch());
        } catch (Exception e) {
            e.printStackTrace();
        }

*/
        for (int i = 0; i < 1; i++) {
            MyMaze3DGenerator mg = new MyMaze3DGenerator();
            Maze3D maze = mg.generate(10,10, 10);
            SearchableMaze3D searchableMaze = new SearchableMaze3D(maze);
            solveProblem(searchableMaze, new BreadthFirstSearch());
            solveProblem(searchableMaze, new BestFirstSearch());
            solveProblem(searchableMaze, new DepthFirstSearch());
        }
        System.out.println(counter);



    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
//Solve a searching problem with a searcher
        long time = System.currentTimeMillis();
        Solution solution = searcher.solve(domain);
        if (solution.getSolutionPath().get(solution.getSolutionPath().size()-1).getPositionValue() > 0)
            counter++;
       // time = System.currentTimeMillis() - time;
     //   System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
//Printing Solution Path
        //        System.out.println("Solution path:");
       // ArrayList<AState> solutionPath = solution.getSolutionPath();
    /*    for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
        }
        System.out.println("Solution's length:" + solutionPath.size());
        System.out.println("The algorithm took "+((double)time/1000)+" Seconds");
        System.out.println(" Goal :"+solutionPath.get(solutionPath.size()-1) + "\nValue of Path: "+solution.getSolutionPath().get(solutionPath.size()-1).getPositionValue()+"\n");
*/
    }
}