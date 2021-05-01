package algorithms.mazeGenerators;

/**
 * This class represents a maze.
 */
public class Maze {
    private Position start;
    private Position goal;
    private int rows;
    private int cols;
    private int[][] map;
 /*
    rows - 2 bytes
    cols -2 bytes
    start rows -2 bytes
    start cols - 2 bytes
    goal rows - 2 bytes
    goal cols - 2 bytes
    map starts with index 12
    starts with 1
    */

    public Maze(byte [] bMaze){
        if (bMaze == null)
            throw new NullPointerException("Byte array is null");
        this.rows = (int)bMaze[1]+((int)bMaze[0])*256;
        this.cols = (int)bMaze[3]+((int) bMaze[2])*256;
        this.start = new Position(((int)bMaze[4]*256+(int)bMaze[5]),(int)bMaze[6]*256+(int)bMaze[7]);
        this.goal = new Position((int)bMaze[8]*256+(int)bMaze[9],(int)bMaze[10]*256+(int)bMaze[11]);
        for (int i = 0; i < rows ; i++) {
            for (int j = 0; j < cols; j++) {
                map[i][j] = bMaze[12+j+i*cols]; // cast to int if there is a problem
            }
        }
    }
    /**
     * creates a new rowsXcols maze object
     * @param rows the number of maze's rows
     * @param cols the number of maze's cols
     */
    public Maze(int rows, int cols) {
        if (rows < 2 && cols < 2 ){
            throw new IllegalArgumentException(String.format("cannot generate %d %d maze",rows,cols));
        }
        this.rows = rows;
        this.cols = cols;
        map = new int[rows][cols];
    }

    public byte[] toByteArray(){
        byte[] bMaze = new byte[12+rows*cols];
        bMaze[0] = (byte) (rows/256);
        bMaze[1] = (byte) (rows%256);
        bMaze[2] = (byte) (cols/256);
        bMaze[3] = (byte) (cols%256);
        bMaze[4] = (byte) (start.getRowIndex()/256);
        bMaze[5] = (byte) (start.getRowIndex()%256);
        bMaze[6] = (byte) (start.getColumnIndex()/256);
        bMaze[7] = (byte) (start.getColumnIndex()%256);
        bMaze[8] = (byte) (goal.getRowIndex()/256);
        bMaze[9] = (byte) (goal.getRowIndex()%256);
        bMaze[10] = (byte) (goal.getColumnIndex()/256);
        bMaze[11] = (byte) (goal.getColumnIndex()%256);
        for (int i = 0; i < rows ; i++) {
            for (int j = 0; j < cols; j++) {
                bMaze[12+j+i*cols] = (byte) map[i][j];
            }
        }
        return bMaze;
    }

    /**
     * Sets the mazes start position
     * @param start The maze's start position
     */
    public void setStart(Position start) {
        if (start == null)
            throw new NullPointerException("start Position is null");
        if (!PositionInMaze(start))
            throw new IndexOutOfBoundsException("Start position out of maze");
        this.start = start;
    }

    /**
     * Sets the mazes goal position
     * @param goal The mazes goal position
     */
    public void setGoal(Position goal) {
        if (goal == null)
            throw new NullPointerException("Goal Position is null");
        if (!PositionInMaze(goal))
            throw new IndexOutOfBoundsException("Goal position out of maze");
        this.goal = goal;
    }

    /**
     * Returns the mazes start position
     * @return the mazes start position
     */
    public Position getStartPosition() {
        return start;
    }

    /**
     * Returns the mazes goal position
     * @return the mazes goal position
     */
    public Position getGoalPosition() {
        return goal;
    }

    /**
     * Returns the number of rows in the maze
     * @return the number of rows in the maze
     */
    public int getRows() {
        return rows;
    }

    /**
     * Returns the number of cols in the maze
     * @return the number of cols in the maze
     */
    public int getCols() {
        return cols;
    }

    /**
     * Returns the map of the maze, 1-for a wall and 0-for a passage
     * @return A 2D array which represents the maze.
     */
    public int[][] getMap() {
        return map;
    }

    /**
     * Returns a char which represents a specific maze cell
     * @param row The index of the cells row
     * @param col The index of the cells col
     * @return S-Start Position, E-End Position, 1-wall, 0-passage
     */
    private String getCellString(int row, int col) {
        if (start.getRowIndex()==row && start.getColumnIndex()==col)
            return "S";
        else if (goal.getRowIndex()==row && goal.getColumnIndex()==col)
            return "E";
        else
            return map[row][col]+"";
    }

    /**
     * Prints the maze
     */
    public void print() {
        for (int i=0;i<map.length;i++) {
            System.out.print("{ ");
            int j;
            for (j=0;j<map[0].length;j++) {
                System.out.print(getCellString(i,j)+" ");
            }
            System.out.println("}");
        }
    }

    /**
     * Sets a maze's position to a specific value
     * @param pos The position to change its value
     * @param num The new value. 1-wall, 0-passage
     */
    public void setPositionValue(Position pos, int num) {
        if (pos == null)
            throw new NullPointerException("Position is null");
        if (!PositionInMaze(pos))
            throw new IndexOutOfBoundsException("position out of maze");
        map[pos.getRowIndex()][pos.getColumnIndex()] = num;
    }

    /**
     * returns a maze's position value
     * @param pos The position to return its value
     * @return The positions value. 1-wall, 0-passage
     */
    public int getPositionValue(Position pos) {
        if (pos == null)
            throw new NullPointerException("Position is null");
        if (!PositionInMaze(pos))
            throw new IndexOutOfBoundsException("position out of maze");
        return map[pos.getRowIndex()][pos.getColumnIndex()];
    }

    /**
     * Sets a maze's position to a specific value
     * @param row The row index of the position
     * @param col The col index of the position
     * @param num The new value. 1-wall, 0-passage
     */
    public void setPositionValue(int row, int col, int num) {
        if (!PositionInMaze(row,col))
            throw new IndexOutOfBoundsException("position out of maze");
        map[row][col] = num;
    }

    /**
     * returns a maze's position value
     * @param row the row index of the position
     * @param col the col index of the position
     * @return The positions value. 1-wall, 0-passage
     */
    public int getPositionValue(int row, int col) {
        if (!PositionInMaze(row,col))
            throw new IndexOutOfBoundsException("position out of maze");
        return map[row][col];
    }

    /**
     * Checks if the position is movable for the generation algorithm - inside the maze and it's a wall
     * @param position The position to check
     * @return True if the position is movable, otherwise - false
     */
    public boolean IsValidMove(Position position) {
        if (PositionInMaze(position)) {
            return (map[position.getRowIndex()][position.getColumnIndex()] == 1);
        }
        return false;
    }

    /**
     * checks if the position is inside the maze
     * @param position the position to check
     * @return true if the position is inside the maze, otherwise - false
     */
    public boolean PositionInMaze(Position position) {
        if (position == null)
            throw new NullPointerException("Null Position");
        return (position.getColumnIndex() < cols && position.getRowIndex() < rows && position.getRowIndex() >= 0 && position.getColumnIndex() >= 0);
    }

    /**
     * checks if the position is inside the maze
     * @param row the row index of the position to check
     * @param col the col index of the position to check
     * @return true if the position is inside the maze, otherwise - false
     */
    public boolean PositionInMaze(int row, int col) {
        return (col < cols && row < rows && row >= 0 && col >= 0);
    }
}
