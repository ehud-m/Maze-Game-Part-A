package algorithms.search;



import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm
{
    //private AState pai;
    protected ArrayList<AState> Alist;
    protected Object queue;
    protected AState curr;
    protected ISearchable s;





    public BreadthFirstSearch() {
        queue = new LinkedList<AState>();
        Alist = new ArrayList<>();
    }

    @Override
    public Solution solve(ISearchable s) {
        this.s=s;
        BFS();
      //  return null;
        return (new Solution(getSolution(s)));
    }

    protected void enqueue(AState state) {
        //flag = 1; to save time we can use flag and break in this BFS***
        if (!s.isVisit(state) ){
            s.visit(state);
            numberOfNodeEvaluated++;
   //         if (s.isSolved(state) ) {
                //  if (!Solved(flag))
     //           s.setGoalState(state);
            //    return true;
                //  if (Solved(flag))
                //       break;;
                //numberOfNodeEvaluated++;
                //break;

            ((LinkedList<AState>) queue).add(state);
        }

     //   if (state.equals(s.getstart()) && a==1){ // to add the Start state in the first iteretion
    //        ((LinkedList<AState>) queue).add(state);
    //    }
    }

   //     return false;


    protected void BFS(){
        curr = s.getstart();
        numberOfNodeEvaluated++;
        enqueue(curr);
        while (!isEmpty()){
            curr = dequeue();
            //numberOfNodeEvaluated++;
            s.changeState(curr);
       //     s.visit(curr);
            //    visitForBest(curr);
            Alist = s.getAllSuccessors();
            //    checkSuccessors();
            for (AState state: Alist ) {
                enqueue(state);
                //    else
                //       clean(state);
            }
            //  if (flag == 1)
            //      break;
        }
    }




    protected AState dequeue(){

        return ((LinkedList<AState>)queue).poll();

    }

    protected boolean isEmpty(){
        boolean res = (((Queue<AState>)queue).isEmpty());
        return res;
    }



   // protected void clean(AState state) {
 //   }

    protected void checkSuccessors(ISearchable s) {
        for (AState successor: Alist ) {
            if (s.getFather() == successor)
                Alist.remove(successor);

        }
    }
    protected boolean Solved(int flag) {
        return flag == 1;
    }

    protected void visitForBest( AState curr) { }

    protected void visitForBreadth( AState state){ s.visit(state); }

  //  private void checkSuccessors(ISearchable s) { }

    @Override
    public String getName() {
        return "Breadth First Search";
    }

    @Override
    public long getNumberOfNodesEvaluated() {
        return numberOfNodeEvaluated;
    }


}

