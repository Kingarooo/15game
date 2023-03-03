import java.util.PriorityQueue;
import java.util.Queue;
import java.util.LinkedList;
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
    this.startime = System.currentTimeMillis();
    this.mapa = new Stack<GameState>();
  }

  public void search() { // search for a solution
    int count = 0;
    queue.offer(new Node(initial)); // add initial state to queue
    while (!queue.isEmpty()) { // while queue is not empty
      Node node = queue.poll(); // get first element of queue
      mapa.add(node.getState());
      ++gerados; // increment number of generated states

      if (node.getState().equals(goal)) { // if goal found
        for (int i = 0; i < mapa.size(); i++) {
          System.out.println(mapa.get(i));
        }
        System.out.println("Goal found!");
        System.out.println("Number of visited states: " + visited);
        System.out.println("Number of generated states: " + gerados);
        System.out.println("Time: " + (System.currentTimeMillis() - startime) + "ms");
        System.out.println("Depth: " + node.getDepth());
        return;
      } else { // if goal not found
        LinkedList<GameState> sucessors = new LinkedList<>();
        sucessors = node.getState().getSuccessors(mapa);
        ++visited; // increment number of visited states
        for (GameState tabu : sucessors) { // for each sucessor of the current state
          Node aux = new Node(tabu, node.getDepth() + 1);
          ++gerados;
          queue.add(aux);
        }

      }
    }

  }
}