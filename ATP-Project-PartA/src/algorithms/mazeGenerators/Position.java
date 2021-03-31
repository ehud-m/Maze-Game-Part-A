package algorithms.mazeGenerators;



public class Position {
    private int row;
    private int column;

    public Position(int row, int column) {
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
        return new Position((p.getRowIndex()-row)/2+row,(p.getColumnIndex()-column)/2+column);

    }
}

