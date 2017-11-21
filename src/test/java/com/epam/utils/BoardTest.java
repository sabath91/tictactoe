package com.epam.utils;

import com.epam.initgame.Dimensions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Test
public class BoardTest {

    @DataProvider(name = "boardDimensions")
    public Object[][] createBoardDimensions() {
        return new Object[][]{
                {4, 4},
                {50, 3},
                {4, 45}
        };

    }

    @Test(dataProvider = "boardDimensions")
    public void shouldCreateBoardWithVariousDimensions(int xSize, int ySize) {
        //given
        Dimensions dimensions = new Dimensions(xSize, ySize);
        //when
        Board board = new Board(dimensions);
        //then
        assertEquals(board.getSize(), xSize * ySize);
    }
}