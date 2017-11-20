public class Game {

    DataProvider dataProvider;
    private GameReferee gameReferee;

    public Game() {
        this.dataProvider = new DataProvider();
    }




    void play() {

        System.out.println("Tic-Tac-Toe v1.0");

        dataProvider.createPlayers();
        dataProvider.createBoardDimensions();
        dataProvider.setWiningSequenceLength();

        gameReferee = new GameReferee(dataProvider.getPlayer1(), dataProvider.getPlayer2());
        for (int i=0; i<3; i++) {
            Round round = new Round(dataProvider);
            if (round.play() != null) {
                System.out.println("Rundę wygrywa: "+ round.getWinner().getName());
                gameReferee.givePointTo(round.getWinner());
            } else {
                System.out.println("Runda zakończona remisem");
                gameReferee.giveEverybodyOnePoint();
            }
        }

        printGameWinner();
    }

    private void printGameWinner() {
        System.out.println("\n\n\n"+gameReferee.getWinner());

    }
}
