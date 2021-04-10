package maze3D;

public class Maze3D {
    private Position3D start;
    private Position3D goal;
    private int rows;
    private int cols;
    private int depth;
    private int[][][] map3D;

    public Maze3D(int depth,int rows, int cols) {
        if (depth < 1 || rows < 1 || cols < 1)
            throw new IllegalArgumentException("cant make 3D maze with this vars");
        this.rows = rows;
        this.cols = cols;
        this.depth = depth;
        this.map3D = new int[depth][rows][cols];
    }

    public void setStart(Position3D start) {
        if (start == null)
            throw new NullPointerException("start pos arg is null");
        this.start = start;
    }

    public void setGoal(Position3D goal) {
        if (goal == null)
            throw new NullPointerException("Goal pos arg is null");
        this.goal = goal;
    }

    public Position3D getStartPosition() {
        return start;
    }

    public Position3D getGoalPosition() {
        return goal;
    }

    public int getDepth() { return depth; }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }


    public int[][][] getMap() {
        return map3D;
    }

    private String getCellString(int depth,int row, int col) {
        if (start.getDepthIndex()==depth &&start.getRowIndex()==row && start.getColumnIndex()==col)
            return "S";
        else if (goal.getDepthIndex()==depth && goal.getRowIndex()==row && goal.getColumnIndex()==col)
            return "E";
        else
            return map3D[depth][row][col]+"";
    }

    public void print() {
        System.out.println("{");
        for (int i=0;i<depth;i++) {
            int j;
            for (j=0;j<rows;j++) {
                int k;
                System.out.print("{ ");
                for(k=0;k<cols;k++){
                    System.out.print(getCellString(i,j,k)+" ");
                }
                System.out.println("}");
            }
            if (i != depth-1) {
                for (int z = 0; z < cols * 2 + 3; z++)
                    System.out.print("-");
                System.out.println();
            }
        }
        System.out.println("}");

    }

    public void setPositionValue(Position3D pos, int num) {
        if (pos == null)
            throw new NullPointerException("pos arg is null");
        map3D[pos.getDepthIndex()][pos.getRowIndex()][pos.getColumnIndex()] = num;
    }

    public int getPositionValue(Position3D pos) {
        return map3D[pos.getDepthIndex()][pos.getRowIndex()][pos.getColumnIndex()];
    }

    public boolean IsValidMove(Position3D position) {
        if (position == null)
            throw new NullPointerException("position arg is null");
        if (PositionInMaze(position)) {
            return (map3D[position.getDepthIndex()][position.getRowIndex()][position.getColumnIndex()] == 1);
        }
        return false;
    }

    public boolean PositionInMaze(Position3D position) {
        if (position == null)
            throw new NullPointerException("position arg is null");
        return (position.getDepthIndex() >=0 && position.getDepthIndex() < depth && position.getColumnIndex() < cols && position.getRowIndex() < rows && position.getRowIndex() >= 0 && position.getColumnIndex() >= 0);
    }
}
