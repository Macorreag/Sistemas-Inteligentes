package zokoban;

import java.util.PriorityQueue;

public class Zokoban {
    char[][] map;
    
    public Zokoban(char[][] map) {
        this.map = map;
        readMap();
        
    }
    
    public State readMap(){
        State state;
        PriorityQueue<Integer> box_pos = new PriorityQueue<>(); 
        int zokoban_pos;
        
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == '%'){
                    box_pos.add(i*map[0].length + j);
                }
                if (map[i][j] == '&'){
                    zokoban_pos = i*map[0].length + j;
                }
            }
        }
        
        return new State(box_pos, zokoban_pos);
    }
    
}
