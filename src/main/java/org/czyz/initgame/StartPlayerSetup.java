package org.czyz.initgame;

import org.czyz.ui.Interaction;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

class StartPlayerSetup implements Interaction {

    private final Scanner scanner;
    private final ResourceBundle labels;

    StartPlayerSetup(Scanner scanner) {
        this.scanner = scanner;
        this.labels = ResourceBundle.getBundle("lang");
    }

    @Override
    public String action(Consumer<String> onError, Function<String, Boolean> function) {

        String sign = scanner.nextLine();
        sign = sign.toUpperCase();
        if(function.apply(sign)){
            return sign;
        }else {
            onError.accept(labels.getString("OXError"));
            return action(onError, function); //recursion was called 127 - works
        }
    }
}
