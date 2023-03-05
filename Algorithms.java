import java.util.PriorityQueue;
import java.util.Queue;
import java.rmi.server.SocketSecurityException;
import java.util.LinkedList;
import java.util.Stack;

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
  }

  public void search() { // search for a solution
    Stack<GameState> visited_states = new Stack<GameState>();
    queue.offer(new Node(initial)); // add initial state to queue
    while (!queue.isEmpty()) { // while queue is not empty
      Node node = queue.poll(); // get first element of queue
      visited_states.push(node.getState());
      ++gerados; // increment number of generated states

      if (node.getState().equals(goal)) { // if goal found
        while (node.getParent() != null) { // while node has a parent
          System.out.println(node.getState().toString());
          node = node.getParent();
        }
        System.out.println("Goal found!");
        System.out.println("Number of visited states: " + visited);
        System.out.println("Number of generated states: " + gerados);
        System.out.println("Time: " + (System.currentTimeMillis() - startime) + "ms");
        System.out.println("Depth: " + node.getDepth());
        return;
      } else { // if goal not found
        LinkedList<Node> sucessors = new LinkedList<Node>();
        sucessors = node.getState().getSuccessors(visited_states);
        ++visited; // increment number of visited states
        for (Node tabu : sucessors) { // for each sucessor of the current state

          tabu.setParent(node);
          ++gerados;
          queue.add(tabu);
        }

      }
    }

  }
}

class DFS {
  private GameState initial;
  private GameState goal;
  private Stack<GameState> mapa; // map of visited states
  private long visited; // number of visited states
  private long gerados; // number of generated states

  public DFS(GameState initial, GameState goal) {
    this.initial = initial;
    this.goal = goal;
    this.visited = 0;
    this.gerados = 0;
    this.mapa = new Stack<GameState>();
  }

  public void searchDFS() {
    Stack<Node> stack = new Stack<Node>();
    stack.push(new Node(initial));
    while (!stack.isEmpty()) {
      Node node = stack.pop();
      if (node.getState().equals(goal)) {
        while (node.getParent() != null) {
          System.out.println(node.getState().toString());
          node = node.getParent();
        }
        System.out.println("Goal found!");
        System.out.println("Number of visited states: " + visited);
        System.out.println("Number of generated states: " + gerados);
        System.out.println("Depth: " + node.getDepth());
        return;
      } else {
        LinkedList<Node> successors = new LinkedList<Node>();
        ++visited;
        // if (Node.getState().getSuccessors.size() == 0) {
          // backtrack to the previous state
          continue;
        }
        // for (Node tabu : successors) {
          // tabu.setParent(node);
          // ++gerados;
          // stack.push(tabu);
        }
        if (stack.size() > 10) {
          mapa = new Stack<GameState>();
          System.gc();
        }
      }
    }
  }

