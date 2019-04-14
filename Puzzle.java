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
                    if (((int[][]) this.goalState)[i][j] == nodeState2[i][j])
                        return true;
                }
            }
        } catch (Exception e) {
            System.err.println("La matriz no es " + this.size + " x " + this.size + ".\n" + e);
        }
        return false;
    }

    @Override
    public LinkedList successorFn(Object nodeState) {
        if (nodeState == null) {
            System.err.println("The node state passed to succesorFn is null\n" + e);
            return null;
        }
        LinkedList succesors = new LinkedList<>();
        int nodeStateAux[][] = ((int[][]) nodeState).clone();
        int blankPos[] = this.getBlankPos(nodeStateAux);
        int newBlankPos = -1;
        LinkedList action_result;
        try {
            for (Action action : this.actions) {
                switch (action) {
                case "UP":
                    newBlankPos = blankPos[0] - 1;
                    if (newBlankPos > -1) {
                        nodeStateAux[blankPos[0]][blankPos[1]] = nodeStateAux[newBlankPos][blankPos[1]];
                        nodeStateAux[newBlankPos][blankPos[1]] = 0;
                        action_result = new LinkedList<>();
                        action_result.add("UP");
                        action_result.add(nodeStateAux);
                        succesors.add(action_result);
                    }
                    break;

                case "DOWN":
                    newBlankPos = blankPos[0] + 1;
                    if (newBlankPos < this.size) {
                        nodeStateAux[blankPos[0]][blankPos[1]] = nodeStateAux[newBlankPos][blankPos[1]];
                        nodeStateAux[newBlankPos][blankPos[1]] = 0;
                        action_result.add("DOWN");
                        action_result.add(nodeStateAux);
                        succesors.add(action_result);
                    }
                    break;

                case "LEFT":
                    newBlankPos = blankPos[1] - 1;
                    if (newBlankPos > -1) {
                        nodeStateAux[blankPos[0]][blankPos[1]] = nodeStateAux[blankPos[0]][newBlankPos];
                        nodeStateAux[blankPos[0]][newBlankPos] = 0;
                        action_result.add("LEFT");
                        action_result.add(nodeStateAux);
                        succesors.add(action_result);
                    }
                    break;

                case "RIGHT":
                    newBlankPos = blankPos[1] + 1;
                    if (newBlankPos < this.size) {
                        nodeStateAux[blankPos[0]][blankPos[1]] = nodeStateAux[blankPos[0]][newBlankPos];
                        nodeStateAux[blankPos[0]][newBlankPos] = 0;
                        action_result.add("RIGHT");
                        action_result.add(nodeStateAux);
                        succesors.add(action_result);
                    }
                    break;
                default:
                    break;
                }
                nodeStateAux = nodeStateAux.clone();
            }
            return succesors;
        } catch (NullPointerException e) {
            System.out.println("Actions are not defined.");
            System.err.println(e);
        }
        return null;
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