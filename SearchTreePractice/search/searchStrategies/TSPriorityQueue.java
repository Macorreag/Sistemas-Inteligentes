package search.searchStrategies;

import java.util.Collection;
import search.*;
import java.util.Comparator;
import java.util.PriorityQueue;
// import searchTree.StateComparator;

/**
 * TSPriorityQueue
 */
public class TSPriorityQueue implements Fringe {

    PriorityQueue<Node> pQueue;

    public TSPriorityQueue() {
        Comparator<Node> c = (node1, node2) -> (int) (node1.pathCost - node2.pathCost);
        this.pQueue = new PriorityQueue<>(c);
    }

    @Override
    public boolean addNode(Node node) {
        return this.pQueue.add(node);
    }

    @Override
    public Node removeNode() {
        return this.pQueue.poll();
    }

    @Override
    public boolean isEmpty() {
        return this.pQueue.isEmpty();
    }

    @Override
    public boolean addAll(Collection<Node> nodes) {
        return this.pQueue.addAll(nodes);
    }

}