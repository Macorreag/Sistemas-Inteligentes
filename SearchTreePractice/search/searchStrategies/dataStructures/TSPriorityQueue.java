package search.searchStrategies.dataStructures;

import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;
// import searchTree.StateComparator;

import search.Node;

/**
 * TSPriorityQueue
 */
public class TSPriorityQueue<Action, State> implements Fringe<Action, State> {

    PriorityQueue<Node<Action, State>> pQueue;

    public TSPriorityQueue(Comparator<Node<Action, State>> c) {
        this.pQueue = new PriorityQueue<>(c);
    }

    @Override
    public boolean addNode(Node<Action, State> node) {
        return this.pQueue.add(node);
    }

    @Override
    public Node<Action, State> removeNode() {
        return this.pQueue.poll();
    }

    @Override
    public boolean isEmpty() {
        return this.pQueue.isEmpty();
    }

    @Override
    public boolean addAll(Collection<Node<Action, State>> nodes) {
        return this.pQueue.addAll(nodes);
    }

}