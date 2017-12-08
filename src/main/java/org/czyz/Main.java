package org.czyz;

import org.czyz.game.Game;
import org.czyz.ui.ConsolePrinter;
import org.czyz.ui.ErrorPrinter;
import org.czyz.ui.Printer;
import org.czyz.settings.Settings;
import org.czyz.initgame.GameInitializer;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

class Main {
    private static Printer printer;

    public static void main(String[] args) {
        setupPrinter(args);
        setupLanguage();
        GameInitializer gameInitializer = new GameInitializer(printer);
        Settings settings = gameInitializer.setupGame();
        new Game(settings, printer).play();

    }

    //refactor to strategy pattern
    private static void setupLanguage() {
        printer.print("Wybierz język| choose language: \npolski: pl\nenglish: en");
        Scanner scanner = new Scanner(System.in);
        String language = scanner.nextLine();
        language = language.toUpperCase();

        switch (language) {
            case "EN":
                Locale.setDefault(new Locale("en", "US"));
                break;
            case "PL":
                Locale.setDefault(new Locale("pl", "PL"));
                break;
            default:
                printer.print("Nieprawidłowy wybór. Ustawiono domyślny język - POLSKI\n");
                Locale.setDefault(new Locale("pl", "PL"));
                break;
        }
        ResourceBundle.getBundle("lang");
    }

    //refactor to strategy pattern
    private static void setupPrinter(String[] args) {
        if (args.length > 0) {
            switch (args[0].toUpperCase()) {
                case "E":
                    printer = new ErrorPrinter();
                    break;
                default:
                    printer = new ConsolePrinter();
            }
        } else {
            printer = new ConsolePrinter();
        }
    }
}
