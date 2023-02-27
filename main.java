import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        for (int k = 0; k < 1; k++) {
            GameState board = new GameState(4);
            board.read_board(in);
        }
        in.close();
    }

}
