package org.czyz.game.round;

import org.czyz.settings.Sign;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

class MovesHistory extends Observable {

    private final Set<Position> xMoves;
    private final Set<Position> oMoves;
    private Move lastMove;

    MovesHistory() {
        xMoves = new HashSet<>();
        oMoves = new HashSet<>();
    }

    boolean isPositionFree(Position position) {
        return !xMoves.contains(position) && !oMoves.contains(position);
    }


    void markField(Position position, Sign playerSign) {

        switch (playerSign) {
            case O:
                oMoves.add(position);
                break;
            case X:
                xMoves.add(position);
                break;
        }
        lastMove = new Move(position, playerSign);
        setChanged();
        notifyObservers();
    }


    Move getLastMove() {
        return lastMove;
    }

    Set<Position> getXMoves() {
        return xMoves;
    }

    Set<Position> getOMoves() {
        return oMoves;
    }

    int size(){
        return xMoves.size() + oMoves.size();
    }
}
