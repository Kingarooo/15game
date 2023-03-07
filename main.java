import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String modoPesquisa = args[0];
        GameState board = new GameState(4);
        GameState goalboard = new GameState(4);
        board.read_board(in);
        goalboard.read_board(in);
        System.out.println(modoPesquisa);

        if (modoPesquisa == "DFS") {
            if (board.isSolvable() == goalboard.isSolvable()) {
                DFS dfs = new DFS(board, goalboard);
                dfs.search();
            } else {
                System.out.println("No solution");
                return;
            }
        } else if (modoPesquisa.equals("BFS")) {
            if (board.isSolvable() == goalboard.isSolvable()) { // aqui nao falta por o caso de os 2 nao serem
                                                                // solvable??
                BFS bfs = new BFS(board, goalboard);
                bfs.search();
            } else {
                System.out.println("No solution");
                return;
            }
        }
        // }
        // if (modoPesquisa.equals("A*")) {
        // if (board.isSolvable() == goalboard.isSolvable()) {
        // A* a* = new A*(board, goalboard);
        // A*.searchA*();
        // } else {
        // System.out.println("No solution");
        // return;
        // }
        if (modoPesquisa.equals("Greedy")) {
            if (board.isSolvable() == goalboard.isSolvable()) {
                Greedy greedy = new Greedy(board, goalboard);
                greedy.search();
            } else {
                System.out.println("No solution");
                return;
            }
        } else {
            System.out.println("Invalid search algorithm selected.");
            return;
        }
        in.close();
    }
}
