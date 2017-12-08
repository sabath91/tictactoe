package org.czyz.game.round;

import org.czyz.ui.Printer;
import org.czyz.settings.Sign;
import org.czyz.settings.Settings;

import java.util.ResourceBundle;

class MoveManager {
    private final MoveHandler moveHandler;
    private final MoveValidator moveValidator;
    private final PlayerSwitcher playerSwitcher;
    private final MovesHistory movesHistory;
    private final Printer printer;
    private final ResourceBundle labels;

    MoveManager(Settings settings, Printer printer, PlayerSwitcher playerSwitcher, MovesHistory movesHistory) {
        this.printer = printer;
        this.movesHistory = movesHistory;
        this.playerSwitcher = playerSwitcher;
        this.moveValidator = new MoveValidator(settings.getBoardDimensions(), movesHistory);
        this.labels= ResourceBundle.getBundle("lang");
        this.moveHandler =new MoveHandler();
    }

    void handleMove(){
        printer.print(playerSwitcher.getCurrentPlayer() + labels.getString("move"));
        String validUserInput = moveHandler.action(printer::print, moveValidator::validate);
        int move = Integer.parseInt(validUserInput);
        Position position = new Position(move);
        Sign playerSign = playerSwitcher.getCurrentPlayer().getSign();
        movesHistory.markField(position, playerSign);
    }

}
