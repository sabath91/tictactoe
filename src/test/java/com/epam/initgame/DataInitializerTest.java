package com.epam.initgame;

import com.epam.utils.Settings;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.testng.Assert.*;

@Test
public class DataInitializerTest {

    private static final int PROPER_SIZE = 5;


    @DataProvider(name = "boardDimensions")
    public Object[][] createBoardDimensions() {
        return new Object[][]{
                {44, 44},
                {3, 3},
                {50, 3},
                {4, 45}
        };
    }


    @DataProvider(name = "yFails")
    public Object[][] createBoardDimensionsYFails() {
        return new Object[][]{
                {4, -4},
                {50, 2},
                {4, 0}
        };
    }

    @DataProvider(name = "xFails")
    public Object[][] createBoardDimensionsXFails() {
        return new Object[][]{
                {-4, 4},
                {2, 22},
                {0, 3}
        };
    }


    @Test(dataProvider = "boardDimensions")
    public void shouldBeAbleToCreateProperDimensions(int xSize, int ySize) {
        //given
        ByteArrayInputStream in = new ByteArrayInputStream((xSize + "\n" + ySize).getBytes());
        System.setIn(in);

        //when
        DataInitializer dataInitializer = new DataInitializer();
        dataInitializer.createBoardDimensions();

        //then
        assertEquals(dataInitializer.getSettings().getDimensions().getxSize(),xSize );
        assertEquals(dataInitializer.getSettings().getDimensions().getySize(),ySize );
    }

    @Test(dataProvider = "yFails")
    public void shouldAskSecondTimeBecauseOfInvalidYSize(int xSize, int ySize) {
        //given
        ByteArrayInputStream in = new ByteArrayInputStream((xSize + "\n" + ySize + "\n" + PROPER_SIZE).getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        DataInitializer dataInitializer = new DataInitializer();

        //when
        dataInitializer.createBoardDimensions();

        String expectedOutput  = "Proszę podać wymiary planszy\n" +
                                    "x: \n" +
                                    "y: \n" +
                                    "Minimlane wymiary planszy to 3x3. Spróbuj jeszcze raz\n" +
                                    "y: \n";
        //then
        assertEquals( outContent.toString(),expectedOutput);
    }

    @Test(dataProvider = "xFails")
    public void shouldAskSecondTimeBecauseOfInvalidXSize(int xSize, int ySize) {
        //given
        ByteArrayInputStream in = new ByteArrayInputStream((xSize + "\n" + PROPER_SIZE + "\n"+ ySize).getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        DataInitializer dataInitializer = new DataInitializer();

        //when
        dataInitializer.createBoardDimensions();

        String expectedOutput  = "Proszę podać wymiary planszy\n" +
                                    "x: \n" +
                                    "Minimlane wymiary planszy to 3x3. Spróbuj jeszcze raz\n" +
                                    "x: \n" +
                                    "y: \n";
        //then
        assertEquals( outContent.toString(),expectedOutput);
    }


}