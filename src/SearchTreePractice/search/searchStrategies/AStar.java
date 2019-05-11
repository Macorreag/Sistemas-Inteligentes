package search.searchStrategies;

import java.util.Comparator;

import search.Node;
import search.searchStrategies.dataStructures.Fringe;
import search.searchStrategies.dataStructures.TSPriorityQueue;

/**
 * AStar
 */
public class AStar <Action, State> extends Strategy<Action, State> {

    Heuristic <Action, State> heuristic;

    public AStar (Heuristic<Action, State> heuristic){
        this.heuristic = heuristic;

    }
    @Override
    public Fringe<Action, State> getFringe() {
        if (this.fringe == null) {
            Comparator<Node<Action, State>> c = (node1, node2) -> (int) (node1.pathCost + this.heuristic.heuristic(node1) - (node2.pathCost + this.heuristic.heuristic(node2)) );
            this.fringe = new TSPriorityQueue<>(c);
        }
        return this.fringe;
    }
}