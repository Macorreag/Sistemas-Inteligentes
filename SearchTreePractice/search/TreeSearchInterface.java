package search;

import java.util.LinkedList;

/**
 * TreeSearchInterface
 */
public interface TreeSearchInterface<State> {

    public double stepCost(Node node);

    public boolean goalTest(State state);

    public LinkedList successorFn(State node);
}