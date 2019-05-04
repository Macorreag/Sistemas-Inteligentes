package SearchTreePractice.search.searchStrategies.dataStructures;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import SearchTreePractice.search.Node;

/**
 * Queue
 */
public class TSQueue<Action, State> implements Fringe<Action, State> {

    Queue<Node<Action, State>> queue;

    public TSQueue() {
        this.queue = new LinkedList<>();
    }

    @Override
    public boolean addNode(Node<Action, State> node) {
        return this.queue.add(node);
    }

    @Override
    public Node<Action, State> removeNode() {
        return this.queue.poll();
    }

    @Override
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    @Override
    public boolean addAll(Collection<Node<Action, State>> nodes) {
        return this.queue.addAll(nodes);
    }

}