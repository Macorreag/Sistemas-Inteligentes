import java.util.LinkedList;
import search.*;

import search.searchStrategies.*;

/**
 * Main
 */
public class Main {

    static void printState(int state[][]) {
        for (int row = 0; row < state.length; row++) {
            for (int col = 0; col < state.length; col++) {
                System.out.print(state[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // int[][] initialState = { { 1, 5, 2 }, { 0, 4, 3 }, { 7, 8, 6 } }; // Este
        // caso sirve para probar BFS
        // int[][] initialState = null; // { { 1, 5, 2 }, { 0, 4, 3 }, { 7, 8, 6 } }; //
        // Este
        // caso sirve para probar BFS
        int[][] goalState = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };

        LinkedList<String> actions = new LinkedList<>();
        actions.add("UP");
        actions.add("DOWN");
        actions.add("LEFT");
        actions.add("RIGHT");

        Problem pzzl = new Puzzle(null, actions, goalState, 3);
        // StateComparator sComparator = (StateComparator) pzzl;
        ((Puzzle) pzzl).desorganize(7);
        // printState((int[][]) pzzl.initialState);

        // TreeSearch<String, int[][]> ts = new TreeSearch<>(pzzl, new TSStack());
        TreeSearch<String, int[][]> ts = new TreeSearch<>(pzzl, new TSPriorityQueue());
        Node<String, int[][]> result = ts.search();
        // Node<String, int[][]> result = ts.dfs();
        // Node<String, int[][]> result = ts.dls(6);

        if (result == null) {
            System.out.println("No hay solución para el estado inicial dado.");
            return;
        }

        while (result != null) {
            System.out.println("----------level " + result.depth + "----------");
            printState(result.state);
            result = result.parent;
        }

    }
}