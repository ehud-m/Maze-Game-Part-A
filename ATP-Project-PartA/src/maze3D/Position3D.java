package maze3D;

import algorithms.mazeGenerators.Position;

import java.util.Objects;

public class Position3D {
    private int row;
    private int column;
    private int depth;

    public Position3D(int depth,int row, int column) {
     /*   if (depth < 0 || row < 0 || column < 0)
            throw new IllegalArgumentException("position cant have negative vars");*/
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
        //null check??
        if (!(other instanceof Position3D))
            return false;
        return ((Position3D)other).depth==this.depth && ((Position3D)other).row==this.row && ((Position3D)other).column==this.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, depth);
    }

}
