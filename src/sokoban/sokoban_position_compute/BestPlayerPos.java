package sokoban.sokoban_position_compute;

import java.util.LinkedList;
import java.util.List;

import search.Node;
import search.Problem;

/**
 * BestPlayerPos
 */
public class BestPlayerPos extends Problem<Byte, Integer> {

    private int rows, cols;
    private List<Integer>walls_pos, boxes_pos;

    public BestPlayerPos(int initialState, int goalState, Byte[] actions, List<Integer> boxes_pos,
            List<Integer> walls_pos, int rows, int cols) {
        super(initialState, goalState, actions);
        this.boxes_pos = boxes_pos;
        this.walls_pos = walls_pos;
        this.actions = actions;
        this.initialState = initialState;
        this.goalState = goalState;
        this.cols = cols;
        this.rows = rows;
    }

    @Override
    public boolean goalTest(Integer state) {
        return state == this.goalState;
    }

    @Override
    public int stateComparation(Integer state1, Integer state2) {
        return state1 - state2;
    }

    @Override
    public double stepCost(Node<Byte, Integer> node) {
        return node.pathCost + 1;
    }

    @Override
    public LinkedList<LinkedList<Object>> successorFn(Integer state) {
        LinkedList<LinkedList<Object>> successors = new LinkedList<>();
        int newPos = -1;
        LinkedList<Object> action_state;
        for (byte action : this.actions) {
            switch (action) {
            // UP
            case 1:
                /* Verifica que no se salga del mapa */
                if (state >= this.cols) {
                    newPos = state - this.cols;

                    // Se verifica si hay un obstaculo en la nueva posición
                    if (existsAnObbstacle(newPos))
                        break;

                    // Si no hay obstaculos, se puede mover, entonces hay estado sucesor al hacer la
                    // acción UP
                    action_state = null;
                    action_state = new LinkedList<>();
                    action_state.add(action);
                    action_state.add(newPos);
                    successors.add(action_state);
                }

                break;

            // DOWN
            case 2:
                /* Verifica que no se salga del mapa */
                if (state <= (this.rows-1)*(this.cols)) {
                    newPos = state + cols;

                    // Se verifica si hay un obstaculo en la nueva posición
                    if (existsAnObbstacle(newPos))
                        break;

                    // Si no hay obstaculos, se puede mover, entonces hay estado sucesor al hacer la
                    // acción DOWN
                    action_state = null;
                    action_state = new LinkedList<>();
                    action_state.add(action);
                    action_state.add(newPos);
                    successors.add(action_state);
                }
                break;

            // LEFT
            case 3:
                /* Verifica que no se salga del mapa */
                if (state > 0) {
                    newPos = state - 1;

                    // Se verifica si hay un obstaculo en la nueva posición
                    if (existsAnObbstacle(newPos))
                        break;

                    // Si no hay obstaculos, se puede mover, entonces hay estado sucesor al hacer la
                    // acción LEFT
                    action_state = null;
                    action_state = new LinkedList<>();
                    action_state.add(action);
                    action_state.add(newPos);
                    successors.add(action_state);
                }
                break;

            // RIGHT
            case 4:
                if (state < rows) {
                    newPos = state + 1;

                    // Se verifica si hay un obstaculo en la nueva posición
                    if (existsAnObbstacle(newPos))
                        break;

                    // Si no hay obstaculos, se puede mover, entonces hay estado sucesor al hacer la
                    // acción RIGHT
                    action_state = null;
                    action_state = new LinkedList<>();
                    action_state.add(action);
                    action_state.add(newPos);
                    successors.add(action_state);
                }
                break;
            default:
                break;
            }
        }

        return successors;
    }

    /*
    *   Verifica si hay obstaculos para la nueva posición, si hay se retorna -1
    */
    private boolean existsAnObbstacle (int newPos) {
        // Verifica si se va a topar con un muro
        for (int wall : this.walls_pos) {
            if (wall == newPos) {
                return true;
            }
        }

        // Verifica si se va a topar con una caja
        for (int wall : this.boxes_pos) {
            if (wall == newPos) {
                return true;
            }
        }
        return false;
    }

}