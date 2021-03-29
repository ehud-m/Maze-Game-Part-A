package algorithms.mazeGenerators;

public class Maze {
    private Position start;
    private Position end;
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

    public void setEnd(Position end) {
        this.end = end;
    }

    public Position getStartPosition() {
        return start;
    }

    public Position getEndPosition() {
        return end;
    }

    private String getCellString(int row,int col) {
        if (start.getRowIndex()==row && start.getColumnIndex()==col)
            return "S";
        else if (end.getRowIndex()==row && end.getColumnIndex()==col)
            return "E";
        else
            return map[row][col]+"";
    }

    public void print() {
        for (int i=0;i<=map.length;i++) {
            int j;
            for (j=0;j<map[0].length-1;j++) {
                System.out.print(getCellString(i,j)+" ");
            }
            System.out.println(getCellString(i,j));
        }
    }
}
