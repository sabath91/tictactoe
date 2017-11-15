import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Board {

    private int xSize;
    private int ySize;
    private List<Field> gameBoard;
    private int load;


    Board(int xSize, int ySize, List<Field> gameBoard) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.gameBoard = gameBoard;
        load = 0;
    }

    public static Board initializeGameBoard(int xSize, int ySize) {
        int boardSize = xSize * ySize;
        List<Field> gameBoard = new ArrayList<>(boardSize);
        for (int i = 0; i < boardSize; i++) {
            gameBoard.add(new Field(Sign.E));
        }
        return new Board(xSize, ySize, gameBoard);
    }

    List<Field> getGameBoard() {
        return Collections.unmodifiableList(gameBoard);
    }

    public void printBoard() {
        StringBuilder board = new StringBuilder();
        for (int i = 0; i < gameBoard.size(); i++) {
            board.append(gameBoard.get(i).getSingOrNumber(i)).append("\t");
            if ((i + 1) % xSize == 0) {
                board.append('\n');
            }
        }
        System.out.println(board);
    }

    public boolean isNotFull() {
        return load < gameBoard.size();
    }

    void mark(int move, Sign sign) {
        gameBoard.get(move - 1).set(sign);
        load++;
    }

    public boolean isFieldEmpty(int move) {
        try {
            return gameBoard.get(move - 1).isEmpty();
        } catch (IndexOutOfBoundsException e) {
            return false;
        }

    }

    public ArrayList<Field> getRow(int lastMove) {
        int rowNumber = (lastMove - 1) / xSize;
        ArrayList<Field> result = new ArrayList<>(xSize);
        for (int i = 0; i < xSize; i++) {
            result.add(gameBoard.get(rowNumber * xSize + i));
        }
        return result;
    }

    public ArrayList<Field> getRightDiagonal(int lastMove) {
        int initRowNumber = (lastMove - 1) / xSize;
        int initColumnNumber = (lastMove - 1) % xSize;
        int difference = Math.abs(Math.min(initColumnNumber, initRowNumber) - 0);
        int rowNumber = initRowNumber - difference;
        int columnNumber = initColumnNumber - difference;
        int resultSize = Math.min(xSize, ySize);

        ArrayList<Field> result = new ArrayList<>();
        result.add(gameBoard.get(rowNumber + columnNumber));
       try {
           for (int i = 1; i < resultSize; i++) {
               result.add(gameBoard.get(rowNumber + columnNumber + i * (xSize + 1)));
           }
       }catch (IndexOutOfBoundsException e){
           //
       }
        return result;
    }

    public ArrayList<Field> getLeftDiagonal(int lastMove) {
        int initRowNumber = (lastMove - 1) / xSize;
        int initColumnNumber = (lastMove - 1) % xSize;
        int difference = Math.abs(Math.min(initColumnNumber, initRowNumber) - 0);
        int rowNumber = initRowNumber - difference;
        int columnNumber = initColumnNumber + difference;
        int resultSize = Math.min(xSize, ySize);

        ArrayList<Field> result = new ArrayList<>(resultSize);
        result.add(gameBoard.get(rowNumber + columnNumber));
        for (int i = 1; i < resultSize; i++) {
            result.add(gameBoard.get(rowNumber + columnNumber + xSize - 1));
        }
        return result;
    }

    public ArrayList<Field> getColumn(int lastMove) {
        int columnNumber = (lastMove - 1) % xSize;
        ArrayList<Field> result = new ArrayList<>(ySize);
        for (int i = 0; i < ySize; i++) {
            int index = columnNumber + (xSize * i);
            result.add(gameBoard.get(index));
        }
        return result;

    }
}
