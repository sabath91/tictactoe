package org.czyz.settings;

public class Player {
    private final String name;
    private final Sign sing;

    public Player(String name, Sign sing) {
        this.name = name;
        this.sing = sing;
    }

    public Sign getSign() {
        return sing;
    }

    @Override
    public String toString() {
        return name;
    }
}
