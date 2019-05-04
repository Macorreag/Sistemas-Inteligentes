package SearchTreePractice.search.searchStrategies;

import SearchTreePractice.search.searchStrategies.dataStructures.Fringe;
import SearchTreePractice.search.searchStrategies.dataStructures.TSStack;

/**
 * DLS
 */
public class DLS<Action, State> extends Strategy<Action, State> {

    public DLS (int max_depth) {
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