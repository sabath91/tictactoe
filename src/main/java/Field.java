class Field {

    private Sign sign;

    Field(Sign sign) {
        this.sign = sign;
    }

    public String getSingOrNumber(int index){
        return sign.getSingOrNumber(index+1);
    }

    boolean isEmpty(){
        return sign.equals(Sign.E);
    }

    void set(Sign sign) {
        this.sign = sign;
    }

    String getSign(){
      return sign.getSign();
    }
}
