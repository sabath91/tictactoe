package com.epam;

import com.epam.game.Game;
import com.epam.initgame.DataInitializer;

class App {

    public static void main(String[] args) {

        System.out.println("Tic-Tac-Toe v1.0");

        DataInitializer dataInitializer = new DataInitializer();
        dataInitializer.setupGame();

        Game game = new Game(dataInitializer.getSettings());
        game.play();
    }

}
