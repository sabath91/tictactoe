package org.czyz.initgame;

import org.czyz.ui.Interaction;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

class BoardDimensionCreator implements Interaction {

    private final Scanner scanner;
    private final ResourceBundle labels;

    public BoardDimensionCreator(Scanner scanner) {
        this.scanner = scanner;
        this.labels = ResourceBundle.getBundle("lang");
    }

    @Override
    public String action(Consumer<String> onError, Function<String, Boolean> function) {
        String dim = scanner.nextLine();

        if(function.apply(dim)){
            return dim;
        }else {
            onError.accept(labels.getString("boardSizeError"));
            return action(onError, function); //recursion was called 127 - works
        }
    }
}
