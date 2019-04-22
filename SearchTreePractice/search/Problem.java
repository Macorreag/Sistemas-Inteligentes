import java.util.LinkedList;

/**
 * Problem
 */
public abstract class Problem<Action, State> implements StateComparator<State>, TreeSearchInterface<State> {

    State initialState, goalState;
    LinkedList<Action> actions;

    public Problem(State initalState, State goalState, LinkedList<Action> actions) {
        this.initialState = initalState;
        this.actions = actions;
        this.goalState = goalState;
    }

}