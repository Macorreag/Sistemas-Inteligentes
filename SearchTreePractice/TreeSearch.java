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
        Node<Action, State> node = this.root;
        LinkedList<Node<Action, State>> newNodes;
        fridge.add(node);
        while (!fridge.isEmpty()) {
            node = fridge.poll();
            if (this.problem.goalTest(node.state)) {
                return node;
            }
            newNodes = this.expand(node);
            numNodes += newNodes.size();
            fridge.addAll(newNodes);
        }
        return null;
    }

    public Node<Action, State> dfs() {
        // fridge es una pila.
        Node<Action, State> node = this.root;
        fridge.add(node);
        LinkedList<Node<Action, State>> aux = null;
        while (!fridge.isEmpty()) {
            aux = this.expand(node);
            fridge.addAll(0, aux);
            if (aux.isEmpty()) {
                aux = null;
                node = fridge.pop();
                if (this.problem.goalTest(node.state)) {
                    return node;
                }
                node.parent.children.remove(node);
                continue;
            }
            node = aux.peek();
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
                in_path = this.problem.stateComparation(r, parentNode.state);
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