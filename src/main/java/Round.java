class Round {
    private final Board board;
    private final MoveManager moveManager;
    private final RoundReferee roundReferee;
    private boolean isMovePossible = true;
    private boolean isBoardNotFull = true;
    Round(DataProvider dataProvider) {
        board = Board.initializeGameBoard(dataProvider.getDimensions().xSize , dataProvider.getDimensions().ySize);
        moveManager = new MoveManager(board, dataProvider.getPlayer1(), dataProvider.getPlayer2());
        roundReferee = new RoundReferee(dataProvider.getWiningSequenceLength(), board);
    }



    Player play() {
        while (isMovePossible && isBoardNotFull) {
            board.printBoard();
            makeMove();
            checkIfWinningMove();
        }
        board.printBoard();
        return getWinner();
    }

    private Player getWinner() {
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
