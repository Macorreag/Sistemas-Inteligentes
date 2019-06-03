package sokoban;

import java.util.List;

public class State {
    
    public List<Integer> boxes_pos;
    public int zokoban_pos;
    
    public State(List<Integer> boxes_pos, int zokoban_pos) {
        this.boxes_pos = boxes_pos;
        this.zokoban_pos = zokoban_pos;
    }
    
}
