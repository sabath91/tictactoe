import java.util.Scanner;

class MockedDataProvider extends DataProvider {

    private Player player1= new Player("PlayerX", Sign.X);
    private Player player2 =new Player("PlayerO", Sign.O) ;
    private Dimensions dimensions = new Dimensions(3,3);
    private int winingSequenceLength = 3;

    void createPlayers() {
        player1= new Player("PlayerX", Sign.X);
        player2 =new Player("PlayerO", Sign.O) ;
    }

    void createBoardDimensions() {
        dimensions = new Dimensions(3,3);
    }

    void setWiningSequenceLength() {
        winingSequenceLength = 3;
    }
}
