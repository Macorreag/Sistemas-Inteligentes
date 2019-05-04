package SearchTreePractice.search.searchStrategies.dataStructures;

import java.util.Collection;
import java.util.Stack;

import SearchTreePractice.search.Node;

/**
 * TSStack
 */
public class TSStack<Action, State> implements Fringe<Action, State> {

    Stack<Node<Action, State>> stack;

    public TSStack() {
        this.stack = new Stack<>();
    }

    @Override
    public boolean addAll(Collection<Node<Action, State>> nodes) {
        return this.stack.addAll(nodes);
    }

    @Override
    public boolean addNode(Node<Action, State> node) {
        return this.stack.push(node) != null;
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public Node<Action, State> removeNode() {
        return this.stack.pop();
    }

}