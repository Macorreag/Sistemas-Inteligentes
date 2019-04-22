import java.util.Collection;
import java.util.Stack;

/**
 * TSStack
 */
public class TSStack implements Fringe {

    Stack<Node> stack;

    public TSStack() {
        this.stack = new Stack<>();
    }

    @Override
    public boolean addAll(Collection<Node> nodes) {
        return this.stack.addAll(nodes);
    }

    @Override
    public boolean addNode(Node node) {
        return this.stack.push(node) != null;
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public Node removeNode() {
        return this.stack.pop();
    }

}