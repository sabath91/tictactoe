package org.czyz.game.round;

import org.czyz.settings.Sign;

class Move {
    final Position position;
    final Sign sign;

    Move(Position position, Sign sign) {
        this.position = position;
        this.sign = sign;
    }
}
