import java.util.ArrayList;

class RoundReferee {

    //TODO or Setting winingArea = Settings.getWinningArea;
    int winningArea;
    //todo
    Sign sign = Sign.X;
    private Board board;
    int lastMove;
    //todo

    public RoundReferee(int winningArea, Board board) {
        this.winningArea = winningArea;
        this.board = board;
    }

    public boolean isWinningMove(int move) {
        lastMove=move;
        return winOnRow() || winOnColumn() || winOnRightDiagonal() || winOnLeftDiagonal();
    }

    private boolean winOnLeftDiagonal() {
        ArrayList<Field> list= board.getLeftDiagonal(lastMove);
        return isWinningSequenceOnList(list);
    }


    private boolean winOnRightDiagonal() {
        ArrayList<Field> list= board.getRightDiagonal(lastMove);
        return isWinningSequenceOnList(list);
    }

    private boolean winOnColumn() {
        ArrayList<Field> list= board.getColumn(lastMove);
        return isWinningSequenceOnList(list);
    }

    boolean winOnRow(){
       ArrayList<Field> list= board.getRow(lastMove);
       return isWinningSequenceOnList(list);
    }


    private boolean isWinningSequenceOnList(ArrayList<Field> list) {
        int maxLength=0;
        for(Field field : list){
            if(field.getSign().equals(sign.getSign())){
                maxLength ++;
                if(maxLength >= winningArea){
                    return true;
                }
            }else {
                maxLength = 0;
            }
        }
        return false;
    }


}
