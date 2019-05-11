package search.searchStrategies;

import search.Node;
import search.Problem;

/**
 * AStarInterface
 */

public abstract class Heuristic<Action, State> {
    
    protected Problem<Action, State> problem;
    public Heuristic( Problem<Action, State> problem ){
        this.problem = problem;
    }

    abstract public double heuristic(Node<Action, State> node);

    
}