package algorithms.search;

import sun.misc.Queue;

import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {

    private PriorityQueue<AState> queue;


    public BestFirstSearch() {
        this.queue = new PriorityQueue<AState>();
    }


}
