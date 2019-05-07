package search.searchStrategies;

import java.util.Comparator;
import search.Node;
import search.searchStrategies.dataStructures.Fringe;
import search.searchStrategies.dataStructures.TSPriorityQueue;

/**
 * Greddy
 */
public class Greddy<Action, State> extends Strategy<Action, State> {

    Heuristic<Action, State> heuristic;

    public Greddy(Heuristic<Action, State> heuristic) {
        this.heuristic = heuristic;
    }

    @Override
    public Fringe<Action, State> getFringe() {
        if (this.fringe == null) {
            Comparator<Node<Action, State>> c = (node1,
                    node2) -> (int) (this.heuristic.heuristic(node1) - this.heuristic.heuristic(node2));
            this.fringe = new TSPriorityQueue<>(c);
        }
        return this.fringe;
    }
}