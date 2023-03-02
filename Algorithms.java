import java.util.PriorityQueue;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
class Astar {
  public PriorityQueue<Node> queue;
  public Queue <Node> queue2;
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
class BFS{
  Queue <Node> queue = new LinkedList<Node>();
  Set<GameState> visited = new HashSet<GameState>();
  private GameState initial;
  private GameState goal;

  public void BFS (GameState initial, GameState goal) {
    this.queue = new LinkedList<>();
    this.initial = initial;
    this.goal = goal;
  }
  public void search() {
  queue.offer(new Node(initial));
  visited.add(initial);
  while (!queue.isEmpty()) {

  }
}
}