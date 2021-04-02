package maze3D;

import algorithms.mazeGenerators.Position;

public class Position3D {
    private int row;
    private int column;
    private int depth;

    public Position3D(int depth,int row, int column) {
        this.depth=depth;
        this.row = row;
        this.column = column;
    }

    public int getDepthIndex() {return depth;}
    public int getRowIndex() {
        return row;
    }
    public int getColumnIndex() {
        return column;
    }

    public boolean equals(Object other) {
        if (!(other instanceof Position))
            return false;
        return ((Position3D)other).depth==this.depth && ((Position3D)other).row==this.row && ((Position3D)other).column==this.column;
    }

    public Position3D getBetween(Position3D p){
        return new Position3D((p.depth-depth)/2+depth,(p.row-row)/2+row,(p.column-column)/2+column);
    }

}
