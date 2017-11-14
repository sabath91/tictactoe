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
        load = 0;
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

    public void printBoard(){
        StringBuilder board = new StringBuilder();
        for(int i=0; i<gameBoard.size(); i++){
            board.append(gameBoard.get(i).getSingOrNumber(i)).append("\t");
            if((i+1)%xSize==0){
                board.append('\n');
            }
        }
        System.out.println(board);
    }

    public boolean isNotFull() {
        return load < gameBoard.size();
    }

    void mark(int move, Sign sign) {
        gameBoard.get(move-1).set(sign);
        load++;
    }

    public boolean isFieldEmpty(int move) {
        try {
            return gameBoard.get(move-1).isEmpty();
        }catch (IndexOutOfBoundsException e){
            return false;
        }

    }
}
