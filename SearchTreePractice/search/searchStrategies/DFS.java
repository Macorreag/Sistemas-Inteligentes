package search.searchStrategies;

import search.searchStrategies.dataStructures.Fringe;
import search.searchStrategies.dataStructures.TSStack;

/**
 * DFS
 */
public class DFS<Action, State> extends Strategy<Action, State>{

    public DFS () {
        this(Integer.MAX_VALUE);
    }

    public DFS (int max_depth) {
        this.max_depth = max_depth;
    }

    @Override
    public Fringe<Action, State> getFringe() {
        if (this.fringe == null) {
            this.fringe = new TSStack<>();
        }
        return this.fringe;
    }
}