package search.searchStrategies;

import search.searchStrategies.dataStructures.Fringe;
import search.searchStrategies.dataStructures.TSStack;

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