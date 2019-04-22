import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Queue
 */
public class TSQueue implements Fringe {

    Queue<Node> queue;

    public TSQueue() {
        this.queue = new LinkedList<>();
    }

    @Override
    public boolean addNode(Node node) {
        return this.queue.add(node);
    }

    @Override
    public Node removeNode() {
        return this.queue.poll();
    }

    @Override
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    @Override
    public boolean addAll(Collection<Node> nodes) {
        return this.queue.addAll(nodes);
    }

}