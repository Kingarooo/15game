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

}