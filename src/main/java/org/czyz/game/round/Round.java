package org.czyz.game.round;

import org.czyz.ui.Printer;
import org.czyz.game.Score;
import org.czyz.settings.Settings;

import java.util.ResourceBundle;

public class Round {

    private final BoardPrinter boardPrinter;
    private final MoveManager moveManager;
    private final PlayerSwitcher playerSwitcher;
    private final RoundReferee roundReferee;
    private Score score;
    private final Printer printer;
    private final ResourceBundle labels;

    public Round(Settings settings, Printer printer) {
        BoardBuilder boardBuilder = new BoardBuilder(settings.getBoardDimensions());
        this.boardPrinter = new BoardPrinter(boardBuilder, settings.getBoardDimensions(), printer);
        this.playerSwitcher = new PlayerSwitcher(settings.getPlayer1(), settings.getPlayer2(), settings.getStartingPlayer());
        this.roundReferee = new RoundReferee(settings);
        MovesHistory movesHistory = new MovesHistory();
        this.moveManager = new MoveManager(settings,printer, playerSwitcher, movesHistory);
        movesHistory.addObserver(boardBuilder);
        movesHistory.addObserver(roundReferee);
        this.printer = printer;
        this.labels = ResourceBundle.getBundle("lang");
    }

    public Score play() {
        while (roundReferee.canBeContinued()) {
            boardPrinter.print();
            moveManager.handleMove();
            playerSwitcher.switchPlayers();
        }
        boardPrinter.print();
        score = roundReferee.score();
        printScore();
        return score;
    }

    private void printScore() {
        if(score.isDraw()){
            printer.print(labels.getString("roundDraw"));
        }else {
            printer.print(labels.getString("roundWon")+playerSwitcher.lastPlayer());
        }
    }

}
