package com.epam.game.round;

import com.epam.initgame.DataProvider;
import com.epam.utils.Board;
import com.epam.utils.Player;

public class Round {
    private final Board board;
    private final MoveManager moveManager;
    private final RoundReferee roundReferee;
    private boolean isMovePossible = true;
    private boolean isBoardNotFull = true;
    public Round(DataProvider dataProvider) {
        board = Board.initializeGameBoard(dataProvider.getDimensions().getxSize() , dataProvider.getDimensions().getySize());
        moveManager = new MoveManager(board, dataProvider.getPlayer1(), dataProvider.getPlayer2());
        roundReferee = new RoundReferee(dataProvider.getWiningSequenceLength(), board);
    }



    public Player play() {
        while (isMovePossible && isBoardNotFull) {
            board.printBoard();
            makeMove();
            checkIfWinningMove();
        }
        board.printBoard();
        return getWinner();
    }

    public Player getWinner() {
        if(roundReferee.isWinningMove(moveManager.getLastMove(), moveManager.getLastPlayer().getSign())){
            return moveManager.getLastPlayer();
        }else {
            //draw
            return null;
        }
    }

    private void checkIfWinningMove() {
        isMovePossible = !roundReferee.isWinningMove(moveManager.getLastMove(), moveManager.getLastPlayer().getSign());
    }

    private void makeMove() {
        moveManager.handleMove();
        isBoardNotFull = board.isNotFull();
    }

}
