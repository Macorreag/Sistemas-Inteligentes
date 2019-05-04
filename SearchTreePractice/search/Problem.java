package search;

import java.util.LinkedList;

/**
 * Problem
 */
public abstract class Problem<Action, State> implements StateComparator<State>, TreeSearchInterface<Action, State> {

    protected State initialState;
	protected State goalState;
    protected LinkedList<Action> actions;

    public Problem(State initalState, State goalState, LinkedList<Action> actions) {
        this.initialState = initalState;
        this.actions = actions;
        this.goalState = goalState;
    }

}