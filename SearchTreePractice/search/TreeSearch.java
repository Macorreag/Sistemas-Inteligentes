package search;

import java.util.LinkedList;

import search.searchStrategies.Fringe;

/**
 * TreeSearch
 */
public class TreeSearch<Action, State> {

    public static final int BFS = 0;
    public static final int IDLS = 2;
    public static final int DFS = 1;

    Node<Action, State> root;
    Fringe strategy; // Cola para almacenar los nodos y armar el arbol
    Problem problem;
    int numNodes;

    public TreeSearch(Problem problem, Fringe strategy) {
        this.strategy = strategy;
        this.problem = problem;
        try {
            Node<Action, State> root = new Node(null, 1, (State) this.problem.initialState, 0, null);
            root.pathCost = this.problem.stepCost(root);
            this.root = root;
            this.numNodes = 1;
        } catch (Exception e) {
            System.out.println("Problem must be not null\n");
            System.err.println(e);
        }
    }

    public Node search() {
        Node node = this.root;
        LinkedList<Node> newNodes;

        this.strategy.addNode(node);

        while (!this.strategy.isEmpty()) {
            node = this.strategy.removeNode();
            if (this.problem.goalTest(node.state)) {
                return node;
            }
            newNodes = this.expand(node);
            if (!newNodes.isEmpty())
                this.strategy.addAll(newNodes);
        }
        return null;
    }

    /**
     * @param strategy the strategy to set
     */
    public void setStrategy(Fringe strategy) {
        this.strategy = null;
        this.strategy = strategy;
    }

    public LinkedList<Node> expand(Node node) {
        LinkedList<Node> successors = new LinkedList<>();
        Node parentNode = node;
        Action a;
        State r;
        int in_path;
        LinkedList temp;
        for (Object A_result : this.problem.successorFn(node.state)) {
            temp = (LinkedList) A_result;
            in_path = -1;
            a = (Action) (temp.pop());
            r = (State) (temp.pop());
            while (parentNode.parent != null && in_path != 0) {
                in_path = this.problem.stateComparation(r, parentNode.state);
                parentNode = parentNode.parent;
            }
            if (in_path != 0) {
                Node s = new Node<>(node, node.depth + 1, r, 0, a);
                s.pathCost = this.problem.stepCost(s);
                successors.add(s);
            }
        }
        // node.children = successors;
        return successors;
    }

}