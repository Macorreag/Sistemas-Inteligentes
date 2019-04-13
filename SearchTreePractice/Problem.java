import java.util.LinkedList;

/**
 * Problem
 */
public abstract class Problem <Action, State> {

    State initialState;

    public Problem(State initalState) {
        this.initialState = initalState;
    }

    public abstract boolean goalTest(State nodeState); 
    public abstract LinkedList successorFn(State nodeState);
    
}