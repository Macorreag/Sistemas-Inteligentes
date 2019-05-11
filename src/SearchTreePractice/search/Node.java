package search;

import java.util.LinkedList;

/**
 * Node
 */
public class Node<Action, State> {

    public Node<Action, State> parent;
    public LinkedList<Node<Action, State>> children;
    public int depth;
    public double pathCost;
    public State state;
    public Action action;

    public Node(Node<Action, State> parent, int depth, State state, double pathCost, Action action) {
        this.parent = parent;
        this.depth = depth;
        this.state = state;
        this.children = new LinkedList<>();
        this.action = action;
    }

}