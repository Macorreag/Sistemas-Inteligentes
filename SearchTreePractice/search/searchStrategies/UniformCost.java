package SearchTreePractice.search.searchStrategies;

import java.util.Comparator;

import SearchTreePractice.search.Node;
import SearchTreePractice.search.searchStrategies.dataStructures.Fringe;
import SearchTreePractice.search.searchStrategies.dataStructures.TSPriorityQueue;

/**
 * UniformCost
 */
public class UniformCost <Action, State> extends Strategy<Action, State> {

    public UniformCost () {
        this(Integer.MAX_VALUE);
    }

    public UniformCost (int max_depth) {
        this.max_depth = max_depth;
    }

    @Override
    public Fringe<Action, State> getFringe() {
        if (this.fringe == null) {
            Comparator<Node<Action, State>> c = (node1, node2) -> (int) (node1.pathCost - node2.pathCost);
            this.fringe = new TSPriorityQueue<>(c);
        }
        return this.fringe;
    }
}