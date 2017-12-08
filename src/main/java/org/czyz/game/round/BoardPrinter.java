package org.czyz.game.round;

import org.czyz.ui.Printer;
import org.czyz.settings.BoardDimensions;
import java.util.Formatter;

class BoardPrinter {

    private final BoardBuilder boardBuilder;
    private final int boardWidth;
    private final int boardSize;
    private final Printer printer;

    BoardPrinter(BoardBuilder boardBuilder, BoardDimensions dimensions, Printer printer) {
        this.boardBuilder = boardBuilder;
        this.boardSize = dimensions.boardSize();
        this.boardWidth = dimensions.getWidth();
        this.printer = printer;
    }

    void print() {

        Board gameBoard = boardBuilder
                .viaArrayList()
                .fillUpBoard()
                .fillWithMoves()
                .build();

        Formatter board = null;
        try {
            board = new Formatter();
            for (int i = 0; i < boardSize; i++) {
                if ((i + 1) % boardWidth == 0) {
                    board.format("%5s", gameBoard.get(i));
                } else {
                    board.format("%5s   |", gameBoard.get(i));
                }
                if ((i + 1) % boardWidth == 0) {
                    board.format("%n");
                    for (int j = 0; j < boardWidth - 1; j++) {
                        board.format("%8s|", "--------");
                    }
                    board.format("%8s", "--------");
                    board.format("%n");
                }
            }
            printer.print(board);
        } finally {
            if (board != null)
                board.close();
        }
    }
}
