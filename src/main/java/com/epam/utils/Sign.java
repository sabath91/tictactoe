package com.epam.utils;

public enum Sign {
    X("X"),O("O"),


    E("-") {
        public String getSingOrNumber(int index) {
            return String.valueOf(index);
        }
    };

    private String enumMember;

    Sign(String enumMember){
        this.enumMember = enumMember;
    }

    public String getSingOrNumber(int index){
        return enumMember;
    }
    public String getSign(){
        return this.enumMember;
    }
}
