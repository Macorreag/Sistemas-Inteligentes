import java.util.LinkedList;

/**
 * Main
 */
public class Main implements Collection{

    public static void main(String[] args) {

        Node<String, String> node = new Node(null, 1, "724|506|831",1);
        Node node1 = new Node();
        LinkedList<Node> list = new LinkedList<>();
        list.add(node);
        System.out.println(list.contains(node1));
        list.add(node1);
        System.out.println(list.contains(node1));
    }

    @Override
    public boolean contains(Object o) {
        
    }    
}