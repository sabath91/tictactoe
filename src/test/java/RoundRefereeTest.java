import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;
@Test
public class RoundRefereeTest {


    List<Field> testBoard;
    RoundReferee roundReferee = new RoundReferee();

    public void shouldWinWhenInRow(){
        //given
        testBoard = new ArrayList<>(Arrays.asList(
                new Field(Sign.X), new Field(Sign.E), new Field(Sign.X),
                new Field(Sign.O),new Field(Sign.O),new Field(Sign.X),
                new Field(Sign.X),new Field(Sign.O),new Field(Sign.O)
        ));
        //when
        boolean result = roundReferee.isWinningMove(2);

        //then
        assertTrue(result);
    }

    public void shouldNotWinWhenOnlyEmptySignsInRow(){
        //given
        testBoard = new ArrayList<>(Arrays.asList(
                new Field(Sign.E)
        ));
        //when
        //then
    }

    public void shouldNotWinWhenInterruptedByOtherSign(){
    //given
    //when
    //then
    }


    public void shouldWinWhenInColumn(){
    //given
    //when
    //then
    }

    public void shouldWinWhenOnLeftDiagonal(){
    //given
    //when
    //then
    }

    public void shouldWinWhenOnRightDiagonal(){
    //given
    //when
    //then
    }


}