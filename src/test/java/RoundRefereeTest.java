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
        boolean result = roundReferee.isWinningMove(2, Sign.X);

        //then
        assertTrue(result);
    }

    public void shouldNotWinNoSequence() {
        //given
        testBoard = new ArrayList<>(Arrays.asList(
                new Field(Sign.X), new Field(Sign.E), new Field(Sign.E),
                new Field(Sign.O), new Field(Sign.X), new Field(Sign.X),
                new Field(Sign.E), new Field(Sign.O), new Field(Sign.E),
                new Field(Sign.E), new Field(Sign.E), new Field(Sign.X)
        ));
        roundReferee = new RoundReferee(winningArea, new Board(xSize, 4, testBoard));

        //when
        boolean result = roundReferee.isWinningMove(5, Sign.X);

        //then
        assertFalse(result);
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
        boolean result = roundReferee.isWinningMove(4, Sign.X);

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
        boolean result = roundReferee.isWinningMove(3, Sign.X);

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
        boolean result = roundReferee.isWinningMove(9, Sign.X);

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
        boolean result = roundReferee.isWinningMove(2, Sign.X);

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
        boolean result = roundReferee.isWinningMove(2, Sign.X);

        //then
        assertFalse(result);
    }

    public void nextTest() {
        //given
        testBoard = new ArrayList<>(Arrays.asList(
                new Field(Sign.E), new Field(Sign.X), new Field(Sign.E), new Field(Sign.E),
                new Field(Sign.E), new Field(Sign.E), new Field(Sign.O), new Field(Sign.E),
                new Field(Sign.E), new Field(Sign.E), new Field(Sign.E), new Field(Sign.E),
                new Field(Sign.E), new Field(Sign.E), new Field(Sign.E), new Field(Sign.E)
        ));
        roundReferee = new RoundReferee(winningArea, new Board(4, 4, testBoard));

        //when
        boolean result = roundReferee.isWinningMove(7, Sign.O);

        //then
        assertFalse(result);
    }

}