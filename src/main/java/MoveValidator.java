class MoveValidator {

    Board board;

    MoveValidator(Board board) {

        this.board = board;
    }

    boolean validate(int move) {
        return board.isFieldEmpty(move);
    }
}
