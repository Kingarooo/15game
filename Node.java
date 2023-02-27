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

  void setParent(Node parent) {
    this.parent = parent;
  }

  void setChildren(Node[] children) {
    this.children = children;
  }

  @Override
  public String toString() {
    return state.toString();
  }

}
