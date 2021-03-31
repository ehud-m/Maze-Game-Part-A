package algorithms.search;



import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm
{
    //private AState pai;
    protected ArrayList<AState> Alist;
    protected Object queue;
    protected AState curr;




    public BreadthFirstSearch() {
        queue = new LinkedList<AState>();
        Alist = new ArrayList<>();
    }

    @Override
    public Solution solve(ISearchable s) {
        BFS(s);

        return (new Solution(getSolution(s)));
    }

    protected void enqueue(AState state){
        ((LinkedList<AState>)queue).add(state);
    }

    protected AState dequeue(){

        return ((LinkedList<AState>)queue).poll();

    }

    protected boolean isEmpty(){
        boolean res = (((Queue<AState>)queue).isEmpty());
        return res;
    }

    protected void BFS(ISearchable s){
        int flag = 0;
        curr = s.getstart();
        numberOfNodeEvaluated++;
        enqueue(curr);
        while (!isEmpty()){
            curr = dequeue();
            //numberOfNodeEvaluated++;
            s.changeState(curr);
            Alist = s.getAllSuccessors();
            for (AState state: Alist ) {
                if (!isVisit(s,state)) {
                    s.visit(state);
                    numberOfNodeEvaluated++;
                    if (s.isSolved(state)) {
                        //flag = 1;
                        s.setGoalState(state);
                        //numberOfNodeEvaluated++;
                        //break;
                    }
                    enqueue(state);
                }
            }
        //    if (flag == 1)
          //      break;
        }
    }

    @Override
    public String getName() {
        return "Breadth First Search";
    }

    @Override
    public long getNumberOfNodesEvaluated() {
        return numberOfNodeEvaluated;
    }

    protected boolean isVisit(ISearchable s,AState state){
        return s.isVisit(state);

    }
}

