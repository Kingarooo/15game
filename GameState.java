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

  public void read_board() {
    Scanner in = new Scanner(System.in);
    int index = 0;
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        this.board[index++] = in.nextInt();
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

  public int Inversions() {
    int inversions = 0;
    int position0;
    for (int i = 0; i < this.getSize(); i++) {
      for (int j = i + 1; j < this.getSize() - 1; j++) {
        if (this.at(i) != 0 && this.at(j) != 0 && this.at(i) > this.at(j))
          inversions++;
        else if (this.at(i) == 0) {
          position0 = i;
        }
      }
    }
    return inversions;
  }

  public boolean HelperSolution(int inversions) {
    return inversions % 2 == 0;
  }

  public void Solvable(){
    if (this.HelperSolution(this.Inversions()) == ((zeroPosition/4 + 1) % 2 == 0))
        System.out.println("É solvable");
    else 
        System.out.println("Não é solvable");
    return;
  }
}
