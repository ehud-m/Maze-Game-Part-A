package algorithms.mazeGenerators;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;


public class MyMazeGenerator extends AMazeGenerator{
    private Random rand;

    public MyMazeGenerator() {
        rand=new Random();
    }

    private Position randomEdge(Maze maze) {
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
        //maze.setGoal(new Position(8,8)); /////#######CHEckkkk
        fixFrame(maze);
        setGoal(maze);
        return maze;
    }
    private void setGoal(Maze maze) {
        Position p;
        do {
            p=randomEdge(maze);
        }
        while (maze.getPositionValue(p)!=0 || p.equals(maze.getStartPosition()));
        maze.setGoal(p);
    }

/*
    private int fixRows(Maze maze, int[] Line, int flag,int row) {
        Random rand = new Random();
        int counter = 0;
        for (int i = 0; i < maze.getCols(); i++) {
            if (Line[i] == 1)
                counter++;
        }
        if (counter == maze.getCols() || counter == maze.getCols() - 1 ) { /////////////////bug
            counter = 0;
            for (int i = 0; i < maze.getCols(); i++) {
                if (maze.getPositionValue(new Position(row, i)) == 0) {
                    maze.setPositionValue(new Position(0, i), rand.nextInt(2));
                    counter = 1;
                }
            }
            return counter;
        }
        return  0;
    }

    private int fixLeftCols(Maze maze,int changes){
        Random rand = new Random();
        int [][] frame = maze.getMap();
        int counter = 0;
        for (int i = 0;i < maze.getRows(); i++) {
            if (frame[i][0] == 1)
                counter++;

        }
        if (counter == maze.getRows() - 1|| counter == maze.getRows()){
            for (int i = 0;i < maze.getRows(); i++) {
                if (maze.getPositionValue(new Position(i, 1)) == 0)
                    maze.setPositionValue(new Position(i,0),rand.nextInt(2));
            }
            return 1;
        }
        return  0;
    }
    private int fixRightCols(Maze maze,int changes){
        Random rand = new Random();
        int [][] frame = maze.getMap();
        int counter = 0;
        for (int i = 0;i < maze.getRows(); i++) {
            if (frame[i][maze.getCols()-1] == 1)
                counter++;

        }
        if (counter == maze.getRows() - 1 || counter == maze.getRows()){
            for (int i = 0;i < maze.getRows(); i++) {
                if (maze.getPositionValue(new Position(i, maze.getCols()-2)) == 0)
                    maze.setPositionValue(new Position(i,maze.getCols()-1),rand.nextInt(2));
            }
            return 1;
        }
        return  0;

    }*/

    private void fixFrame (Maze maze){
        if (checkLineOnes(maze,0,0,1,0))
            fixLine(maze,0,0,1,0);
        if (checkLineOnes(maze,maze.getRows()-1,0,1,0))
            fixLine(maze,maze.getRows()-1,0,1,0);
        if (checkLineOnes(maze,0,0,0,1))
            fixLine(maze,0,0,0,1);
        if (checkLineOnes(maze,0,maze.getCols()-1,0,1))
            fixLine(maze,0,maze.getCols()-1,0,1);
    }
    private boolean checkLineOnes(Maze maze,int rowInd,int colInd,int checkRow, int checkCol) {
        int size;
        if (checkCol==1)
            size=maze.getRows();
        else
            size=maze.getCols();
        for (int i = 1; i < size-1; i++) {
            if (maze.getPositionValue(rowInd+i*checkCol,colInd+i*checkRow)==0)
                return false;
        }
        return true;
    }

    private void fixLine(Maze maze,int rowInd,int colInd,int fixRow, int fixCol) {
        int size;
        if (fixCol==1)
            size=maze.getRows();
        else
            size=maze.getCols();
        for (int i = 0; i < size; i++) {
            if (hasOneNeib(maze,rowInd+i*fixCol,colInd+i*fixRow) && rand.nextInt(2)==0)
                maze.setPositionValue(rowInd+i*fixCol,colInd+i*fixRow,0);
        }
    }

    private boolean hasOneNeib(Maze maze,int row, int col) {
        int count=0;
        if (maze.PositionInMaze(row+1,col) && maze.getPositionValue(row+1,col)==0)
            count++;
            //return true;
        if (maze.PositionInMaze(row,col+1) && maze.getPositionValue(row,col+1)==0)
            count++;
            //return true;
        if (maze.PositionInMaze(row-1,col) && maze.getPositionValue(row-1,col)==0)
            count++;
            //return true;
        if (maze.PositionInMaze(row,col-1) && maze.getPositionValue(row,col-1)==0)
            count++;
            //return true;
        return count==1;
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
}