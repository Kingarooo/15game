import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

class Greedy {
  public LinkedList<Node> list;
  private GameState initial;
  private GameState goal;
  private long startime;

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

  private void printPath(Node node) {
    int depth = 0;
    while (node.getParent() != null) {
      System.out.println(node.getState().toString());
      node = node.getParent();
      depth++;
    }
    System.out.println("Goal found!");
    System.out.println("Time: " + (System.currentTimeMillis() - startime) + "ms");
    System.out.println("Depth: " + depth);
  }

  public void search(String mode) {
    LinkedList<GameState> visited_states = new LinkedList<GameState>();
    list.add(new Node(initial));
    while (!list.isEmpty()) {
      Node node = list.poll();
      if (node.getState().equals(goal)) {
        printPath(node);
        return;
      }
      visited_states.add(node.getState());
      LinkedList<Node> children = node.getState().getSuccessors(visited_states);
      for (Node child : children) {
        if (!visited_states.contains(child.getState())) {
          if (mode.equals("misplaced")) {
            list.add(new Node(child.getState(), misplaced(child.getState())));
          } else if (mode.equals("manhattan")) {
            list.add(new Node(child.getState(), manhattanDistance(child.getState())));
          }
        }
      }

    }
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
    LinkedList<GameState> visited_states = new LinkedList<GameState>();
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
  private Stack<Node> map;
  private long startime;

  public DFS(GameState initial, GameState goal) {
    this.initial = initial;
    this.goal = goal;
    this.map = new Stack<Node>();
    this.startime = System.currentTimeMillis();
  }

  private void printPath(Node node) {
    int depth = 0;
    while (node.getParent() != null) {
      System.out.println(node.getState().toString());
      node = node.getParent();
      depth++;
    }
    System.out.println("Goal found!");
    System.out.println("Time: " + (System.currentTimeMillis() - startime) + "ms");
    System.out.println("Depth: " + depth);
  }

  public boolean search(int depth) {
    LinkedList<GameState> visited_states = new LinkedList<GameState>();
    map.push(new Node(initial));

    Node node = map.peek();
    // System.out.println(node);
    // try {
    // Thread.sleep(800);
    // } catch (InterruptedException ex) {
    // Thread.currentThread().interrupt();
    // }

    return search(node, visited_states, depth - 1);

  }

  public boolean search(Node sucessor, LinkedList<GameState> visited_states, int depth) {

    if (sucessor.getState().equals(goal)) {
      printPath(sucessor);
      return true;
    } else if (depth == 0) {
      return false;
    } else {
      visited_states.push(sucessor.getState());
      LinkedList<Node> sucessors = new LinkedList<Node>();
      sucessors = sucessor.getState().getSuccessors(visited_states);
      for (Node sucessor2 : sucessors) {
        sucessor2.setParent(sucessor);
        map.push(sucessor2);
        if (search(sucessor2, visited_states, depth - 1))
          return true;
        else
          map.pop();
      }

    }
    return false;

  }
}
