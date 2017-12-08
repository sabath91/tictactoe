package org.czyz.initgame;

import org.czyz.ui.Interaction;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

class WinningSequenceCreator implements Interaction {

    private final Scanner scanner;
    private final ResourceBundle labels;

    WinningSequenceCreator(Scanner scanner) {
        this.scanner = scanner;
        this.labels = ResourceBundle.getBundle("lang");
    }

    @Override
    public String action(Consumer<String> onError, Function<String, Boolean> function) {
        String length = scanner.nextLine();

        if (function.apply(length)) {
            return length;
        } else {
            onError.accept(labels.getString("winningSequenceLengthError"));
            return action(onError, function); //recursion was called 127 - works
        }
    }
}
