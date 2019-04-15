import java.util.Hashtable;
import java.util.LinkedList;

/**
 * TreeSearch
 */
public class TreeSearch<Action, State> {

    Node<Action, State> root;
    LinkedList<Node<Action, State>> fridge; // Cola para almacenar los nodos y armar el arbol
    Problem problem;
    int numNodes;

    public TreeSearch(Problem problem) {
        this.fridge = new LinkedList<>();
        this.problem = problem;
        try {
            this.root = new Node<>(null, 1, (State) this.problem.initialState, 1, null);
            this.numNodes = 1;
        } catch (Exception e) {
            System.out.println("Problem must be not null\n");
            System.err.println(e);
        }
    }

    public Node<Action, State> bfs() {
        // fridge es una cola.
        fridge.add(this.root);
        Node<Action, State> node = null;
        while (!fridge.isEmpty()) {
            node = fridge.poll();
            if (this.problem.goalTest(node.state)) {
                return node;
            }
            fridge.addAll(this.expand(node));
        }
        return null;
    }

    public LinkedList<Node<Action, State>> expand(Node node) {
        LinkedList<Node<Action, State>> successors = new LinkedList<>();
        Node parentNode = node;
        Action a;
        State r;
        boolean in_path;
        LinkedList temp;
        for (Object action_result : this.problem.successorFn(node.state)) {
            temp = (LinkedList<Node>) action_result;
            in_path = false;
            a = (Action) (temp.pop());
            r = (State) (temp.pop());
            while (parentNode.parent != null && !in_path) {
                in_path = (r != parentNode.state);
                parentNode = parentNode.parent;
            }
            if (!in_path) {
                Node<Action, State> s = new Node<>(node, node.depth + 1, r, node.pathCost + 1, a);
                successors.add(s);
            }
        }
        node.children = successors;
        return successors;
    }

}