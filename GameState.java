import java.util.Scanner;

class GameState {
  // constructor
  private int size;
  private int[] board;
  private int zeroPosition;

  public GameState(int n) {
    // create a new game
    this.size = n;
    this.board = new int[n * n];
  }

  @Override
  public String toString() {
    String s = "";
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        s += this.board[i * this.size + j] + " ";
      }
      s += "\n";
    }
    return s;
  }

  int at(int index) {
    return board[index];

  }

  int getSize() {
    return size;
  }

  public void read_board(Scanner in) {
    int index = 0;
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        this.board[index++] = in.nextInt();
        if (this.board[index - 1] == 0)
          setZeroPosition(index - 1);
      }
    }
  }

  void printBoard() {
    int index = 0;
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        System.out.print(this.board[index++] + " ");
      }
      System.out.println();
    }
  }

  int getZeroPosition() {
    return zeroPosition;
  }

  void setZeroPosition(int i) {
    this.zeroPosition = i;
  }

  boolean isZero(int i) {
    return getZeroPosition() == i;
  }

  public int Inversions() {

    int inversions = 0;
    for (int i = 0; i < this.getSize() * this.getSize(); i++) {
      if (isZero(i)) {
        inversions += ((i / this.getSize()) + 1);
        continue;
      }

      int count = 0;
      for (int j = i + 1; j < this.getSize() * this.getSize(); j++) {
        if (!isZero(i) && !isZero(j) && at(i) > at(j)) {
          count++;
        }
      }

      inversions += count;
    }
    return inversions;
  }

  public boolean isSolvable() {
    if ((size % 2) == 1)
      return (Inversions() % 2 == 0);
    else if (Inversions() % 2 == 0 && ((zeroPosition / size) + 1) % 2 == 1) {
      return true;
    } else if (Inversions() % 2 == 1 && ((zeroPosition / size) + 1) % 2 == 0) {
      return true;
    } else
      return false;

  }

  public int[] possibleMoves() {
    int[] moves = new int[4];
    int index = 0;
    if (zeroPosition % size != 0) {
      moves[index++] = zeroPosition - 1;
    }
    if (zeroPosition % size != size - 1) {
      moves[index++] = zeroPosition + 1;
    }
    if (zeroPosition / size != 0) {
      moves[index++] = zeroPosition - size;
    }
    if (zeroPosition / size != size - 1) {
      moves[index++] = zeroPosition + size;
    }
    return moves;
  }

  public Node[] getSuccessors() {
    int[] moves = possibleMoves();
    Node[] successors = new Node[moves.length];
    for (int i = 0; i < moves.length; i++) {
      GameState newState = new GameState(this.size);
      for (int j = 0; j < this.size * this.size; j++) {
        newState.board[j] = this.board[j];
      }
      newState.board[zeroPosition] = newState.board[moves[i]];
      newState.board[moves[i]] = 0;
      newState.setZeroPosition(moves[i]);
      successors[i] = new Node(newState);

    }
    for (int i = 0; i < successors.length; i++) {
      successors[i].setParent(new Node(this));
    }

    return successors;
  }
}