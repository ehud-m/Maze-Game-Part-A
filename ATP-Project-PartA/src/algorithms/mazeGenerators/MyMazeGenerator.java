package algorithms.mazeGenerators;
import java.util.*;
/**
 * A maze generator which uses DFS generating algorithm to generate the maze
 */
public class MyMazeGenerator extends AMazeGenerator{
    private Random rand;
    //saves for each position in the maze the number of steps from it to the start position
    private int [][] discoveryTime;

    public MyMazeGenerator() {
        rand=new Random();
    }

    /**
     * creates a random position in the maze's frame
     * @param maze The maze which is generated
     * @return A random position in the maze's frame
     */
    private Position randomEdge(Maze maze) {
        //choose a random edge of the maze
        int edge = rand.nextInt(4);
        int row,col;

        if (edge<=1) { //Top or Bottom edge
            col=rand.nextInt(maze.getCols());
            //Top edge
            if (edge==0)
                row=0;
            //Bottom edge
            else
                row= maze.getRows()-1;
        }
        else { //Left or Right edge
            row=rand.nextInt(maze.getRows());
            //Left edge
            if (edge==2)
                col=0;
            //Right edge
            else
                col=maze.getCols()-1;
        }
        return new Position(row,col);
    }

    /**
     * creates a 2x2 maze from the maze it receives
     * @param maze The maze which will be changed
     * @return The maze created
     */
    private Maze createTwoOnTwo(Maze maze) {
        int [][] twoOnTwo = maze.getMap();
        twoOnTwo[0][0]=0;
        twoOnTwo[0][1]=0;
        twoOnTwo[1][0]=1;
        twoOnTwo[1][1]=0;
        maze.setGoal(new Position(1,1));
        maze.setStart(new Position(0,0));
        return maze;
    }

    @Override
    public Maze generate(int row, int col) {
        if (row < 2 || col < 2)
            throw new IllegalArgumentException("Invalid Size of Maze!");
        Maze maze = new Maze(row,col);
        discoveryTime = new int[maze.getRows()][maze.getCols()];
        InitBoard(maze,1);
        Position start;
        //generate a 2X2 maze
        if (row == 2 && col == 2)
            return createTwoOnTwo(maze);
        //in a 2x3 maze, we want to chose a start position which will have
        //a neighbour(in the corner) - so the maze will be solvable
        do {
            start = randomEdge(maze);
        }
        while ((GetMyNeibs(maze, start)).size() == 0);
        maze.setPositionValue(start,0);
        maze.setStart(start);
        //creates a Cell object of the start position - includes its possible neighbours
        Cell First = new Cell(start,GetMyNeibs(maze,start));
        //The stack which is used for the DFS algorithm
        Stack<Cell> stack = new Stack<Cell>();
        stack.push(First);
        DFS(maze,stack);
        fixFrame(maze);
        setGoal(maze);
        return maze;
    }

    /**
     * Sets the maze's goal position
     * @param maze The maze to set it's goal position
     */

    private void setGoal(Maze maze) {
        Position p;
        do {
            p=randomEdge(maze);
        }
        //The goal position must be in the edge of the maze, must be a passage, and be far enough from the start
        while (maze.getPositionValue(p)!=0 || p.equals(maze.getStartPosition()) || discoveryTime[p.getRowIndex()][p.getColumnIndex()] <= ((maze.getRows() + maze.getCols())/2));
        maze.setGoal(p);
    }

    /**
     * The dfs algorithm could create a maze with a frame with lines full of ones,
     * so this function fixes each line if needed
     * @param maze The maze which it's frame will be fixed
     */
    private void fixFrame (Maze maze){
        //first row
        if (checkLineOnes(maze,0,0,1,0))
            fixLine(maze,0,0,1,0);
        //last row
        if (checkLineOnes(maze,maze.getRows()-1,0,1,0))
            fixLine(maze,maze.getRows()-1,0,1,0);
        //first col
        if (checkLineOnes(maze,0,0,0,1))
            fixLine(maze,0,0,0,1);
        //last col
        if (checkLineOnes(maze,0,maze.getCols()-1,0,1))
            fixLine(maze,0,maze.getCols()-1,0,1);
    }

    /**
     * Checks if the line (row/col) in the maze has only 1 (only wall)
     * @param maze The maze to check
     * @param rowInd If row is checked, contains the index of the row, otherwise - 0
     * @param colInd If col is checked, contains the index of the col, otherwise - 0
     * @param checkRow 1 if a row is checked, otherwise - 0
     * @param checkCol 1 if a col is checked, otherwise - 0
     * @return True if the line has only walls, otherwise-false
     */
    private boolean checkLineOnes(Maze maze,int rowInd,int colInd,int checkRow, int checkCol) {
        int size;
        if (checkCol==1)
            size=maze.getRows();
        else
            size=maze.getCols();
        //dont check the first and last cell - it could be fixed already for other line
        for (int i = 1; i < size-1; i++) {
            //if a row is checked, checkCol=0 and only the row at rowInd will be checked
            //same for the column index
            //if the line has a passage(zero) in it, return false
            if (maze.getPositionValue(rowInd+i*checkCol,colInd+i*checkRow)==0)
                return false;
        }
        return true;
    }

