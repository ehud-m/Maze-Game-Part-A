package algorithms.search;
/**
 * represent a general picture of specific move in a searchable problem
 */
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
