import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
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

    static Board initializeGameBoard(int xSize, int ySize) {
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

    void printBoard() {
        Formatter board = new Formatter();
        for (int i = 0; i < gameBoard.size(); i++) {
            if ((i + 1) % xSize == 0) {
                board.format("%5s", gameBoard.get(i).getSingOrNumber(i));
            } else {
                board.format("%5s   |", gameBoard.get(i).getSingOrNumber(i));
            }
            if ((i + 1) % xSize == 0) {
                board.format("\n");
                for (int j = 0; j < xSize - 1; j++) {
                    board.format("%8s|", "--------");
                }
                board.format("%8s", "--------");
                board.format("\n");
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
        int resultSize = xSize;

        ArrayList<Field> result = new ArrayList<>();
        try {
            result.add(gameBoard.get(rowNumber * xSize + columnNumber ));
        }catch (IndexOutOfBoundsException e) {
            result.add(new Field(Sign.E));
        }

        for (int i = 1; i < resultSize; i++) {
            try {
                result.add(gameBoard.get(rowNumber * xSize + columnNumber  + i * (xSize + 1)));
            } catch (IndexOutOfBoundsException e) {
                result.add(new Field(Sign.E));
            }

        }
        return result;
    }

    public ArrayList<Field> getLeftDiagonal(int lastMove) {
        int initRowNumber = (lastMove - 1) / xSize;
        int initColumnNumber = (lastMove - 1) % xSize;
        int difference = Math.abs(Math.min(initColumnNumber, initRowNumber) - 0);
        int rowNumber = initRowNumber + difference;
        int columnNumber = initColumnNumber - difference;
        int resultSize =xSize;

        ArrayList<Field> result = new ArrayList<>();
        try {
            result.add(gameBoard.get(rowNumber * xSize + columnNumber * ySize));
        } catch (IndexOutOfBoundsException e) {
            result.add(new Field(Sign.E));
        }
        for (int i = 1; i < resultSize; i++) {
            try {
                result.add(gameBoard.get((rowNumber * xSize + columnNumber * ySize) - (i * (xSize - 1))));
            } catch (IndexOutOfBoundsException e) {
                result.add(new Field(Sign.E));
            }
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