    /**
     * fixes the line (row/col - depends on fixRow and fixCol values) at the frame
     * of the maze, which has only walls in it
     * @param maze The maze which should be fixed
     * @param rowInd If row is fixed, contains the index of the row, otherwise - 0
     * @param colInd If col is fixed, contains the index of the col, otherwise - 0
     * @param fixRow 1 if a row is fixed, otherwise - 0
     * @param fixCol 1 if a col is fixed, otherwise - 0
     */
    private void fixLine(Maze maze,int rowInd,int colInd,int fixRow, int fixCol) {
        int size;
        if (fixCol==1)
            size=maze.getRows();
        else
            size=maze.getCols();
        //if a row is fixed, checkCol=0 and only the row at rowInd will be fixed
        //same for the column index
        for (int i = 0; i < size; i++) {
            //if a position has exactly one neighbour, choose randomly if to break it
            if (hasOneNeib(maze,rowInd+i*fixCol,colInd+i*fixRow) && rand.nextInt(2)==0)
                maze.setPositionValue(rowInd+i*fixCol,colInd+i*fixRow,0);
        }
    }

    /**
     * Checks if a position in the maze has exactly one neighbour which is a passage
     * @param maze The maze to check
     * @param row The index of the row to check
     * @param col the index of the col to check
     * @return True if the position has one nieghbour, otherwise - false
     */
    private boolean hasOneNeib(Maze maze,int row, int col) {
        int count=0;
        if (maze.PositionInMaze(row+1,col) && maze.getPositionValue(row+1,col)==0)
            count++;
        if (maze.PositionInMaze(row,col+1) && maze.getPositionValue(row,col+1)==0)
            count++;
        if (maze.PositionInMaze(row-1,col) && maze.getPositionValue(row-1,col)==0)
            count++;
        if (maze.PositionInMaze(row,col-1) && maze.getPositionValue(row,col-1)==0)
            count++;
        return count==1;
    }

    /**
     * The main method of the algorithm. Using DFS to generate the maze
     * @param maze
     * @param stack The stack of the DFS algorithm, which replaces the recursion
     *              Should contain at the beginning the start Cell
     */
    private void DFS(Maze maze, Stack<Cell> stack){
        for (int [] rows : discoveryTime)
            Arrays.fill(rows, 0);
        while(!stack.isEmpty()){
            Cell currentCell = stack.pop();
            if (currentCell.HaveNeighbours()){
                Position neighbour = currentCell.GetCellNeighbour();
                //get a current Cells neighbour which is a wall (wasn't yet visited by the DFS)
                //If it doesn't exist - skip to the next Cell inside the stack
                while(maze.getPositionValue(neighbour) != 1 && currentCell.HaveNeighbours())
                    neighbour = currentCell.GetCellNeighbour();
                if (maze.getPositionValue(neighbour) == 1){
                    //Push the current cell because it may have more neighbours
                    stack.push(currentCell);
                    //break the neighbours wall, and the cell between currentCell and the neighbour
                    maze.setPositionValue(neighbour,0);
                    maze.setPositionValue(currentCell.getPosition().getBetween(neighbour),0);
                    discoveryTime[currentCell.getPosition().getBetween(neighbour).getRowIndex()][currentCell.getPosition().getBetween(neighbour).getColumnIndex()] = discoveryTime[currentCell.getPosition().getRowIndex()][currentCell.getPosition().getColumnIndex()]+1;
                    discoveryTime[neighbour.getRowIndex()][neighbour.getColumnIndex()] = discoveryTime[currentCell.getPosition().getRowIndex()][currentCell.getPosition().getColumnIndex()]+2;
                    //Push the neighbour which was discovered, and its neighbours to the stack (as Cell object)
                    stack.push(new Cell(neighbour,GetMyNeibs(maze,neighbour)));
                }
            }
        }
    }

    /**
     * Gets a position in the maze, and returns a randomly ordered list of its neighbours
     * which weren't yet visited. A neighbour is a position with a distance of 2 in one of the indexes
     * @param maze The maze to find the neighbours in
     * @param position The position which we search its neighbours
     * @return
     */
    private LinkedList<Position> GetMyNeibs(Maze maze , Position position){
        if (position == null || maze == null)
            return null;
        LinkedList<Position> neibs = new LinkedList<Position>();
        Position up = new Position(position.getRowIndex()-2,position.getColumnIndex());
        Position down = new Position(position.getRowIndex()+2,position.getColumnIndex());
        Position left = new Position(position.getRowIndex(),position.getColumnIndex()-2);
        Position right = new Position(position.getRowIndex(),position.getColumnIndex()+2);

        //add only positions which are in the maze, and werent yet visited (value=1)
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