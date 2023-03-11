class Node {
  private GameState state;
  private Node parent;
  private Node[] children;
  private int depth;
  private int cost;

  Node(GameState state) {
    this.state = state;
    this.parent = null;

    this.depth = 0;
    this.cost = 0;
  }

  Node(GameState state, int cost) {
    this.state = state;
    this.parent = null;

    this.cost = cost;
  }

  void setParent(Node parent) {
    this.parent = parent;
  }

  Node getParent() {
    return this.parent;
  }

  void setChildren(Node[] children) {
    this.children = children;
  }

  Node[] getChildren() {
    return this.children;
  }

  void setDepth(int depth) {
    this.depth = depth;
  }

  int getDepth() {
    return this.depth;
  }

  GameState getState() {
    return this.state;
  }

  boolean equals(Node node) {
    return this.state.equals(node.state);
  }

  @Override
  public String toString() {
    return state.toString();
  }

}
