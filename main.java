import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String modoPesquisa = args[0];
        GameState board = new GameState(4);
        GameState goalboard = new GameState(4);
        board.read_board(in);
        goalboard.read_board(in);

        switch (modoPesquisa) {
            case "DFS":
                if (board.isSolvable() == goalboard.isSolvable()) {
                    DFS dfs = new DFS(board, goalboard);
                    dfs.search();
                    return;
                } else {
                    System.out.println("No solution");
                    return;
                }
            case "BFS":
                if (board.isSolvable() == goalboard.isSolvable()) {

                    BFS bfs = new BFS(board, goalboard);
                    bfs.search();
                    return;
                } else {
                    System.out.println("No solution");
                    return;
                }
            default:
                System.out.println("Invalid search algorithm selected.");

        }
        in.close();
    }
}
