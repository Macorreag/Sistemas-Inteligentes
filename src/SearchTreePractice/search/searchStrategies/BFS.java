package search.searchStrategies;

import search.searchStrategies.dataStructures.Fringe;
import search.searchStrategies.dataStructures.TSQueue;

/**
 * BFS
 */
public class BFS<Action, State> extends Strategy<Action, State>{

    public BFS () {
        this(Integer.MAX_VALUE);
    }

    public BFS (int max_depth) {
        this.max_depth = max_depth;
    }

    @Override
    public Fringe<Action, State> getFringe() {
        if (this.fringe == null) {
            this.fringe = new TSQueue<>();
        }
        return this.fringe;
    }
}