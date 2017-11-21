package com.epam.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputCollector {
    private Scanner scanner;
    private Printer printer;

    public InputCollector() {
        scanner = new Scanner(System.in);
        printer = new Printer();
    }

    public int askToProvideInt(String message) {
        printer.giveMessage(message);
        boolean properInt = false;
        int ourInt = 0;
        do {
            try {
                ourInt = scanner.nextInt();
                properInt=true;
            } catch (InputMismatchException e) {
                printer.giveMessage("Zły znak. Proszę o jakąś wartość liczbową");
                scanner.nextLine();
            }
        }while (!properInt);
        return ourInt;
    }
}