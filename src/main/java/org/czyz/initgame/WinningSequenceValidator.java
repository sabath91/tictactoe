package org.czyz.initgame;

class WinningSequenceValidator {
    private static final int MIN_BOARD_DIMENSION = 3;
    private static int smallerBoardDimension;

    WinningSequenceValidator(int smallerBoardDimension) {
        WinningSequenceValidator.smallerBoardDimension =smallerBoardDimension;
    }

    boolean validate(String userInput){
        int length;
        try {
            length = Integer.valueOf(userInput);
        }catch (NumberFormatException e){
            return false;
        }
        return length>=MIN_BOARD_DIMENSION && length<=smallerBoardDimension;
    }

}
