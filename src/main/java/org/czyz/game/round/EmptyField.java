package org.czyz.game.round;

class EmptyField implements Field {
    private final int index;

    EmptyField(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return String.valueOf(index);
    }
}
