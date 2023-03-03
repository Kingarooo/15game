import java.util.PriorityQueue;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

class DFS {

  private GameState initial;
  private GameState goal;

  public DFS(GameState initial, GameState goal) {
    this.initial = initial;
    this.goal = goal;
  }

}

class Greedy {
  public PriorityQueue<Node> queue;
  public Queue<Node> queue2;
  private GameState initial;
  private GameState goal;

  private int expandedNodes = 0;
  private int generatedNodes = 0;

  public Greedy(GameState initial, GameState goal) {
    this.queue = new PriorityQueue<Node>();
    this.initial = initial;
    this.goal = goal;
  }

  public void search() {
    queue.add(new Node(initial));
  }
}

class Astar {
  public PriorityQueue<Node> queue;
  public Queue<Node> queue2;
  private GameState initial;
  private GameState goal;

  private int expandedNodes = 0;
  private int generatedNodes = 0;

  public Astar(GameState initial, GameState goal) {
    this.queue = new PriorityQueue<Node>();
    this.initial = initial;
    this.goal = goal;
  }

  public void search() {
    queue.add(new Node(initial));
  }
}

class BFS {
  public Queue<Node> queue = new LinkedList<>();
  private GameState initial; // initial state
  private GameState goal; // goal state
  private Stack<GameState> mapa; // map of visited states
  private long visited; // number of visited states
  private long gerados; // number of generated states
  private long startime;

  public BFS(GameState initial, GameState goal) {
    this.queue = new LinkedList<>();
    this.initial = initial;
    this.goal = goal;
    this.visited = 0;
    this.gerados = 0;

  }

  public void search() { // search for a solution
    int count = 0;
    queue.offer(new Node(initial)); // add initial state to queue
    while (!queue.isEmpty()) { // while queue is not empty
      Node node = queue.poll(); // remove first element from queue
      ++gerados; // increment number of generated states

      if (queue.equals(goal)) { // if goal found
        System.out.println("Goal found!");
        return;
      } else { // if goal not found
        LinkedList<GameState> sucessors = new LinkedList<>();
        sucessors = node.getState().getSucessors(mapa);
        for(GameState tabu: node.getState().getSucessors(mapa)){ // for each sucessor of the current state
        Node aux = new Node(tabu, node.getDepth()+1);
        ++gerados;
        // queue.add(aux);
      }

    }
  }
}
// }