public class Game {

    DataProvider dataProvider;

    public Game() {
        this.dataProvider = new DataProvider();
    }




    void play() {

        System.out.println("Tic-Tac-Toe v1.0");

        dataProvider.createPlayers();
        dataProvider.createBoardDimensions();
        dataProvider.setWiningSequenceLength();
        Round round = new Round(dataProvider);
        if(round.play()!=null){
            System.out.println(">>>>WYGRYWA<<<<>>>>" + round.play().getName().toUpperCase() + "<<<<");
        }else{
            System.out.println(">>>>REMIS<<<<");
        }


    }
}
