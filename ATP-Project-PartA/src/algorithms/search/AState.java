package algorithms.search;

public abstract class AState {

    protected AState father;
    protected int positionValue;

    public void setFather(AState other){
        father = other;
    }
    public int getPositionValue() {
        return positionValue;
    }
    public AState getFather() {
        return this.father;
    }
}
