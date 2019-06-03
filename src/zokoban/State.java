package zokoban;

import java.util.List;
import java.util.PriorityQueue;

public class State {
    
    public List<Integer> box_pos;
    public int zokoban_pos;
    
    public State(List<Integer> box_pos, int zokoban_pos) {
        this.box_pos = box_pos;
        this.zokoban_pos = zokoban_pos;
    }
    
}
