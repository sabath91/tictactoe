package com.epam.utils;

public class Player {

    private  final String name;
    private final Sign sign;


    public Player(String name, Sign sign) {
        this.name = name;
        this.sign =sign;
    }

    public Sign getSign() {
        return sign;
    }

    public String getName() {
        return name;
    }
}
