package com.epam.initgame;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.testng.Assert.assertEquals;

@Test
public class DataInitializerTest {

    private static final int PROPER_SIZE = 3;


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

    @DataProvider(name = "sequenceLength")
    public Object[][] createSequenceLength() {
        return new Object[][]{
                {3, 3, 3},
                {7, 5, 4},
                {10, 10, 5},
                {10, 29, 10},
                {40, 50, 35}
        };
    }

    @DataProvider(name = "invalidSequenceLength")
    public Object[][] invalidSequenceLength() {
        return new Object[][]{
                {3, 3, 1},
                {7, 5, 6},
                {10, 10, 15},
                {4, 5, -4}

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
        assertEquals(dataInitializer.getSettings().getDimensions().getxSize(), xSize);
        assertEquals(dataInitializer.getSettings().getDimensions().getySize(), ySize);
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

        String expectedOutput = "Proszę podać wymiary planszy\n" +
                "x: \n" +
                "y: \n" +
                "Minimlane wymiary planszy to 3x3. Spróbuj jeszcze raz\n" +
                "y: \n";
        //then
        assertEquals(outContent.toString(), expectedOutput);
    }

    @Test(dataProvider = "xFails")
    public void shouldAskSecondTimeBecauseOfInvalidXSize(int xSize, int ySize) {
        //given
        ByteArrayInputStream in = new ByteArrayInputStream((xSize + "\n" + PROPER_SIZE + "\n" + ySize).getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        DataInitializer dataInitializer = new DataInitializer();

        //when
        dataInitializer.createBoardDimensions();

        String expectedOutput = "Proszę podać wymiary planszy\n" +
                "x: \n" +
                "Minimlane wymiary planszy to 3x3. Spróbuj jeszcze raz\n" +
                "x: \n" +
                "y: \n";
        //then
        assertEquals(outContent.toString(), expectedOutput);
    }

    @Test(dataProvider = "sequenceLength")
    public void shouldBeAbleToCreateProperLengthSequence(int xSize, int ySize, int length) {
        //given
        ByteArrayInputStream in = new ByteArrayInputStream((xSize + "\n" + ySize + "\n" + length).getBytes());
        System.setIn(in);

        DataInitializer dataInitializer = new DataInitializer();

        //when
        dataInitializer.createBoardDimensions();
        dataInitializer.setWiningSequenceLength();

        //then
        assertEquals(dataInitializer.getSettings().getWiningSequenceLength(), length);
    }

    @Test(dataProvider = "invalidSequenceLength")
    public void shouldAskSecondTimeBecauseOfInvalidLengthSequence(int xSize, int ySize, int length) {
        //given
        ByteArrayInputStream in = new ByteArrayInputStream((xSize + "\n" + ySize + "\n" + length + "\n" + PROPER_SIZE).getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        DataInitializer dataInitializer = new DataInitializer();

        //when
        dataInitializer.createBoardDimensions();
        dataInitializer.setWiningSequenceLength();

        String expectedOutput = "Proszę podać wymiary planszy\n" +
                "x: \n" +
                "y: \n" +
                "Podaj długość zwycięskiego ciągu\n" +
                "Podaj liczbę z przedziału <3, mniejszyWymiarPlanszy>! Spróbuj jeszcze raz\n" +
                "Podaj długość zwycięskiego ciągu\n";
        //then
        assertEquals(outContent.toString(), expectedOutput);

    }


}