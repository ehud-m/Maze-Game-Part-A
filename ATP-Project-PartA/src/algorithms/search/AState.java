package algorithms.search;

public abstract class AState {
    protected AState father;
    public void setFather(Object other){
        if (!(other instanceof MazeState))
            return;
        father = ((MazeState)other);
    }

    public AState getFather() {
        return this.father;
    }
}
