import java.util.LinkedList;

/**
 * 8-Puzzle
 */
public class Puzzle extends Problem {
    int size;

    public Puzzle(int[][] initialState, LinkedList<String> actions, int goalState[][], int size) {
        super(initialState, goalState, actions);
        this.initialState = initialState;
        this.goalState = goalState;
        this.actions = actions;
        this.size = size;
    }

    @Override
    public boolean goalTest(Object nodeState) {
        int nodeState2[][] = (int[][]) nodeState;
        try {
            for (int i = 0; i < this.size; i++) {
                for (int j = 0; j < this.size; j++) {
                    if (((int[][]) this.goalState)[i][j] != nodeState2[i][j])
                        return false;
                }
            }
        } catch (Exception e) {
            System.err.println("La matriz no es " + this.size + " x " + this.size + ".\n" + e);
        }
        return true;
    }

    @Override
    public LinkedList<Node> successorFn(Object nodeState) {
        if (nodeState == null) {
            System.err.println("The node state passed to succesorFn is null");
            return null;
        }
        LinkedList succesors = new LinkedList<>();
        int nodeStateAux[][] = ((int[][]) nodeState).clone();
        int blankPos[] = this.getBlankPos(nodeStateAux);
        int newBlankPos[] = { -1, -1 };
        LinkedList action_result;
        try {
            for (Object action : this.actions) {
                action_result = new LinkedList<>();
                switch ((String) action) {
                case "UP":
                    newBlankPos[0] = blankPos[0] - 1;
                    newBlankPos[1] = blankPos[1];
                    if (newBlankPos[0] > -1) {
                        action_result = new LinkedList<>();

                        action_result.add("UP");
                        action_result.add(cloneAndUpdateMatrix(nodeStateAux, newBlankPos, blankPos));
                        succesors.add(action_result);
                    }
                    break;

                case "DOWN":
                    newBlankPos[0] = blankPos[0] + 1;
                    newBlankPos[1] = blankPos[1];
                    if (newBlankPos[0] < this.size) {
                        action_result.add("DOWN");
                        action_result.add(cloneAndUpdateMatrix(nodeStateAux, newBlankPos, blankPos));
                        succesors.add(action_result);
                    }
                    break;

                case "LEFT":
                    newBlankPos[1] = blankPos[1] - 1;
                    newBlankPos[0] = blankPos[0];
                    if (newBlankPos[1] > -1) {
                        action_result.add("LEFT");
                        action_result.add(cloneAndUpdateMatrix(nodeStateAux, newBlankPos, blankPos));
                        succesors.add(action_result);
                    }
                    break;

                case "RIGHT":
                    newBlankPos[1] = blankPos[1] + 1;
                    newBlankPos[0] = blankPos[0];
                    if (newBlankPos[1] < this.size) {
                        action_result.add("RIGHT");
                        action_result.add(cloneAndUpdateMatrix(nodeStateAux, newBlankPos, blankPos));
                        succesors.add(action_result);
                    }
                    break;
                default:
                    break;
                }
            }
            return succesors;
        } catch (NullPointerException e) {
            System.out.println("Actions are not defined.");
            System.err.println(e);
        }
        return null;
    }

    private int[][] cloneAndUpdateMatrix(int[][] state, int[] coorToUpdate, int[] zeroCoor) {
        int newMatrix[][] = new int[this.size][this.size];
        for (int row = 0; row < this.size; row++) {
            for (int col = 0; col < this.size; col++) {
                newMatrix[row][col] = state[row][col];
            }
        }
        newMatrix[zeroCoor[0]][zeroCoor[1]] = state[coorToUpdate[0]][coorToUpdate[1]];
        newMatrix[coorToUpdate[0]][coorToUpdate[1]] = 0;
        return newMatrix;
    }

    private int[] getBlankPos(int[][] state) {
        int pos[];
        for (int row = 0; row < this.size; row++) {
            for (int col = 0; col < this.size; col++) {
                if (state[row][col] == 0) {
                    pos = new int[2];
                    pos[0] = row;
                    pos[1] = col;
                    return pos;
                }
            }
        }
        return null;
    }

}