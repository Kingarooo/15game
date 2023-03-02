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
            GameState successors[] = board.getSuccessors();
            
            for (int i = 0; i < successors.length; i++) {
                System.out.println(successors[i]);
            }if (modoPesquisa.equals("BFS")) {
            // GameState.BFS();
        } 
        else {
            System.out.println("Invalid search algorithm selected.");
            return;
        }
        }
        
        in.close();
    }

}
