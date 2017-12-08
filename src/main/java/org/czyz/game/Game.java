package org.czyz.game;

import org.czyz.ui.Printer;
import org.czyz.game.round.Round;
import org.czyz.settings.Settings;

import java.util.ResourceBundle;

public class Game {
    private final Score gameScore;
    private final Settings settings;
    private final ResourceBundle labels;
    private final Printer printer;

    public Game(Settings settings, Printer printer) {
        this.settings = settings;
        this.labels = ResourceBundle.getBundle("lang");
        gameScore = new Score();
        this.printer = printer;
        
    }

    public void play() {
        //magic number - replace with constant
        for (int i = 0; i < 3; i++) {
            Round round = new Round(settings, printer);
            Score score = round.play();
            gameScore.player1Score += score.player1Score;
            gameScore.player2Score += score.player2Score;
        }
        //it does two things - consider to give a more general name, and extract the play part to a separate method
        printScore();
    }

    /*
    handle {
        play()
        print()
    }
     */

    private void printScore() {
        if (gameScore.isDraw()) {
            printer.print(labels.getString("draw"));
        } else {
            //consider to extract this part to a dedicated method printWinner
            //consider to create getWinner, getLoser,then you will not need this condition
            if (gameScore.player1Score > gameScore.player2Score) {
                //toString is normally called automatically whenever necessary, consider not extracting a sign value manually
                String winner = settings.getPlayer1().getSign().toString();
                String loser = settings.getPlayer2().getSign().toString();
                //duplication, consider to print after a winner is resolved
                printer.print(labels.getString("gameWon") + winner + ". " + winner + ":" + gameScore.player1Score + " " + loser +":"+gameScore.player2Score);
            } else {
                String winner = settings.getPlayer1().getSign().toString();
                String loser = settings.getPlayer2().getSign().toString();
                printer.print(labels.getString("gameWon") + winner + ". " + winner + ":" + gameScore.player2Score + " " + loser +":"+gameScore.player1Score);
            }

        }
    }
}
