package org.czyz.game.round;

import java.util.List;

class Board {

    private final List<Field> board;

    //i would rename board to fields
    Board(List<Field> board) {
        this.board = board;
    }

    @Override
    public String toString() {
        return board.toString();
    }

    //consider to rename i to idx or index
    Field get(int i) {
        return board.get(i);
    }
}
