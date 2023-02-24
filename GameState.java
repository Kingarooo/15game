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

}