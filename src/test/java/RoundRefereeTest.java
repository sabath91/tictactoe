import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class RoundRefereeTest {


    List<Field> testBoard;
    RoundReferee roundReferee;
    int winningArea = 3;
    int xSize = 3;
    int ySize = 3;

    public void shouldWinWhenInRow() {
        //given
        testBoard = new ArrayList<>(Arrays.asList(
                new Field(Sign.X), new Field(Sign.X), new Field(Sign.X),
                new Field(Sign.O), new Field(Sign.O), new Field(Sign.X),
                new Field(Sign.X), new Field(Sign.O), new Field(Sign.O)
        ));
        roundReferee = new RoundReferee(winningArea, new Board(xSize, ySize, testBoard));

        //when
        boolean result = roundReferee.isWinningMove(2);

        //then
        assertTrue(result);
    }

    public void shouldWin() {
        //given
        testBoard = new ArrayList<>(Arrays.asList(
                new Field(Sign.X), new Field(Sign.E), new Field(Sign.E),
                new Field(Sign.O), new Field(Sign.X), new Field(Sign.E),
                new Field(Sign.E), new Field(Sign.E), new Field(Sign.E),
                new Field(Sign.E), new Field(Sign.E), new Field(Sign.E)
        ));
        roundReferee = new RoundReferee(winningArea, new Board(xSize, 4, testBoard));

        //when
        boolean result = roundReferee.isWinningMove(5);

        //then
        assertTrue(result);
    }

    public void shouldWinWhenInColumn() {
        //given
        testBoard = new ArrayList<>(Arrays.asList(
                new Field(Sign.X), new Field(Sign.E), new Field(Sign.O),
                new Field(Sign.X), new Field(Sign.E), new Field(Sign.E),
                new Field(Sign.X), new Field(Sign.O), new Field(Sign.O)
        ));
        roundReferee = new RoundReferee(winningArea, new Board(xSize, ySize, testBoard));

        //when
        boolean result = roundReferee.isWinningMove(4);

        //then
        assertTrue(result);
    }

    public void shouldWinWhenOnLeftDiagonal() {
        //given
        testBoard = new ArrayList<>(Arrays.asList(
                new Field(Sign.O), new Field(Sign.E), new Field(Sign.X),
                new Field(Sign.O), new Field(Sign.X), new Field(Sign.X),
                new Field(Sign.X), new Field(Sign.E), new Field(Sign.O)
        ));
        roundReferee = new RoundReferee(winningArea, new Board(xSize, ySize, testBoard));

        //when
        boolean result = roundReferee.isWinningMove(3);

        //then
        assertTrue(result);
    }

    public void shouldWinWhenOnRightDiagonal() {
        //given
        testBoard = new ArrayList<>(Arrays.asList(
                new Field(Sign.X), new Field(Sign.E), new Field(Sign.E),
                new Field(Sign.O), new Field(Sign.X), new Field(Sign.O),
                new Field(Sign.X), new Field(Sign.O), new Field(Sign.X)
        ));
        roundReferee = new RoundReferee(winningArea, new Board(xSize, ySize, testBoard));

        //when
        boolean result = roundReferee.isWinningMove(9);

        //then
        assertTrue(result);
    }

    public void shouldNotWinWhenOnlyEmptySignsInRow() {
        //given
        testBoard = new ArrayList<>(Arrays.asList(
                new Field(Sign.E), new Field(Sign.E), new Field(Sign.E),
                new Field(Sign.E), new Field(Sign.E), new Field(Sign.E),
                new Field(Sign.E), new Field(Sign.E), new Field(Sign.E)
        ));
        roundReferee = new RoundReferee(winningArea, new Board(xSize, ySize, testBoard));

        //when
        boolean result = roundReferee.isWinningMove(2);

        //then
        assertFalse(result);
    }

    public void shouldNotWinWhenInterruptedByOtherSign() {
        //given
        testBoard = new ArrayList<>(Arrays.asList(
                new Field(Sign.X), new Field(Sign.X), new Field(Sign.E),
                new Field(Sign.E), new Field(Sign.O), new Field(Sign.E),
                new Field(Sign.E), new Field(Sign.E), new Field(Sign.E)
        ));
        roundReferee = new RoundReferee(winningArea, new Board(xSize, ySize, testBoard));

        //when
        boolean result = roundReferee.isWinningMove(2);

        //then
        assertFalse(result);
    }
}