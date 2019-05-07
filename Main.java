import java.util.LinkedList;

import search.Node;
import search.Problem;
import search.TreeSearch;
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

    static void showAnswer(Node<String, int[][]> result) {
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

        int[][] goalState = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
        int[][] initialState = { { 1, 2, 0 }, { 4, 5, 3 }, { 7, 8, 6 } };

        LinkedList<String> actions = new LinkedList<>();
        actions.add("UP");
        actions.add("DOWN");
        actions.add("LEFT");
        actions.add("RIGHT");

        Problem<String, int[][]> pzzl = new Puzzle(initialState, actions, goalState, 3);
        // ((Puzzle) pzzl).desorganize(20);
        // printState((int[][]) pzzl.initialState);

        TreeSearch<String, int[][]> ts = new TreeSearch<>(pzzl, new Greddy<>(new Missplaced(pzzl)));
        showAnswer(ts.search());
        // ts.setStrategy(new BFS<>());
        // showAnswer(ts.search());

    }
}