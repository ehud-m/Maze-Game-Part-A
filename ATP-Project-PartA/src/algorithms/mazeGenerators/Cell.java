package algorithms.mazeGenerators;

import java.util.LinkedList;


public class Cell {
    private Position position;
    private LinkedList<Position> neighbours;



    public Cell(Position position, LinkedList<Position> neighbours) {
        if (position == null || neighbours == null)
            throw new NullPointerException("position or LinkedList is null");
        this.position = position;
        this.neighbours = neighbours;
    }
    public boolean  HaveNeighbours(){
        if (neighbours.isEmpty())
            return false;

        return !(neighbours.isEmpty()) ;

    }

    public Position getPosition() {
        return position;
    }

    public Position GetCellNeighbour(){
        if (this.neighbours.isEmpty()){
            return null;
        }
        return this.neighbours.pop();
    }
}
