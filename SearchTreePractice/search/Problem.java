package search;

import java.util.LinkedList;

import search.searchStrategies.Heuristic;

/**
 * Problem
 */
public abstract class Problem<Action, State> implements StateComparator<State>, TreeSearchInterface<Action, State> {

    protected State initialState;
	protected State goalState;
    protected LinkedList<Action> actions;
    protected Heuristic<Action, State> heuristic;

    public Problem(State initialState, State goalState, LinkedList<Action> actions) {
        this.initialState = initialState;
        this.actions = actions;
        this.goalState = goalState;
    }

    /**
     * @param heuristic the heuristic to set
     */
    public void setHeuristic(Heuristic<Action, State> heuristic) {
        this.heuristic = heuristic;
    }

    /**
     * @return the goalState
     */
    public State getGoalState() {
        return goalState;
    }

}