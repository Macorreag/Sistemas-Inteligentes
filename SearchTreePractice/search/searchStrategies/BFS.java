package SearchTreePractice.search.searchStrategies;

import SearchTreePractice.search.searchStrategies.dataStructures.Fringe;
import SearchTreePractice.search.searchStrategies.dataStructures.TSQueue;

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