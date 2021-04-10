package algorithms.mazeGenerators;

public class Position {
    private int row;
    private int column;




    public Position(int row, int column) {
        if (row <0 || column < 0)
            throw new IndexOutOfBoundsException("cannot have negative rows or columns");
        this.row = row;
        this.column = column;
    }



    public int getRowIndex() {
        return row;
    }
    public int getColumnIndex() {
        return column;
    }

    public boolean equals(Object other) {
        if (!(other instanceof Position))
            return false;
        return ((Position)other).row==this.row && ((Position)other).column==this.column;
    }

    public Position getBetween(Position p){
        if (p == null)
            throw new NullPointerException("Position is null");
        return new Position((p.getRowIndex()-row)/2+row,(p.getColumnIndex()-column)/2+column);

    }
}

