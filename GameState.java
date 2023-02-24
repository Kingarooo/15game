import java.util.Scanner;
import java.util.Scanner;

class GameState {
  // constructor
  private int size;
  private int[] board;

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

  void read_board() {
    Scanner in = new Scanner(System.in);
    int index = 0;
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        this.board[index++] = in.nextInt();
      }
    }
  }

  void print_board() {

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