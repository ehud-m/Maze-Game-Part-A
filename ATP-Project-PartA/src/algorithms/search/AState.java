package algorithms.search;

public abstract class AState {
    protected AState father;
    protected int positionValue;
    protected boolean visited;


    public void setFather(AState other){
        father = other;
    }
 /*   public void setVisited(boolean visit) {
        this.visited = visit;
    }*/
    public int getPositionValue() {
        return positionValue;
    }
    public AState getFather() {
        return this.father;
    }
}
