package SearchTreePractice.search.searchStrategies.dataStructures;

import java.util.Collection;

import SearchTreePractice.search.Node;

/**
 * Search
 */
public interface Fringe<Action, State> {

    public Node<Action, State> removeNode();

    public boolean addNode(Node<Action, State> node);

    public boolean isEmpty();

    public boolean addAll(Collection<Node<Action, State>> nodes);

}