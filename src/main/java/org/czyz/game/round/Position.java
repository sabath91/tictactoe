package org.czyz.game.round;

class Position {

    private final int movePosition;

    Position(int movePosition) {
        this.movePosition = movePosition;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        return movePosition == position.movePosition;
    }

    @Override
    public int hashCode() {
        return movePosition;
    }

    int getIndex() {
        return movePosition - 1;
    }
}
