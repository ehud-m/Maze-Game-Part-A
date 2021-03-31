package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{
    private Position p;

    private int positionValue;



    public MazeState(Position p,int positionValue) {
        this.p = p;
        this.positionValue = positionValue;
        this.father = null;
    }

    public int getPositionValue() {
        return positionValue;
    }
    public MazeState(Position p) {
        this.p = p;
        this.positionValue = 0;
        this.father = null;
    }


    public Position getPosition() {
        return p;
    }

    public boolean equals(Object other) {
        if (!(other instanceof MazeState))
            return false;
        return ((MazeState)other).getPosition().equals(p);
    }

    public String toString() {
        return "("+(p.getRowIndex()+1)+","+(p.getColumnIndex()+1)+")";
    }

 /*   public void print(){
        System.out.println("P: "+p.getRowIndex()+","+p.getColumnIndex());
    }*/

 /*
comperator! should be in mazeState
    @Override
    public AState compare(AState a, AState b) {
        int totalFromA,totalFromB,cRow,cCol,rowA,rowB,colA,colB;
        MazeState state1 = (MazeState)a;
        MazeState state2 = (MazeState)b;
        rowA = state1.getPosition().getRowIndex();
        rowB = state2.getPosition().getRowIndex();
        colA = state1.getPosition().getColumnIndex();
        colB = state2.getPosition().getColumnIndex();
        cRow = currentState.getPosition().getRowIndex();
        cCol = currentState.getPosition().getColumnIndex();
        totalFromA = Math.abs(cRow - rowA) + Math.abs(cCol-colA);
        totalFromB = Math.abs(cRow - rowB) + Math.abs(cCol-colB);

        if (totalFromA > totalFromB)
            return state1;
        else
            return state2;
    }
*/

}
