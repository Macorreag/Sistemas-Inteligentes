package sokoban;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import search.Node;
import search.Problem;
import search.TreeSearch;
import search.searchStrategies.BFS;
import sokoban.sokoban_position_compute.BestPlayerPos;

public class Sokoban {
    char[][] map;
    SokobanSolver zs;
    List<Integer> walls_pos;
    
    public Sokoban(char[][] map) {
        this.map = map;
        this.walls_pos = new ArrayList<>();
    }
    
    private State readMap(){
        List<Integer> boxes_pos = new LinkedList<>(); 
        int zokoban_pos = -1;
        
        for (int i = 0; i < this.map.length; i++) {
            for (int j = 0; j < this.map[0].length; j++) {
                if (this.map[i][j] == '#') {
                    this.walls_pos.add(i*this.map[0].length+j);
                    continue;
                }
                if (this.map[i][j] == '%'){
                    boxes_pos.add(i*this.map[0].length + j);
                    continue;
                }
                if (this.map[i][j] == '&'){
                    zokoban_pos = i*this.map[0].length + j;
                }
            }
        }
        
        return new State(boxes_pos, zokoban_pos);
    }

    private void printPath (Node<Byte, Integer> result) {
        if (result == null) {
            System.out.println("No hay solución para el estado inicial dado.");
            return;
        }

        while (result != null) {
            System.out.println("----------level " + result.depth + "----------");
            // System.out.println(result.action);
            System.out.println(result.state + " Action: " + result.action);
            result = result.parent;
        }
    }

    public void /*Node<Byte, Integer> */solve() {
        Node<Byte, Integer> result = null;
        State state = readMap();
        Byte[] actions = new Byte[4]; // 1: UP; 2: DOWN; 3: LEFT; 4: RIGHT.
        for (byte i = 0; i < 4; i++) {
            actions[i] = (byte) (i + 1);
        }
        Problem<Byte, Integer> bpp = new BestPlayerPos(state.zokoban_pos, -1, actions, state.boxes_pos , this.walls_pos, this.map.length, this.map[0].length);
        TreeSearch<Byte, Integer> ts = new TreeSearch<>(bpp, new BFS<>());
        System.out.println("Player Position: " + state.zokoban_pos);
        for (int box_pos : state.boxes_pos) {
            bpp.setGoalState(box_pos + this.map[0].length);
            result = ts.search();
            System.out.println(
                "Box Position: " + box_pos + "\n" +
                ((result == null) ? "No hay solución" : ("La solución es: " + result.state))
            );
            printPath(result);
            ts.reset();
        }
        // return result;

    }
    
}
