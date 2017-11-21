package com.epam.initgame;

import com.epam.utils.Settings;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;

import static org.testng.Assert.*;

@Test
public class DataInitializerTest {

    @DataProvider(name = "boardDimensions")
    public Object[][] createBoardDimensions() {
        return new Object[][]{
                {4, 4},
                {50, 3},
                {4, 45}
        };

    }

    @Test(dataProvider = "boardDimensions")
    public void shouldBeAbleToCreateProperDimensions(int xSize, int ySize) {
        //given
        ByteArrayInputStream in = new ByteArrayInputStream((xSize + "\n" + ySize).getBytes());
        System.setIn(in);
        DataInitializer dataInitializer = new DataInitializer();

        //when
        dataInitializer.createBoardDimensions();

        //then
        assertEquals(dataInitializer.getSettings().getDimensions().getxSize(),xSize );
        assertEquals(dataInitializer.getSettings().getDimensions().getySize(),ySize );
    }

}