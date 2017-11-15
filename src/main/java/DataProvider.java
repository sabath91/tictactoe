import java.util.Scanner;

class DataProvider {

    private Scanner scanner;
    private Player player1;
    private Player player2;
    private Dimensions dimensions;
    private int winingSequenceLength;

    DataProvider() {
        this.scanner = new Scanner(System.in);
    }

    void createPlayers() {
        player1 = createPlayer(Sign.X);
        player2 = createPlayer(Sign.O);
    }

    private Player createPlayer(Sign sign) {
        System.out.println("Podaj imię gracza grającego " + sign.getSign());
        String name = scanner.nextLine();
        return new Player(name, sign);
    }

    void createBoardDimensions() {
        System.out.println("Proszę podać wymiary planszy");
        System.out.print("x: ");
        int xSize = scanner.nextInt();
        System.out.print("y: ");
        int ySize = scanner.nextInt();
        this.dimensions = new Dimensions(xSize, ySize);
        if (!dimensions.areValid()) {
            System.out.println("Minimlane wymiary planszy to 3x3. Spróbuj jeszcze raz:");
            createBoardDimensions();
        }

    }

    void setWiningSequenceLength() {
        System.out.println("Podaj długość zwycięskiego ciągu");
        winingSequenceLength = scanner.nextInt();
        if ((winingSequenceLength > Math.min(dimensions.xSize, dimensions.ySize)) || (winingSequenceLength < 3)) {
            System.out.println("Podaj liczbę z przedziału <3, mniejszyWymiarPlanszy>! Spróbuj jeszcze raz");
            setWiningSequenceLength();
        }

    }

    Dimensions getDimensions() {
        return dimensions;
    }

    Player getPlayer1() {
        return player1;
    }

    Player getPlayer2() {
        return player2;
    }

    public int getWiningSequenceLength() {
        return winingSequenceLength;
    }
}
