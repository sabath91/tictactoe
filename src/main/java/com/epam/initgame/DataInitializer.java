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
        int xSize = askUserForBoardDimension("x: ");

        int ySize = askUserForBoardDimension("y: ");

        settings.setDimensions(new Dimensions(xSize, ySize));
    }


    private int askUserForBoardDimension(String message) {
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

    void setWiningSequenceLength(){
        settings.setWiningSequenceLength(askUserForWinningSequenceLength("Podaj długość zwycięskiego ciągu"));
    }

    private int askUserForWinningSequenceLength(String message){
        int length ;
        boolean retry;
        do {
            length = inputCollector.askToProvideInt(message);
            if(validator.lessThen(length,MIN_BOARD_DIMENSION) || validator.moreThen(length, smallerBoardDimension())){
                retry = true;
                printer.giveMessage("Podaj liczbę z przedziału <3, mniejszyWymiarPlanszy>! Spróbuj jeszcze raz");
            }else {
                retry = false;
            }
        }while (retry);
        return length;
    }

    private int smallerBoardDimension(){
        return Math.min(settings.getDimensions().getxSize(), settings.getDimensions().getySize());
    }


}
