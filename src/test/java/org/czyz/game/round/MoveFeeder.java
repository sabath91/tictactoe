package org.czyz.game.round;

import org.czyz.settings.Sign;

import java.util.stream.IntStream;

class MoveFeeder {
    private final MovesHistory movesHistory;

    MoveFeeder(MovesHistory movesHistory) {
        this.movesHistory = movesHistory;
    }



    void fillRow(Triplet triplet) {
        IntStream.iterate(0, i -> i + 1)
                .limit(triplet.width())
                .limit(triplet.length())
                .forEach(index -> movesHistory.markField(new Position(index + 1), Sign.X));
    }

    void fillColumn(Triplet triplet) {
        IntStream.iterate(0, i -> i + triplet.width())
                .limit(triplet.width())
                .limit(triplet.length())
                .forEach(index -> movesHistory.markField(new Position(index + 1), Sign.X));
    }

    void fillAscending(Triplet triplet) {
        IntStream.iterate(triplet.height() * triplet.width() - triplet.width(), i -> i - triplet.width() + 1)
                .limit(triplet.height())
                .limit(triplet.length())
                .forEach(index ->  movesHistory.markField(new Position(index + 1), Sign.X));
    }

    void fillDescending(Triplet triplet) {
        IntStream.iterate(0, i -> i + triplet.width() + 1)
                .limit(triplet.height())
                .limit(triplet.length())
                .forEach(index -> movesHistory.markField(new Position(index + 1), Sign.X));
    }
}
