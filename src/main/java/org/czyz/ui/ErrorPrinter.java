package org.czyz.ui;

import org.czyz.ui.Printer;

import java.util.Formatter;

public class ErrorPrinter implements Printer {
    @Override
    public void print(String message) {
        System.err.println(message);
    }

    @Override
    public void print(Formatter message) {
        System.err.println(message);
    }
}
