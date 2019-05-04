package SearchTreePractice.search;

import java.util.LinkedList;

import SearchTreePractice.search.searchStrategies.Strategy;

/**
 * TreeSearch
 */
public class TreeSearch<Action, State> {

    public static final int BFS = 0;
    public static final int IDLS = 2;
    public static final int DFS = 1;

    Node<Action, State> root;
    Strategy<Action, State> strategy; // Cola para almacenar los nodos y armar el arbol
    Problem<Action, State> problem;
    int numNodes;

    public TreeSearch(Problem<Action, State> problem, Strategy<Action, State> strategy) {
        this.strategy = strategy;
        this.problem = problem;
        try {
            Node<Action, State> root = new Node<>(null, 1, (State) this.problem.initialState, 0, null);
            root.pathCost = this.problem.stepCost(root);
            this.root = root;
            this.numNodes = 1;
        } catch (Exception e) {
            System.out.println("Problem must be not null\n");
            System.err.println(e);
        }
    }

    public Node<Action, State> search() {
        if (this.strategy.getNum_iterations() > 1) {
            Node<Action, State> solution;
            for (int iteration = 0; iteration < this.strategy.getNum_iterations(); iteration++) {
                if ((solution = _search(iteration)) != null)
                    return solution;
            }
        }
        return _search(this.strategy.getMax_depth());
    }

    private Node<Action, State> _search(int max_depth) {
        Node<Action, State> node = this.root;
        LinkedList<Node<Action, State>> newNodes;

        this.strategy.getFringe().addNode(node);

        while (!this.strategy.getFringe().isEmpty()) {
            node = this.strategy.getFringe().removeNode();
            if (this.problem.goalTest(node.state)) {
                return node;
            }
            if (node.depth < max_depth) {
                newNodes = this.expand(node);
                if (!newNodes.isEmpty())
                    this.strategy.getFringe().addAll(newNodes);
            }
        }
        return null;
    }

    /**
     * @param strategy the strategy to set
     */
    public void setStrategy(Strategy<Action, State> strategy) {
        this.strategy = null;
        this.strategy = strategy;
    }

    @SuppressWarnings("unchecked")
    public LinkedList<Node<Action, State>> expand(Node<Action, State> node) {
        LinkedList<Node<Action, State>> successors = new LinkedList<>();
        Node<Action, State> parentNode;
        Action a;
        State r;
        int in_path;
        for (LinkedList<Object> A_result : this.problem.successorFn(node.state)) {
            in_path = -1;
            parentNode = node;
            a = (Action) (A_result.pop());
            r = (State) (A_result.pop());
            while (parentNode != null && in_path != 1) {
                in_path = this.problem.stateComparation(r, parentNode.state);
                parentNode = parentNode.parent;
            }
            if (in_path != 1) {
                Node<Action, State> s = new Node<>(node, node.depth + 1, r, 0, a);
                s.pathCost = this.problem.stepCost(s);
                successors.add(s);
            }
        }
        // node.children = successors;
        return successors;
    }

}