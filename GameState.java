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

  void read_board(Scanner in) {
    int index = 0;
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        if (in.hasNextInt()) {
          this.board[index++] = in.nextInt();
          if (this.board[index - 1] == 0)
            this.zeroPosition = index - 1;
        }
      }
    }
  }

  int getZeroPosition() {
    return zeroPosition;
  }

  void setZeroPosition(int zeroPosition) {
    this.zeroPosition = zeroPosition;
  }

  void print_board() {
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        System.out.print(this.board[i * this.size + j] + " ");
      }
      System.out.println();
    }

  }

  public static int Inversions(GameState board) {
    int inversions = 0;
    int position0;
    for (int i = 0; i < board.getSize(); i++) {
      for (int j = i + 1; j < board.getSize() - 1; j++) {
        if (board.at(i) != 0 && board.at(j) != 0 && board.at(i) > board.at(j))
          inversions++;
        else if (board.at(i) == 0) {
          position0 = i;
        }
      }
    }
    return inversions;
  }

  public static boolean Solvable(GameState board, int inversions) {
    for (int i = 0; i < board.getSize(); i++) {
      for (int j = i + 1; j < board.getSize() - 1; j++) {
        if (board.at(i) != 0 && board.at(j) != 0 && board.at(i) > board.at(j))
          inversions++;
      }
    }
    System.out.println(inversions);
    return inversions % 2 == 0;
  }

}