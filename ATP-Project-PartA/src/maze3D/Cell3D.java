package maze3D;



import algorithms.mazeGenerators.Position;

import java.util.LinkedList;
import java.util.List;

public class Cell3D {
    private Position3D position;
    private LinkedList<Position3D> neighbours;
    private int visited;


    public Cell3D(Position3D position, LinkedList<Position3D> neighbours) {
        this.position = position;
        this.neighbours = neighbours;
    }
    public boolean  HaveNeighbours(){
        if (neighbours.isEmpty())
            return false;

        return !(neighbours.isEmpty()) ;

    }

    public Position3D getPosition() {
        return position;
    }

    public LinkedList<Position3D> GetCellNeighbour(){
        if (this.neighbours.isEmpty()){
            return null;
        }
        return this.neighbours;
    }
}
