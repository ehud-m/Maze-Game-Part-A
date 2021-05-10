package Server;

import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;
import algorithms.search.ISearchingAlgorithm;

public class SearchingAlogrithmFactory {
    public static ISearchingAlgorithm getSearchingAlgorithm(){
         switch (Configurations.getConfigInstance().loadProp().getProperty("mazeSearchingAlgorithm")){
                case "BreathFirstSearch":
                    return new BreadthFirstSearch();
                case "DepthFirstSearch":
                    return new DepthFirstSearch();
                default:
                    return new BestFirstSearch();
            }
    }
}
