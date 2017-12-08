package org.czyz.initgame;

class DimensionValidator {
    private static final int MIN_BOARD_DIMENSION = 3;
    private static final int MAX_BOARD_DIMENSION = 101;


    boolean validate(String userInput){
        int dim;
        try {
            dim = Integer.valueOf(userInput);
        }catch (NumberFormatException e){
            return false;
        }
        return dim>=MIN_BOARD_DIMENSION && dim<=MAX_BOARD_DIMENSION;
    }
}
