package org.czyz.game.round;

import org.czyz.settings.Sign;
import org.czyz.game.*;
import org.czyz.settings.BoardDimensions;
import org.czyz.settings.Settings;
import org.czyz.settings.WinningSequenceLength;

import java.util.*;

class RoundReferee implements Observer {
    private final WinningSequenceLength winningSequenceLength;
    private final int boardWidth;
    private final int boardHeight;
    private final BoardDimensions boardDimensions;
    private MovesHistory movesHistory;
    private Move lastMove;
    private Field wanted;
    private Board board;
    private boolean canBeContinued;


    RoundReferee(Settings settings) {
        movesHistory = new MovesHistory();
        winningSequenceLength = settings.getWinningSequenceLength();
        boardDimensions = settings.getBoardDimensions();
        boardWidth = settings.getBoardDimensions().getWidth();
        boardHeight = settings.getBoardDimensions().getHeight();
        canBeContinued = true;

    }

    boolean canBeContinued() {
        return canBeContinued;
    }

    @Override
    public void update(Observable movesHistory, Object arg) {
        this.movesHistory = (MovesHistory) movesHistory;
        this.lastMove = ((MovesHistory) movesHistory).getLastMove();

        board = new BoardBuilder(boardDimensions)
                .viaArrayList()
                .fillUpBoard()
                .fillWithMoves(this.movesHistory)
                .build();
        canBeContinued = !isWinningMove() && isBoardNotFull();
    }

    private boolean isBoardNotFull() {
        return movesHistory.size() < boardDimensions.boardSize();
    }

    private boolean isWinningMove() {
        Sign sign = lastMove.sign;
        switch (sign) {
            case O:
                wanted = new OField();
                break;
            case X:
                wanted = new XField();
                break;
        }
        return winOnRow() || winOnColumn() || winOnAscendingDiagonal() || winOnDescendingDiagonal();
    }

    private boolean winOnDescendingDiagonal() {
        List<Field> list = descendingDiagonal(lastMove.position.getIndex());
        return isWinningSequenceOnList(list);
    }


    private boolean winOnAscendingDiagonal() {
        List<Field> list = ascendingDiagonal(lastMove.position.getIndex());
        return isWinningSequenceOnList(list);
    }

    private boolean winOnColumn() {
        List<Field> list = getColumn(lastMove.position.getIndex());
        return isWinningSequenceOnList(list);
    }

    private boolean winOnRow() {
        List<Field> list = getRow(lastMove.position.getIndex());
        return isWinningSequenceOnList(list);
    }


    private boolean isWinningSequenceOnList(List<Field> list) {
        int maxLength = 0;
        for (Field field : list) {
            if (field.toString().equals(wanted.toString())) {
                maxLength++;
                if (maxLength >= winningSequenceLength.value()) return true;
            } else {
                maxLength = 0;
            }
        }
        return false;
    }

    private List<Field> getRow(int lastMove) {
        int rowNumber = lastMove / boardWidth;
        ArrayList<Field> result = new ArrayList<>(boardWidth);
        for (int i = 0; i < boardWidth; i++) {
            result.add(board.get(rowNumber * boardWidth + i));
        }
        return result;
    }

    private List<Field> ascendingDiagonal(int lastMarkedIndex) {
        List<Field> diagonal = new ArrayList<>();

        diagonal.addAll(overIndexForAscending(lastMarkedIndex)); //just elements over
        diagonal.add(board.get(lastMarkedIndex)); //this index
        diagonal.addAll(underIndexForAscending(lastMarkedIndex)); //just elements under index;

        return diagonal;
    }

    private List<Field> underIndexForAscending(int lastMarkedIndex) {
        List<Field> result = new ArrayList<>();
        int step = boardWidth -1;
        int previousColumn = getColumnNumber(lastMarkedIndex);
        int index = lastMarkedIndex + step;

        while (index < boardDimensions.boardSize() && previousColumn>getColumnNumber(index)){
            result.add(board.get(index));
            index+=step;
            previousColumn--;
        }
        return result;
    }

    private List<Field> overIndexForAscending(int lastMarkedIndex) {
        List<Field> result = new ArrayList<>();
        int step = boardWidth - 1;
        int previousColumn = getColumnNumber(lastMarkedIndex);
        int index = lastMarkedIndex - step;

        while (index >= 0 && previousColumn<getColumnNumber(index)){
            result.add(board.get(index));
            index-=step;
            previousColumn++;
        }
        Collections.reverse(result);
        return result;
    }

    private int getColumnNumber(int index) {
        return index % boardWidth;
    }

    private List<Field> descendingDiagonal(int lastMarkedIndex) {
        List<Field> diagonal = new ArrayList<>();

        diagonal.addAll(overIndexForDescending(lastMarkedIndex)); //just elements over index
        diagonal.add(board.get(lastMarkedIndex)); //this index
        diagonal.addAll(underIndexForDescending(lastMarkedIndex)); //just elements under index;

        return diagonal;
    }

    private List<Field> underIndexForDescending(int lastMarkedIndex) {
        List<Field> result = new ArrayList<>();
        int step = boardWidth +1;
        int previousColumn = getColumnNumber(lastMarkedIndex);
        int index = lastMarkedIndex + step;

        while (index < boardDimensions.boardSize() && previousColumn<getColumnNumber(index)){
            result.add(board.get(index));
            index+=step;
            previousColumn++;
        }
        return result;
    }

    private List<Field> overIndexForDescending(int lastMarkedIndex) {
        List<Field> result = new ArrayList<>();
        int step = boardWidth +1;
        int previousColumn = getColumnNumber(lastMarkedIndex);
        int index = lastMarkedIndex - step;
        while (index>=0 && previousColumn > getColumnNumber(index)){
            result.add(board.get(index));
            index -=step;
            previousColumn--;
        }
        Collections.reverse(result);
        return result;
    }

    private List<Field> getColumn(int lastMove) {
        int columnNumber = lastMove  % boardWidth;
        ArrayList<Field> result = new ArrayList<>(boardHeight);
        for (int i = 0; i < boardHeight; i++) {
            int index = columnNumber + (boardWidth * i);
            result.add(board.get(index));
        }
        return result;

    }

    Score score() {
        Score score = new Score();
        if (isBoardNotFull() && !canBeContinued) { // restoring value of isWinningMove without calculations
            if (lastMove.sign.equals(Sign.O)) {
                score.player1Win();
            } else {
                score.player2Win();
            }
        } else { // draw
            score.markDraw();
        }
        return score;
    }
}
