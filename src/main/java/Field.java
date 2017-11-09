class Field {

    private Sign sign;

    Field(Sign sign) {
        this.sign = sign;
    }

    public String getSingOrNumber(int index){
        return sign.getSingOrNumber(index+1);
    }


}
