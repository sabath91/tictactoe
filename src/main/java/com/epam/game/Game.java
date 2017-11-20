package com.epam.game;

import com.epam.game.round.Round;
import com.epam.utils.Settings;

public class Game {


    private GameReferee gameReferee;    //todo final
    private final Settings settings;

    public Game(Settings settings) {
        this.settings = settings;
    }




    public void play() {


        gameReferee = new GameReferee(settings.getPlayer1(), settings.getPlayer2());
        for (int i=0; i<3; i++) {
            Round round = new Round(settings);
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
