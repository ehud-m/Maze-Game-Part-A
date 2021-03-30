package algorithms.mazeGenerators;

import java.util.LinkedList;
import java.util.List;

public class Cell {
    private Position position;
    private LinkedList<Position> neighbours;
    private int visited;


    public Cell(Position position, LinkedList<Position> neighbours) {
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
