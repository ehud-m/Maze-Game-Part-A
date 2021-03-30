package algorithms.mazeGenerators;

public class Maze {
    private Position start;
    private Position goal;
    private int rows;
    private int cols;
    private int[][] map;

    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        map = new int[rows][cols];
    }

    public void setStart(Position start) {
        this.start = start;
    }

    public void setGoal(Position goal) {
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
}
