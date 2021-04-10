package algorithms.mazeGenerators;

public class Maze {
    private Position start;
    private Position goal;
    private int rows;
    private int cols;
    private int[][] map;

    public Maze(int rows, int cols) {
        if (rows < 2 && cols < 2 ){
            throw new IllegalArgumentException(String.format("cannot generate %d %d maze",rows,cols));
        }
        this.rows = rows;
        this.cols = cols;
        map = new int[rows][cols];
    }

    public void setStart(Position start) {
        if (start == null)
            throw new NullPointerException("start Position is null");
        if (!PositionInMaze(start))
            throw new IndexOutOfBoundsException("Start position out of maze");
        this.start = start;
    }

    public void setGoal(Position goal) {
        if (goal == null)
            throw new NullPointerException("Goal Position is null");
        if (!PositionInMaze(goal))
            throw new IndexOutOfBoundsException("Goal position out of maze");
        this.goal = goal;
    }

    public Position getStartPosition() {
        return start;
    }

    public Position getGoalPosition() {
        return goal;
    }



    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }


    public int[][] getMap() {
        return map;
    }

    private String getCellString(int row, int col) {
        if (start.getRowIndex()==row && start.getColumnIndex()==col)
            return "S";
        else if (goal.getRowIndex()==row && goal.getColumnIndex()==col)
            return "E";
        else
            return map[row][col]+"";
    }

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

    public void setPositionValue(Position pos, int num) {
        if (pos == null)
            throw new NullPointerException("Position is null");
        if (!PositionInMaze(pos))
            throw new IndexOutOfBoundsException("position out of maze");
        map[pos.getRowIndex()][pos.getColumnIndex()] = num;
    }

    public int getPositionValue(Position pos) {
        if (pos == null)
            throw new NullPointerException("Position is null");
        if (!PositionInMaze(pos))
            throw new IndexOutOfBoundsException("position out of maze");
        return map[pos.getRowIndex()][pos.getColumnIndex()];
    }

    public void setPositionValue(int row, int col, int num) {
        if (!PositionInMaze(row,col))
            throw new IndexOutOfBoundsException("position out of maze");
        map[row][col] = num;
    }

    public int getPositionValue(int row, int col) {
        if (!PositionInMaze(row,col))
            throw new IndexOutOfBoundsException("position out of maze");
        return map[row][col];
    }

    public boolean IsValidMove(Position position) {
        if (PositionInMaze(position)) {
            return (map[position.getRowIndex()][position.getColumnIndex()] == 1);
        }
        return false;
    }

    public boolean PositionInMaze(Position position) {
        if (position == null)
            throw new NullPointerException("Null Position");
        return (position.getColumnIndex() < cols && position.getRowIndex() < rows && position.getRowIndex() >= 0 && position.getColumnIndex() >= 0);
    }

    public boolean PositionInMaze(int row, int col) {
        return (col < cols && row < rows && row >= 0 && col >= 0);
    }
}
