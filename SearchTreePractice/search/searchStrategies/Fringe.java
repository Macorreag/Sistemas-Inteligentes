import java.util.Collection;

/**
 * Search
 */
public interface Fringe {

    public Node removeNode();

    public boolean addNode(Node node);

    public boolean isEmpty();

    public boolean addAll(Collection<Node> nodes);

}