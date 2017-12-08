package org.czyz.settings;

public class Width {
    private final int value;

    public Width(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
