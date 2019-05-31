import java.util.LinkedList;

import PicasYFilas.picasFijas;
import search.Node;
import search.Problem;
import search.TreeSearch;
import search.searchStrategies.*;

/**
 * Main
 */
public class Main {

    static void printState(char state[][]) {
        for (int row = 0; row < state.length; row++) {
            for (int col = 0; col < state.length; col++) {
                System.out.print(state[row][col]);
            }
            System.out.println();
        }
    }

    static void showAnswer(Node<String, char[][]> result) {
        if (result == null) {
            System.out.println("No hay solución para el estado inicial dado.");
            return;
        }

        while (result != null) {
            System.out.println("----------level " + result.depth + "----------");
            // System.out.println(result.action);
            printState(result.state);
            result = result.parent;
        }
    }

    public static void main(String[] args) {
        
        
        
        char[][] initialState = {
                        { '#', '#', '#', '#', '#'},
                        { '#', '$', ' ', ' ', '#'},
                        { '#', '%', '&', ' ', '#'},
                        { '#', ' ', ' ', ' ', '#'},
                        { '#', '#', '#', '#', '#'} 
                            };
                            
        char[][] goalState = {                           
                            { '#', '#', '#', '#', '#'},
                            { '#', '$', ' ', ' ', '#'},
                            { '#', ' ', ' ', ' ', '#'},
                            { '#', ' ', ' ', ' ', '#'},
                            { '#', '#', '#', '#', '#'} 
                            };                            

        LinkedList<String> actions = new LinkedList<>();
        actions.add("UP");
        actions.add("DOWN");
        actions.add("LEFT");
        actions.add("RIGHT");

        Problem<String, char[][]> sokoban = new Zokoban(initialState,actions, goalState, 5);
        // ((Puzzle) pzzl).desorganize(20);
        // printState((int[][]) pzzl.initialState);

        TreeSearch<String, char[][]> ts = new TreeSearch<>(sokoban, new BFS<>());
        showAnswer(ts.search());
        // ts.setStrategy(new BFS<>());
        // showAnswer(ts.search());

    }
}