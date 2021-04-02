package algorithms.search;

public abstract class AState {
    protected AState father;
    protected int positionValue;
    protected boolean visited;
    public void setFather(Object other){
        if (!(other instanceof MazeState))
            return;
        father = ((MazeState)other);
    }
    public void setVisited() {
        this.visited = true;
    }
    public int getPositionValue() {
        return positionValue;
    }
    public AState getFather() {
        return this.father;
    }
}
