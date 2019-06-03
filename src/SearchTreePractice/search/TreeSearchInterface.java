package search;

import java.util.LinkedList;

/**
 * TreeSearchInterface
 */
public interface TreeSearchInterface<Action, State> {

    public double stepCost(Node<Action, State> node);

    public boolean goalTest(State state);

    public LinkedList<LinkedList<Object>> successorFn(State state);
}