public enum Sign {
    X("X"),O("0"),


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

}
