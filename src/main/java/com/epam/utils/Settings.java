package com.epam.utils;

import com.epam.initgame.Dimensions;

public class Settings {

    private Player player1;
    private Player player2;
    private Dimensions dimensions;
    private int winingSequenceLength;

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public int getWiningSequenceLength() {
        return winingSequenceLength;
    }

    public void setWiningSequenceLength(int winingSequenceLength) {
        this.winingSequenceLength = winingSequenceLength;
    }

    public boolean isWinningSequenceLengthValid() {
        return (winingSequenceLength > Math.min(dimensions.getxSize(), dimensions.getySize())) || (winingSequenceLength < 3);
    }
}
