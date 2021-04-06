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
    public static void main(String[] args) {

        MyMaze3DGenerator mg = new MyMaze3DGenerator();

        Maze3D maze = mg.generate(300,300, 300);
        SearchableMaze3D searchableMaze = new SearchableMaze3D(maze);
 //       maze.setGoal(new Position(9,9));
  //      maze.setStart(new Position(0,0));
   //     maze.print();

        solveProblem(searchableMaze, new BreadthFirstSearch());
        solveProblem(searchableMaze, new BestFirstSearch());
        solveProblem(searchableMaze, new DepthFirstSearch());

    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
//Solve a searching problem with a searcher
        long time = System.currentTimeMillis();
        Solution solution = searcher.solve(domain);
        time = System.currentTimeMillis() - time;
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
//Printing Solution Path
                System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        /*for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
        }*/
        System.out.println("Solution's length:" + solutionPath.size());
        System.out.println("The algorithm took "+((double)time/1000)+" Seconds"+solutionPath.get(solutionPath.size()-1));

    }
}