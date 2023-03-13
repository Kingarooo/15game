import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.HashMap;

class Greedy {
  public PriorityQueue<Node> list;
  private GameState initial;
  private GameState goal;
  private long startime;

  Comparator<Node> comparator = new Comparator<Node>() {
    @Override
    public int compare(Node n1, Node n2) {
      if (n1.getCost() < n2.getCost()) {
        return -1;
      } else if (n1.getCost() > n2.getCost()) {
        return 1;
      } else {
        return 0;
      }
    }
  };

  public Greedy(GameState initial, GameState goal) {
    this.initial = initial;
    this.goal = goal;
    this.list = new PriorityQueue<Node>(1, comparator);
    this.startime = System.currentTimeMillis();
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

  private void printPath(Node node, LinkedList<GameState> visited_states) {
    int depth = 0;
    while (node.getParent() != null) { // while node has a parent
      System.out.println(node.getState().toString());
      node = node.getParent();
      depth++;
    }
    System.out.println("Goal found!");
    System.out.println("Time: " + (System.currentTimeMillis() - startime) + "ms");
    System.out.println("Memory: " + (visited_states.size() + depth) + " nodes");
    System.out.println("Depth: " + depth);
  }

  public void search(String mode) {

    LinkedList<GameState> visited_states = new LinkedList<GameState>();
    list.add(new Node(initial, 0));
    while (!list.isEmpty()) {
      Node current = list.poll();
      if (current.getState().equals(goal)) {
        printPath(current, visited_states);
        return;
      } else {
        visited_states.add(current.getState());
        LinkedList<Node> children = current.getState().getSuccessors(current, visited_states);
        for (Node child : children) {
          if (!visited_states.contains(child.getState())) {
            if (mode.equals("Misplaced")) {
              child.setParent(current);
              child.setCost(misplaced(child.getState()));
              list.add(child);
            } else if (mode.equals("Manhattan")) {
              child.setParent(current);
              child.setCost(manhattanDistance(child.getState()));
              list.add(child);
            }
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
  private long startime;

  public Astar(GameState initial, GameState goal) {
    this.queue = new PriorityQueue<Node>();
    this.initial = initial;
    this.goal = goal;
    this.startime = System.currentTimeMillis();
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

  private void printPath(Node node, LinkedList<GameState> visited_states) {
    int depth = 0;
    while (node.getParent() != null) { // while node has a parent
      System.out.println(node.getState().toString());
      node = node.getParent();
      depth++;
    }
    System.out.println("Goal found!");
    System.out.println("Time: " + (System.currentTimeMillis() - startime) + "ms");
    System.out.println("Memory: " + (visited_states.size() + depth) + " nodes");
    System.out.println("Depth: " + depth);
  }

  public void search(String mode) {
    queue.add(new Node(initial));
    PriorityQueue<Node> queue = new PriorityQueue<Node>();
    LinkedList<GameState> visited = new LinkedList<GameState>();
    HashMap<GameState, Node> mapa = new HashMap<GameState, Node>();
    Node startNode = new Node(queue.poll().getState(), 0);
    int flag = 0;
    if (mode == "Manhattan") {
      flag = 0;
    } else if (mode == "Misplaced") {
      flag = 1;
    }
    // adiciono o primeiro node na queue e no mapa
    queue.add(startNode);
    mapa.put(startNode.getState(), startNode);
    System.out.println("Goal: " + goal.toString());

    while (!queue.isEmpty()) {
      Node current = queue.poll();
      if (current.getState().equals(goal)) {
        LinkedList<Node> path = new LinkedList<Node>();
        path.add(current);
        while (current.getState().equals(goal) == false) {
          current = mapa.get(current.getState()).getParent();
          path.addFirst(mapa.get(current.getState()));
        }
        System.out.println("Goal found!");
        printPath(path.getLast(), visited);
        reconstructPath(current);
      }
      visited.add(current.getState());

      LinkedList<Node> successors = current.getState().getSuccessors(current, visited);
      int best = 0;
      for (Node successor : successors) {
        Node successorNode = mapa.get(successor.getState());
        // fazer o custo do atual
        best = current.getCost() + 1;
        if (flag == 0)
          successorNode.setCost(manhattanDistance(successorNode.getState()));
        else if (flag == 1)
          successorNode.setCost(misplaced(successorNode.getState()));
        if (successorNode == null) {
          successorNode = successor;
          mapa.put(successor.getState(), successorNode);
        } else if (best >= successorNode.getCost()) {
          continue;
        }
        best = current.getCost() + 1;
        successorNode.setParent(current);
        successorNode.setCost(best);

        if (!queue.contains(successorNode)) {
          queue.add(successorNode);
        }
      }
    }
    System.out.println("Goal found!");

    return;
  }

  private static LinkedList<Node> reconstructPath(Node current) {
    LinkedList<Node> path = new LinkedList<Node>();
    while (current.getParent() != null) {
      path.addFirst(current);
      current = current.getParent();
    }
    path.addFirst(current);
    System.out.println("Path: ");
    for (Node node : path) {
      System.out.println(node.getState().toString());
    }
    return path;
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

  private void printPath(Node node, LinkedList<GameState> visited_states) {
    int depth = 0;
    while (node.getParent() != null) { // while node has a parent
      System.out.println(node.getState().toString());
      node = node.getParent();
      depth++;
    }
    System.out.println("Goal found!");
    System.out.println("Time: " + (System.currentTimeMillis() - startime) + "ms");
    System.out.println("Memory: " + (visited_states.size() + depth) + " nodes");
    System.out.println("Depth: " + depth);
  }

  public void search() { // search for a solution
    LinkedList<GameState> visited_states = new LinkedList<GameState>();
    queue.offer(new Node(initial)); // add initial state to queue
    while (!queue.isEmpty()) { // while queue is not empty
      Node node = queue.poll(); // get first element of queue
      if (!visited_states.contains(node.getState()))
        visited_states.add(node.getState());

      if (node.getState().equals(goal)) { // if goal found
        printPath(node, visited_states);
        return;
      } else { // if goal not found
        LinkedList<Node> sucessors = new LinkedList<Node>();
        sucessors = node.getState().getSuccessors(node, visited_states);
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

  private void printPath(Node node, LinkedList<GameState> visited_states) {
    int depth = 0;
    while (node.getParent() != null) { // while node has a parent
      System.out.println(node.getState().toString());
      node = node.getParent();
      depth++;
    }
    System.out.println("Goal found!");
    System.out.println("Time: " + (System.currentTimeMillis() - startime) + "ms");
    System.out.println("Memory: " + (visited_states.size() + depth) + " nodes");

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
      printPath(sucessor, visited_states);
      return true;
    } else if (depth == 0) {
      return false;
    } else {
      visited_states.push(sucessor.getState());
      LinkedList<Node> sucessors = new LinkedList<Node>();
      sucessors = sucessor.getState().getSuccessors(sucessor, visited_states);
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
