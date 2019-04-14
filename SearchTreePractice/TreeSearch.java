import java.util.Hashtable;
import java.util.LinkedList;

/**
 * TreeSearch
 */
public class TreeSearch<Action, State> {

    Node<Action, State> root;
    LinkedList<Node<Action, State>> fridge; // Cola para almacenar los nodos y armar el arbol
    Problem problem;

    public TreeSearch(Problem problem) {
        this.fridge = new LinkedList<>();
        this.problem = problem;
        try {
            this.root = new Node<>(null, 1, (State) this.problem.initialState, 1, null);
        } catch (Exception e) {
            System.out.println("Problem must be not null\n");
            System.err.println(e);
        }
    }

    public Node<Action, State> bfs() {
        // fridge es una cola.
        fridge.add(this.root);
        Node<Action, State> node;
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
        for (Object action_result : this.problem.succesorFn(node.state)) {
            action_result = (LinkedList<Node<Action, State>>) action_result;
            in_path = false;
            a = (Action) action_result.pop();
            r = (State) action_result.pop();
            while (parentNode.parent != null && !in_path) {
                in_path = (r != parentNode.state);
                parentNode = parentNode.parent;
            }
            if (!in_path) {
                Node<State> s = new Node<>(node, node.depth + 1, a, node.pathCost + 1, r);
                successors.add(s);
            }
        }
        return successors;
    }

}