import java.util.LinkedList;

/**
 * 8-Puzzle
 */
public class Puzzle<Action, State> extends Problem {

    public Puzzle(State initialState) {
        super(initialState);
        this.initialState = initialState;
    }

    @Override
    public boolean goalTest(Object nodeState) {
        return this.initialState.equals(nodeState);
    }

    @Override
    public LinkedList successorFn(Object nodeState) {
        //LinkedList action_resultList = new LinkedList<>();
        return null;
    }
    
}