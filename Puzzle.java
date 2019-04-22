import java.util.LinkedList;
import java.lang.Math;

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

    public int[][] desorganize(int num_mov) {
        int[][] initState = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
        int action;
        int blankPos[] = { 2, 2 };
        for (int mov = 0; mov < num_mov; mov++) {
            action = (int) (Math.random() * 4) + 1;
            switch (action) {
            case 1:
                if (blankPos[0] > 0) {
                    initState[blankPos[0]][blankPos[1]] = initState[blankPos[0] - 1][blankPos[1]];
                    initState[blankPos[0] - 1][blankPos[1]] = 0;
                    blankPos[0]--;
                }
                break;
            case 2:
                if (blankPos[0] + 1 < this.size) {
                    initState[blankPos[0]][blankPos[1]] = initState[blankPos[0] + 1][blankPos[1]];
                    initState[blankPos[0] + 1][blankPos[1]] = 0;
                    blankPos[0]++;
                }
                break;

            case 3:
                if (blankPos[1] > 0) {
                    initState[blankPos[0]][blankPos[1]] = initState[blankPos[0]][blankPos[1] - 1];
                    initState[blankPos[0]][blankPos[1] - 1] = 0;
                    blankPos[1]--;
                }
                break;
            case 4:
                if (blankPos[1] + 1 < this.size) {
                    initState[blankPos[0]][blankPos[1]] = initState[blankPos[0]][blankPos[1] + 1];
                    initState[blankPos[0]][blankPos[1] + 1] = 0;
                    blankPos[1]++;
                }
                break;
            default:
                break;
            }
        }
        this.initialState = initState;
        return initState;
    }

    @Override
    public int stateComparation(Object state1, Object state2) {
        int stateAux1[][] = (int[][]) state1;
        int stateAux2[][] = (int[][]) state2;
        for (int row = 0; row < this.size; row++) {
            for (int col = 0; col < this.size; col++) {
                if (stateAux1[row][col] != stateAux2[row][col])
                    return -1;
            }
        }
        return 0;
    }

    @Override
    public double stepCost(Node node) {
        return (double) node.depth;
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
    public LinkedList successorFn(Object nodeState) {
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
        } catch (NullPointerException e) {
            System.out.println("Actions are not defined.");
            System.err.println(e);
        }
        return succesors;
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