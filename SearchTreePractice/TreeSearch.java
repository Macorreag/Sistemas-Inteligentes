import java.util.Hashtable;
import java.util.LinkedList;

/**
 * TreeSearch 
 */
public class TreeSearch<Action, State> {

    State goalState;
    Node root;
    LinkedList<Node> fridge; // Cola para almacenar los nodos y armar el arbol
    Problem problem;

    public TreeSearch(State goalState, Problem problem){
        this.goalState = goalState;
        this.fridge = new LinkedList<>();
        this.problem = problem;
        try {
            this.root = new Node<State>(null, 1, this.problem.initialState, 1);            
        } catch (Exception e) {
            System.out.println("Problem must be not null\n");
            System.err.println(e);
        }
    }

    public Node bfs() {
        // fridge es una cola. so
        fridge.add(new Node<Action,State>(null, 1, this.problem.initialState, 0, null));
        Node node;
        while (!fridge.isEmpty()) {
            node = fridge.poll();
            if (this.problem.goalTest(node.state)) {
                return node;
            }
            fridge.addAll(this.expand(node));
        }        
        return null;
    }

    public LinkedList<Node> expand(Node node) {
        LinkedList<Node> successors = new LinkedList<>();
        Node parentNode = node;
        Action a;
        State r;
        boolean in_path;
        for (LinkedList action_result : this.problem.succesorFn(node.state)) {
            in_path = false;
            a = (Action) action_result.pop();
            r = (State) action_result.pop();
            while (parentNode.parent != null && !in_path) {
                in_path = (r != parentNode.state);
                parentNode = parentNode.parent;
            }
            if (!in_path) {
                Node<State> s = new Node<>(
                    node,
                    node.depth + 1,
                    a,
                    node.pathCost + 1,
                    r()
                );
                successors.add(s);
            }
        }
        return successors;
    }
    
}