package org.czyz.settings;

public class BoardDimensions {
    private final Width width;
    private final Height height;

    public BoardDimensions(Width width, Height height) {
        this.width = width;
        this.height = height;
    }

    public int boardSize(){
        return width.getValue() * height.getValue();
    }

    public int getWidth() {
        return width.getValue();
    }

    public int getHeight() {
        return height.getValue();
    }
}
