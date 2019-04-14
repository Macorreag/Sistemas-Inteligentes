import java.util.LinkedList;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        int[][] initialState = { { 7, 2, 4 }, { 5, 0, 6 }, { 8, 3, 1 } };
        int[][] goalState = { { 1, 2, 3 }, { 5, 6, 7 }, { 8, 9, 0 } };

        LinkedList<String> actions = new LinkedList<>();
        actions.add("UP");
        actions.add("DOWN");
        actions.add("LEFT");
        actions.add("RIGHT");

        Puzzle pzzl = new Puzzle(initialState, actions, goalState, initialState.length);

        TreeSearch<String, Integer[][]> ts = new TreeSearch<>(pzzl);
        Node<String, Integer[][]> result = (Node<String, Integer[][]>) ts.bfs();

        for (int i = 0; i < initialState.length; i++) {
            for (int j = 0; j < initialState.length; j++) {
                System.out.println(result.state[i][j] + " ");
            }
            System.out.println();
        }
    }
}