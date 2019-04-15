import java.util.LinkedList;

/**
 * Problem
 */
public abstract class Problem<Action, State> {

    State initialState, goalState;
    LinkedList<Action> actions;

    public Problem(State initalState, State goalState, LinkedList<Action> actions) {
        this.initialState = initalState;
        this.actions = actions;
        this.goalState = goalState;
    }

    public abstract boolean goalTest(State nodeState);

    public abstract LinkedList<Node> successorFn(State nodeState);

    public abstract boolean stateComparation(State state1, State state2);

}