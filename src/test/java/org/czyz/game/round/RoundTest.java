package org.czyz.game.round;

import org.czyz.ui.ConsolePrinter;
import org.czyz.settings.Sign;
import org.czyz.settings.BoardDimensions;
import org.czyz.settings.Player;
import org.czyz.game.Score;
import org.czyz.settings.Settings;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.util.Arrays;

import static org.testng.Assert.*;


@Test
public class RoundTest {

    @DataProvider(name = "moveSequencesForDraw")
    public Object[][] moveSequencesForDraw() {
        return new Object[][]{
                {new Triplet(3, 3, 3), new int[]{1, 2, 3, 5, 4, 7, 6, 9, 8}},
                {new Triplet(4, 3, 3), new int[]{1, 3, 2, 4, 6, 5, 7, 9, 8, 10, 12, 11}},
                {new Triplet(3, 5, 3), new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 11, 14, 13, 15}}
        };
    }

    @DataProvider(name = "moveSequencesForWin")
    public Object[][] moveSequencesForWin() {
        return new Object[][]{
                {new Triplet(3, 3, 3), new int[]{1, 2, 3, 4, 5, 6, 7, 8}},
                {new Triplet(4, 3, 3), new int[]{1, 4, 2, 5, 3}},
                {new Triplet(6, 5, 5), new int[]{1, 6, 2, 7, 3, 8, 4, 9, 5}},
        };
    }

    @Test(dataProvider = "moveSequencesForDraw")
    public void shouldPlayToDraw(Triplet triplet, int[] movesSequence) {
        setUpSystemIn(movesSequence);
        //given
        Settings settings = createSettings(triplet);
        Round round = new Round(settings, new ConsolePrinter());

        //when
        Score score = round.play();

        //then
        assertTrue(score.isDraw(), "should end with draw");
    }

    @Test(dataProvider = "moveSequencesForWin")
    public void shouldPlayToWin(Triplet triplet, int[] movesSequence) {
        setUpSystemIn(movesSequence);
        //given
        Settings settings = createSettings(triplet);
        Round round = new Round(settings, new ConsolePrinter());

        //when
        Score score = round.play();

        //then
        assertTrue(!score.isDraw(), "should end with win");
    }


    private Settings createSettings(Triplet triplet) {
        Player player1 = new Player("player1", Sign.X);
        Player player2 = new Player("player2", Sign.O);
        return new Settings(
                new BoardDimensions(triplet.getWidth(), triplet.getHeight()),
                player1, player2, player1, triplet.getLength());
    }

    private void setUpSystemIn(int[] movesSequence) {
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(movesSequence).forEach(
                move -> stringBuilder.append(move + "\n")
        );
        ByteArrayInputStream in = new ByteArrayInputStream(stringBuilder.toString().getBytes());
        System.setIn(in);
    }

}