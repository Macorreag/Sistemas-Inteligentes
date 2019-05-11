package search.searchStrategies;

import search.searchStrategies.dataStructures.Fringe;

/**
 * Strategy
 */
public abstract class Strategy<Action, State> {

    Fringe<Action, State> fringe;
    int num_iterations = 1, max_depth = Integer.MAX_VALUE;

    abstract public Fringe<Action, State> getFringe();

    /**
     * @return the max_depth
     */
    public int getMax_depth() {
        return max_depth;
    }

    /**
     * @return the num_iterations
     */
    public int getNum_iterations() {
        return num_iterations;
    }
    
}