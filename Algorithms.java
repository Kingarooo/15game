import java.util.PriorityQueue;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

class Greedy {
  public LinkedList<Node> list;
  private GameState initial;
  private GameState goal;

  private int expandedNodes = 0;
  private int generatedNodes = 0;

  public Greedy(GameState initial, GameState goal) {
    this.initial = initial;
    this.goal = goal;
    this.list = new LinkedList<Node>();
  }

  int manhattanDistance(GameState state) {
    int distance = 0;
    for (int i = 0; i < state.getSize() * state.getSize(); i++) {
      int value = state.at(i);
      if (value != 0) {
        int row = i / state.getSize();
        int col = i % state.getSize();
        int goalRow = (value - 1) / this.goal.getSize();
        int goalCol = (value - 1) % this.goal.getSize();
        distance += Math.abs(row - goalRow) + Math.abs(col - goalCol);
      }
    }
    return distance;
  }

  int misplaced(GameState state) {
    int count = 0;
    for (int i = 0; i < state.getSize() * state.getSize(); i++) {
      int value = state.at(i);
      if (value != 0) {
        int row = i / state.getSize();
        int col = i % state.getSize();
        int goalRow = (value - 1) / this.goal.getSize();
        int goalCol = (value - 1) % this.goal.getSize();
        if (row != goalRow || col != goalCol) {
          count++;
        }
      }
    }
    return count;
  }

  public void search() {

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
  private long startime;

  public BFS(GameState initial, GameState goal) {
    this.queue = new LinkedList<>();
    this.initial = initial;
    this.goal = goal;

    this.startime = System.currentTimeMillis();
  }

  private void printPath(Node node) {
    int depth = 0;
    while (node.getParent() != null) { // while node has a parent
      System.out.println(node.getState().toString());
      node = node.getParent();
      depth++;
    }
    System.out.println("Goal found!");
    System.out.println("Time: " + (System.currentTimeMillis() - startime) + "ms");
    System.out.println("Depth: " + depth);
  }

  public void search() { // search for a solution
    Stack<GameState> visited_states = new Stack<GameState>();
    queue.offer(new Node(initial)); // add initial state to queue
    while (!queue.isEmpty()) { // while queue is not empty
      Node node = queue.poll(); // get first element of queue
      // sleep 1 second
      // try {
      // Thread.sleep(100);
      // } catch (InterruptedException e) {
      // e.printStackTrace();
      // }

      if (!visited_states.contains(node.getState()))
        visited_states.add(node.getState());

      if (node.getState().equals(goal)) { // if goal found
        printPath(node);
        return;
      } else { // if goal not found
        LinkedList<Node> sucessors = new LinkedList<Node>();
        sucessors = node.getState().getSuccessors(visited_states);
        for (Node tabu : sucessors) { // for each sucessor of the current state
          tabu.setParent(node);
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
    this.mapa = new Stack<GameState>();
  }

  public void search() {
    Stack<Node> stack = new Stack<Node>();
    Stack<GameState> visited_states = new Stack<GameState>();
    stack.push(new Node(initial));
    while (!stack.isEmpty()) {
      Node node = stack.pop();
      visited_states.add(node.getState());
      if (node.getState().equals(goal)) {
        // imprimir o caminho feito quando achar a solução
        while (node.getParent() != null) {
          System.out.println(node.getState().toString());
          node = node.getParent();
        }
        System.out.println("Goal found!");
        System.out.println("Depth: " + node.getDepth());
        return;
      } else {
        System.out.println(node.getState().toString());
        LinkedList<Node> successors = new LinkedList<Node>();
        //test if the state is already in the stack
        
        successors = node.getState().getSuccessors(visited_states);
        for (Node tabu : successors) {
          tabu.setParent(node);
          stack.push(tabu);
        }
      }
    }
  }
}
