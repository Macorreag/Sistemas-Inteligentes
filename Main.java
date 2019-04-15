import java.util.LinkedList;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        int[][] initialState = { { 1, 2, 3 }, { 4, 0, 6 }, { 7, 5, 8 } };
        // int[][] initialState = { { 7, 2, 4 }, { 5, 0, 6 }, { 8, 3, 1 } };
        int[][] goalState = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };

        LinkedList<String> actions = new LinkedList<>();
        actions.add("UP");
        actions.add("DOWN");
        actions.add("LEFT");
        actions.add("RIGHT");

        Puzzle pzzl = new Puzzle(initialState, actions, goalState, initialState.length);

        TreeSearch<String, int[][]> ts = new TreeSearch<>(pzzl);
        Node<String, int[][]> result = ts.bfs();
        // Node<String, int[][]> result = ts.dfs();

        if (result == null) {
            System.out.println("No hay solución para el estado inicial dado.");
            return;
        }

        while (result != null) {
            System.out.println("----------level " + result.depth + "----------");
            for (int i = 0; i < initialState.length; i++) {
                for (int j = 0; j < initialState.length; j++) {
                    System.out.print((result.state)[i][j] + " ");
                }
                System.out.println();
            }
            result = result.parent;
        }

    }
}