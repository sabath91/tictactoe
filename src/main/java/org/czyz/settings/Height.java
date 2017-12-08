package org.czyz.settings;

public class Height {
    private final int value;

    public Height(int value) {
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

