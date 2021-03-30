package algorithms.mazeGenerators;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;


public class MyMazeGenerator extends AMazeGenerator{
    private Position randomEdge(Maze maze) {
        Random rand=new Random();
        int edge = rand.nextInt(4);
        int row,col;
        if (edge<=1) { //Top or Bottom edge
            col=rand.nextInt(maze.getCols());
            if (edge==0)
                row=0;
            else
                row= maze.getRows()-1;
        }
        else { //Left or Right edge
            row=rand.nextInt(maze.getRows());
            if (edge==2)
                col=0;
            else
                col=maze.getCols()-1;
        }
        return new Position(row,col);
    }
    @Override
    public Maze generate(int row, int col) {
        Maze maze = new Maze(row,col);
        InitBoard(maze,1);
        Position start=randomEdge(maze);
        maze.setPositionValue(start,0);
        maze.setStart(start);



        Cell First = new Cell(start,GetMyNeibs(maze,start));
        Stack<Cell> stack = new Stack<Cell>();
        stack.push(First);
        DFS(maze,stack);
        maze.setGoal(new Position(8,8)); /////#######CHEckkkk
        return maze;
    }

    private void DFS(Maze maze, Stack<Cell> stack){
        //null check
        while(!stack.isEmpty()){
            Cell currentCell = stack.pop();
            if (currentCell.HaveNeighbours()){
                Position neighbour = currentCell.GetCellNeighbour();
                while(maze.getPositionValue(neighbour) != 1 && currentCell.HaveNeighbours())
                    neighbour = currentCell.GetCellNeighbour();
                if (maze.getPositionValue(neighbour) == 1){
                    stack.push(currentCell);
                    maze.setPositionValue(neighbour,0);
                    maze.setPositionValue(currentCell.getPosition().getBetween(neighbour),0);
                    stack.push(new Cell(neighbour,GetMyNeibs(maze,neighbour)));
                }
            }
        }
    }

    private LinkedList<Position> GetMyNeibs(Maze maze , Position position){
        if (position == null || maze == null)
            return null;
        LinkedList<Position> neibs = new LinkedList<Position>();
        Position up = new Position(position.getRowIndex()-2,position.getColumnIndex());
        Position down = new Position(position.getRowIndex()+2,position.getColumnIndex());
        Position left = new Position(position.getRowIndex(),position.getColumnIndex()-2);
        Position right = new Position(position.getRowIndex(),position.getColumnIndex()+2);
        if (maze.IsValidMove(up))
            neibs.add(up);
        if (maze.IsValidMove(down))
            neibs.add(down);
        if (maze.IsValidMove(left))
            neibs.add(left);
        if (maze.IsValidMove(right))
            neibs.add(right);
        Collections.shuffle(neibs,new Random());

        return neibs;





    }

    public static void main(String[] args) {
        MyMazeGenerator m=new MyMazeGenerator();
        Maze maze = m.generate(15,15);
        maze.print();

    }
}