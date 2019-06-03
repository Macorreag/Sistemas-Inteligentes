package sokoban;

import java.util.LinkedList;

import search.Node;
import search.Problem;
import search.searchStrategies.Heuristic;

import java.lang.Math;

/**
 * Zokoban
 */
public class SokobanSolver extends Problem<String, State> {
    int size;
    public static byte map[][];
    Heuristic<String, char[][]> heuristic;
    State satate;

    public SokobanSolver(State initialState, LinkedList<String> actions, State goalState,  int size) {
        super(initialState, goalState,  actions);
        this.initialState = initialState;
        this.goalState = goalState;
        this.actions = actions;
        this.size = size;
    }

    /*
     * Convenciones
     * '#' Muro 
     * '$' Box Objetive
     * '%' Box
     * '&' Player
     */

    @Override
    public int stateComparation(State state1, State state2) {
        for (int row = 0; row < this.size; row++) {
            for (int col = 0; col < this.size; col++) {
                if (state1.box_pos[row][col] != state2.box_pos[row][col])
                    return 0;
            }
        }
        return 1;
    }

    @Override
    public double stepCost(Node<String, char[][]> node) {
        return (double)node.depth;
    }

    @Override
    public boolean goalTest(char[][] nodeState) {
        try {
            for (int i = 0; i < this.size; i++) {
                for (int j = 0; j < this.size; j++) {
                    if (((char[][])this.goalState)[i][j] == '$' && nodeState[i][j] != '%' )
                        return false;
                    

                }
            }
        } catch (Exception e) {
            System.err.println("La matriz no es " + this.size + " x " + this.size + ".\n" + e);
        }
        return true;
        
    }

    @Override
    public LinkedList<LinkedList<Object>> successorFn(char[][] nodeState) {
        if (nodeState == null) {
            System.err.println("The node state passed to succesorFn is null");
            return null;
        }
        LinkedList<LinkedList<Object>> succesors = new LinkedList<>();
        char nodeStateAux[][] = ((char[][])nodeState).clone();
        int playerPos[] = this.getPlayerPos(nodeStateAux);
        int newPlayerPos[] = {playerPos[0],playerPos[1]};
        LinkedList<Object> action_result;
        char nextChar = '!';
        char nextNextChar = '!';
        try {
            for (Object action : this.actions) {
                action_result = new LinkedList<>();
                
                switch ((String)action) {
                    case "UP":
                        
                        newPlayerPos[0] = newPlayerPos[0]-1;
                        nextChar = nodeStateAux[newPlayerPos[0]][newPlayerPos[1]];
                        nextNextChar= nodeStateAux[newPlayerPos[0]-1][newPlayerPos[1]];
                        if( nextChar != '#' ){
                            if(nextChar == ' ' || nextChar == '$'){                                
                                action_result.add(cloneAndUpdateMatrix(nodeStateAux, newPlayerPos, playerPos,-1,0 ));
                                action_result.add("UP");
                            succesors.add(action_result);
                            }else if(nextChar == '%' && (nextNextChar == ' ' || nextNextChar == '$') ){
                                action_result.add(cloneAndUpdateMatrix(nodeStateAux, newPlayerPos, playerPos,-1,0 ));
                                action_result.add("UP");
                                succesors.add(action_result);
                            }
                        }
                        break;

                    case "DOWN":
                        newPlayerPos[0] = newPlayerPos[0]+1;
                        nextChar = nodeStateAux[newPlayerPos[0]][newPlayerPos[1]];
                        nextNextChar= nodeStateAux[newPlayerPos[0]+1][newPlayerPos[1]];
                        if( nextChar != '#' ){                            
                            if(nextChar == ' ' || nextChar == '$'){                                
                                action_result.add(cloneAndUpdateMatrix(nodeStateAux, newPlayerPos, playerPos,1,0 ));
                                action_result.add("DOWN");
                                succesors.add(action_result);
                            }else if(nextChar == '%' && (nextNextChar == ' ' || nextNextChar == '$') ){
                                action_result.add(cloneAndUpdateMatrix(nodeStateAux, newPlayerPos, playerPos,1,0 ));
                                action_result.add("DOWN");
                                succesors.add(action_result);
                            }
                            
                        }
                        break;

                    case "LEFT":
                        newPlayerPos[1] = newPlayerPos[1]-1;
                        nextChar = nodeStateAux[newPlayerPos[0]][newPlayerPos[1]];
                        nextNextChar= nodeStateAux[newPlayerPos[0]][newPlayerPos[1]+1];
                        if( nextChar != '#' ){                            
                            if(nextChar == ' ' || nextChar == '$'){                                
                                action_result.add(cloneAndUpdateMatrix(nodeStateAux, newPlayerPos, playerPos,0,-1 ));
                                action_result.add("LEFT");
                                succesors.add(action_result);
                            }else if(nextChar == '%' && (nextNextChar == ' ' || nextNextChar == '$') ){
                                action_result.add(cloneAndUpdateMatrix(nodeStateAux, newPlayerPos, playerPos,0,-1 ));
                                action_result.add("LEFT");
                                succesors.add(action_result);
                            }
                            
                        }
                        break;

                    case "RIGHT":
                        newPlayerPos[1] = newPlayerPos[1]+1;
                        nextChar = nodeStateAux[newPlayerPos[0]][newPlayerPos[1]];
                        nextNextChar= nodeStateAux[newPlayerPos[0]][newPlayerPos[1]+1];
                        if( nextChar != '#' ){                            
                            if(nextChar == ' ' || nextChar == '$'){                                
                                action_result.add(cloneAndUpdateMatrix(nodeStateAux, newPlayerPos, playerPos,0,1 ));
                                action_result.add("RIGHT");
                                succesors.add(action_result);
                            }else if(nextChar == '%' && (nextNextChar == ' ' || nextNextChar == '$') ){
                                action_result.add(cloneAndUpdateMatrix(nodeStateAux, newPlayerPos, playerPos,0,1 ));
                                action_result.add("RIGHT");
                                succesors.add(action_result);
                            }
                            
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
    
    private char[][] cloneAndUpdateMatrix(char[][] state, int[] coorToMove, int[] coorPlayer,int moveRow, int moveCol) {
        char newMatrix[][] = new char[this.size][this.size];
        for (int row = 0; row < this.size; row++) {
            for (int col = 0; col < this.size; col++) {
                newMatrix[row][col] = state[row][col];
            }
        }
        
        if(newMatrix[coorToMove[0]][coorToMove[1]] == ' ' || newMatrix[coorToMove[0]][coorToMove[1]] == '$'){
            newMatrix[coorPlayer[0]][coorPlayer[1]] = ' ';
        }else if(newMatrix[coorToMove[0]][coorToMove[1]] == '%'){
            newMatrix[coorToMove[0] + moveRow][coorToMove[1] + moveCol] = '%';        
        }
        
        newMatrix[coorToMove[0]][coorToMove[1]] = '&';

        if(this.initialState[coorPlayer[0]][coorPlayer[1]] == '$'){
            /*Error debido a la representación*/
            newMatrix[coorPlayer[0]][coorPlayer[1]] = '$';
        }
        
        return newMatrix;
    }

    private int[] getPlayerPos(char[][] state) {
        int pos[];
        for (int row = 0; row < this.size; row++) {
            for (int col = 0; col < this.size; col++) {
                if (state[row][col] == '&') {
                    pos = new int[2];
                    pos[0] = row;
                    pos[1] = col;
                    return pos;
                }
            }
        }
        return null;
    }
    
    private byte tranform_to_array(byte x, byte y)

}
