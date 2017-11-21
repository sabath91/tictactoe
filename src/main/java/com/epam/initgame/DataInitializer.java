package com.epam.initgame;

import com.epam.initgame.Dimensions;
import com.epam.utils.Player;
import com.epam.utils.Settings;
import com.epam.utils.Sign;

import java.util.InputMismatchException;
import java.util.Scanner;


public class DataInitializer {

    private Scanner scanner;
    private Settings settings;

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

    private void createBoardDimensions() {

        do {
            try {
                System.out.println("Proszę podać wymiary planszy");
                System.out.print("x: ");
                int xSize = scanner.nextInt();
                System.out.print("y: ");
                int ySize = scanner.nextInt();
                settings.setDimensions(new Dimensions(xSize, ySize));
                if (!settings.getDimensions().areValid()) {
                    System.out.println("Minimlane wymiary planszy to 3x3. Spróbuj jeszcze raz:");
                    createBoardDimensions();
                }
            } catch (InputMismatchException e) {
                System.out.println("Zły znak. Proszę o jakąś wartość liczbową");
                scanner.nextLine();
            }
        } while (settings.getDimensions() == null);

    }

    private void setWiningSequenceLength() {


        do{
            System.out.println("Podaj długość zwycięskiego ciągu");
            try {
                settings.setWiningSequenceLength(scanner.nextInt());
                if (settings.isWinningSequenceLengthValid()) {
                    System.out.println("Podaj liczbę z przedziału <3, mniejszyWymiarPlanszy>! Spróbuj jeszcze raz");
                    setWiningSequenceLength();
                }
            }catch (InputMismatchException e){
                System.out.println("Zły znak. Proszę o jakąś wartość liczbową");
                scanner.nextLine();
            }
        }while (settings.isWinningSequenceLengthValid());

    }

}
