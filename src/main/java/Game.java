import java.util.Scanner;

public class Game {


    public static void main(String[] args) {
        System.out.println("Tic-Tac-Toe v1.0");
        System.out.println("Create first player, please provide your name");
        Scanner scanner = new Scanner(System.in);
        String player1Name = scanner.nextLine();
        Player player1 = new Player(player1Name, Sign.X);
        System.out.println("Create second player, please provide your name");
        String player2Name = scanner.nextLine();
        Player player2 = new Player(player2Name, Sign.O);

        System.out.println("Please provide board dimensions");
        System.out.print("x: ");
        int xSize = scanner.nextInt();
        System.out.print("y: ");
        int ySize = scanner.nextInt();

        Round round = new Round(player1, player2, xSize, ySize);
        //   |-> then create board
        //   |-> then logic to round (moves, filing board, etc.

    }


}
