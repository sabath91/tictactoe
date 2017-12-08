package org.czyz.initgame;

class StartPlayerValidator {

    boolean validate(String userInput){
        return userInput.equals("X") || userInput.equals("O");
    }
}
