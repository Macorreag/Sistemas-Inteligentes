package SearchTreePractice.search.searchStrategies;

import SearchTreePractice.search.searchStrategies.dataStructures.Fringe;
import SearchTreePractice.search.searchStrategies.dataStructures.TSStack;

/**
 * IDS
 */
public class IDS<Action, State> extends Strategy<Action, State> {

    public IDS (int max_depth) {
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