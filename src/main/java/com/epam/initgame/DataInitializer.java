package com.epam.initgame;

import com.epam.utils.*;

import java.util.InputMismatchException;
import java.util.Scanner;


public class DataInitializer {

    private static final int MIN_BOARD_DIMENSION = 3;
    private Scanner scanner;
    private Settings settings;
    private Printer printer;
    private InputCollector inputCollector;
    private Validator validator;

    public void setupGame() {
        createPlayers();
        createBoardDimensions();
        setWiningSequenceLength();
    }

    public Settings getSettings() {
        return settings;
    }

    public DataInitializer() {
        scanner = new Scanner(System.in);
        settings = new Settings();
        printer = new Printer();
        inputCollector = new InputCollector();
        validator = new Validator();
    }

    private void createPlayers() {
        settings.setPlayer1(createPlayer(Sign.X));
        settings.setPlayer2(createPlayer(Sign.O));
    }

    private Player createPlayer(Sign sign) {
        System.out.println("Podaj imię gracza grającego " + sign.getSign());
        String name = scanner.nextLine();
        if (name.length() < 3) {
            System.out.println("Niepoprawne imie:(  proszę podać imię zawierające co najmniej 3 litery ");
            createPlayer(sign);
        }
        return new Player(name, sign);
    }

    //should be private but how to test it? -- without reflection
    void createBoardDimensions() {
        printer.giveMessage("Proszę podać wymiary planszy");
        int xSize = getDimension("x: ");

        int ySize = getDimension("y: ");

        settings.setDimensions(new Dimensions(xSize, ySize));
    }

    private int getDimension(String message) {
        int dim;
        boolean retry;
        do {
            dim = inputCollector.askToProvideInt(message);
            if (validator.lessThen(dim, MIN_BOARD_DIMENSION)) {
                retry = true;
                printer.giveMessage("Minimlane wymiary planszy to 3x3. Spróbuj jeszcze raz");
            } else {
                retry = false;
            }
        }
        while (retry);
        return dim;
    }

    private void setWiningSequenceLength() {


        do {
            System.out.println("Podaj długość zwycięskiego ciągu");
            try {
                settings.setWiningSequenceLength(scanner.nextInt());
                if (settings.isWinningSequenceLengthValid()) {
                    System.out.println("Podaj liczbę z przedziału <3, mniejszyWymiarPlanszy>! Spróbuj jeszcze raz");
                    setWiningSequenceLength();
                }
            } catch (InputMismatchException e) {
                System.out.println("Zły znak. Proszę o jakąś wartość liczbową");
                scanner.nextLine();
            }
        } while (settings.isWinningSequenceLengthValid());

    }

}
