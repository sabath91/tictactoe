import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Board {

    private int xSize;
    private List<Field> gameBoard;
    private int load;


    Board(int xSize, List<Field> gameBoard) {
        this.xSize = xSize;
        this.gameBoard = gameBoard;
    }

    public static Board initializeGameBoard(int xSize, int ySize){
        int boardSize = xSize * ySize;
        List<Field> gameBoard = new ArrayList<>(boardSize);
        for(int i=0;i<boardSize;i++){
            gameBoard.add(new Field(Sign.E));
        }
        return new Board(xSize, gameBoard);
    }

    List<Field> getGameBoard() {
        return Collections.unmodifiableList(gameBoard);
    }

    void printBoard(){
        StringBuilder board = new StringBuilder();
        for(int i=0; i<gameBoard.size(); i++){
            board.append(gameBoard.get(i).getSingOrNumber(i));
            if((i+1)%xSize==0){
                board.append('\n');
            }
        }
        System.out.println(board);
    }

    public boolean isNotFull() {
        return load < gameBoard.size();
    }

    public void mark(int move, Sign sign) {
        //TODO
    }
}
