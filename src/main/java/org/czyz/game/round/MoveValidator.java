package org.czyz.game.round;

import org.czyz.settings.BoardDimensions;

class MoveValidator{

    private final BoardDimensions dimensions;
    private final MovesHistory moves;

    MoveValidator(BoardDimensions dimensions, MovesHistory movesHistory) {
        this.moves = movesHistory;
        this.dimensions = dimensions;
    }

    boolean validate(String userInput){
        int position;
        try {
            position = Integer.parseInt(userInput);
            if(position == 0){
                System.exit(0);
            }
        }catch (NumberFormatException e ){
            return false;
        }
        return isInRange(position) && moves.isPositionFree(new Position(position));
    }

    private boolean isInRange(int position) {
        return position > 0 && position <= dimensions.boardSize()+1;
    }

}
