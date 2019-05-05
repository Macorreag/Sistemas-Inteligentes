import search.Node;
import search.Problem;
import search.searchStrategies.Heuristic;;

/**
 * Missplaced
 */
public class Missplaced extends Heuristic<String, int[][]> {

    public Missplaced (Problem<String, int[][]> problem) {
        super(problem);
        this.problem = problem;
    }

    @Override
    public double heuristic(Node<String, int[][]> node) {
        double misplacedTiles = 0;
        for(int i = 0; i < node.state.length*node.state.length; i++ ){
            if(node.state[i/3][i%3] == this.problem.getGoalState()[i/3][(int)i%3]){
                misplacedTiles++;
            }


        }
        return misplacedTiles;
    }
    
}