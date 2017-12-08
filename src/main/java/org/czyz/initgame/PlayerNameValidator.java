package org.czyz.initgame;

class PlayerNameValidator {

    boolean validate(String name){
        return name.length() > 2 && name.matches("^[A-Za-z][A-Za-z0-9]*$");
    }
}
