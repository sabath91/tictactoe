class Round {
    private final Player player1;
    private final Player player2;
    private final Board board;
    private final MoveManager moveManager;
    private final RoundReferee roundReferee;
    private boolean isMovePossible = true;

    Round(Player player1, Player player2, int xSize, int ySize) {
        this.player1 = player1;
        this.player2 = player2;
        board = Board.initializeGameBoard(xSize, ySize);
        moveManager = new MoveManager(board, player1, player2);
        roundReferee = new RoundReferee();
    }


    Player play() {
        while (isMovePossible) {
            board.printBoard();
            makeMove();
            checkIfWinningMove();
        }
        return getWinner();
    }

    private Player getWinner() {
        if(roundReferee.isWinningMove(moveManager.getLastMove())){
            return moveManager.lastPlayer();
        }else {
            //draw
            return null;
        }
    }

    private void checkIfWinningMove() {
        isMovePossible = !roundReferee.isWinningMove(moveManager.getLastMove());
    }

    private void makeMove() {
        moveManager.handleMove();
        isMovePossible = board.isNotFull();
    }

}
