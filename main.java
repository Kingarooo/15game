import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // i want the number of inversions of an N*N-1 tile sliding game
        int[] board = new int[16];
        int inversions = 0;
        int numEstranho = 0;
        int index = 0;
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
