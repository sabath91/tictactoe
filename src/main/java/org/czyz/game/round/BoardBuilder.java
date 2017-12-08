package org.czyz.game.round;

import org.czyz.settings.BoardDimensions;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class BoardBuilder implements Observer {
    private final BoardDimensions dimensions;
    private List<Field> fields;
    //MoveHistory sounds better to me
    private MovesHistory moves;

    BoardBuilder(BoardDimensions dimensions) {
        this.dimensions = dimensions;
        moves = new MovesHistory();
    }

    BoardBuilder viaArrayList(){
        fields = new ArrayList<>(dimensions.boardSize());
        return this;
    }

    Board build(){
        return new Board(fields);
    }

    BoardBuilder fillUpBoard(){
        //+1 to show number but index --->  0 is too similar to O
        fields = IntStream.range(1, dimensions.boardSize()+1).mapToObj(EmptyField::new).collect(Collectors.toList());
        return this;
    }


    BoardBuilder fillWithMoves() {
        for (Position position : moves.getOMoves()) {
            fields.set(position.getIndex(), new OField());
        }
        for (Position position : moves.getXMoves()) {
            fields.set(position.getIndex(), new XField());
        }
        return this;
    }


    BoardBuilder fillWithMoves(MovesHistory movesHistory) {
        for (Position position: movesHistory.getOMoves()) {
            fields.set(position.getIndex(), new OField());
        }
        for (Position position: movesHistory.getXMoves()) {
            fields.set(position.getIndex(), new XField());
        }
        return this;
    }

    //consider to implement your own Observer <-> Observable relation
    @Override
    public void update(Observable movesHistory, Object arg) {
        moves = (MovesHistory) movesHistory;
    }
}
