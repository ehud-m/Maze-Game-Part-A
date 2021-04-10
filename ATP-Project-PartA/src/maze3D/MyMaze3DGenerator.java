package maze3D;

import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;


public class MyMaze3DGenerator extends AMaze3DGenerator{

    private Random rnd;

    public MyMaze3DGenerator() {
        rnd=new Random();
    }
    private Position3D randomEdge(Maze3D maze) {
        if (maze == null)
            throw new NullPointerException("maze arg is null");
        int edge = rnd.nextInt(6);
        int depth,row,col;
        if (edge<=1) { //Top or Bottom edge
            col=rnd.nextInt(maze.getCols());
            depth=rnd.nextInt(maze.getDepth());
            if (edge==0)
                row=0;
            else
                row= maze.getRows()-1;
        }
        else if (edge <=3){ //Left or Right edge
            row=rnd.nextInt(maze.getRows());
            depth=rnd.nextInt(maze.getDepth());
            if (edge==2)
                col=0;
            else
                col=maze.getCols()-1;
        }
        else { //Left or Right edge
            row=rnd.nextInt(maze.getRows());
            col=rnd.nextInt(maze.getCols());
            if (edge==4)
                depth=0;
            else
                depth=maze.getDepth()-1;
        }
        return new Position3D(depth,row,col);
    }
    @Override
    public Maze3D generate(int depth,int row, int col) {
        if (depth < 1 || row < 1 || col < 1)
            throw new IllegalArgumentException("cant make 3D maze with this vars");
        int stepSize = 2;
        Maze3D maze = new Maze3D(depth,row,col);
        InitBoard(maze,1);
        Position3D start=randomEdge(maze);
        maze.setPositionValue(start,0);
        maze.setStart(start);
        Stack<Position3D> stack = new Stack<Position3D>();
        stack.push(start);
        DFS(maze,stack,stepSize);
        setGoal(maze);
        return maze;
    }
    private void setGoal(Maze3D maze) {
        if (maze == null)
            throw new NullPointerException("maze arg is null");
        Position3D p;
        do {
            p=randomEdge(maze);
        }
        while (maze.getPositionValue(p)!=0 || p.equals(maze.getStartPosition()));
        maze.setGoal(p);
    }

    private void DFS(Maze3D maze, Stack<Position3D> stack,int stepSize){
        if (maze == null || stack == null)
            throw new NullPointerException("some arg is null");
        //null check
        int [][][] map = maze.getMap();
        if (map == null)
                throw new NullPointerException("maze map is null");
        int posD,posR,posC,curD,curR,curC;
        LinkedList<Integer> neighbour = new LinkedList<Integer>();
        while(!stack.isEmpty()){
            Position3D currentCell = stack.pop();
            curD=currentCell.getDepthIndex();
            curC=currentCell.getColumnIndex();
            curR=currentCell.getRowIndex();
            Position3D neib = GetMyNeibs(map,neighbour,currentCell,stepSize);
            if (neib==null)
                continue;
            stack.push(currentCell);
            posD=neib.getDepthIndex();
            posR=neib.getRowIndex();
            posC=neib.getColumnIndex();
            map[posD][posR][posC] = 0;
            if (stepSize==3) {
                map[(posD - curD) / 3 * 2 + curD][(posR - curR) / 3 * 2 + curR][(posC - curC) / 3 * 2 + curC] = 0;
                map[(posD - curD) / 3 + curD][(posR - curR) / 3 + curR][(posC - curC) / 3 + curC] = 0;
            }
            else
                map[(posD-curD)/2+curD][(posR-curR)/2+curR][(posC-curC)/2+curC]=0;
            stack.push(neib);
            //}
        }
    }

    private Position3D GetMyNeibs(int[][][] map ,LinkedList<Integer> neibs, Position3D position,int stepSize){
        if (position == null)
            return null;
        int posD,posR,posC;
        posD=position.getDepthIndex();
        posR=position.getRowIndex();
        posC=position.getColumnIndex();
        neibs.clear();
        if (posD+stepSize<map.length && map[posD+stepSize][posR][posC]==1)
            neibs.add(1);
        if (posD-stepSize>=0 && map[posD-stepSize][posR][posC]==1)
            neibs.add(2);
        if (posR+stepSize<map[0].length  && map[posD][posR+stepSize][posC]==1)
            neibs.add(3);
        if ( posR-stepSize>=0 && map[posD][posR-stepSize][posC]==1)
            neibs.add(4);
        if ( posC+stepSize <map[0][0].length && map[posD][posR][posC+stepSize]==1)
            neibs.add(5); 
        if (posC-stepSize >=0 && map[posD][posR][posC-stepSize]==1)
            neibs.add(6);
        if (neibs.size()==0)
            return null;
        int choice = neibs.get(rnd.nextInt(neibs.size()));
        switch (choice) {
            case 1:
                return (new Position3D(posD+stepSize,posR,posC));
            case 2:
                return (new Position3D(posD-stepSize,posR,posC));
            case 3:
                return (new Position3D(posD,posR+stepSize,posC));
            case 4:
                return (new Position3D(posD,posR-stepSize,posC));
            case 5:
                return (new Position3D(posD,posR,posC+stepSize));
            default:
                return (new Position3D(posD,posR,posC-stepSize));
        }
    }
}