package search;

import java.util.LinkedList;

/**
 * Problem
 */
public abstract class Problem<Action, State> implements StateComparator<State>, TreeSearchInterface<State> {

    public State initialState, goalState;
    public LinkedList<Action> actions;

    public Problem(State initalState, State goalState, LinkedList<Action> actions) {
        this.initialState = initalState;
        this.actions = actions;
        this.goalState = goalState;
    }

}