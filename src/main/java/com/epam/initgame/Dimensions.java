package com.epam.initgame;

public class Dimensions {
    private final int xSize;
    private final int ySize;

    Dimensions(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
    }

    boolean areValid() {
        return xSize >= 3 && ySize >= 3;
    }

    public int getxSize() {
        return xSize;
    }

    public int getySize() {
        return ySize;
    }
}
