import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        for (int k = 0; k < 1; k++) {
            String modoPesquisa = in.next();
            GameState board = new GameState(4);
            GameState goalboard = new GameState(4);
            board.read_board(in);
            goalboard.read_board(in);

            if (modoPesquisa.equals("BFS")) {
                if (board.isSolvable() == goalboard.isSolvable()) {
                    BFS bfs = new BFS(board, goalboard);
                    bfs.search();
                } else {

                    System.out.println("No solution");
                    return;
                }

            } else {
                System.out.println("Invalid search algorithm selected.");
                return;
            }
        }

        in.close();
    }

}
