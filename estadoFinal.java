import java.util.Scanner;
public class estadoFinal {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int finali []  = new int [15];
        for (int i = 0; i < 15; i++){
            int num = in.nextInt();
            finali[num-1] = num;
        }
    }
}
