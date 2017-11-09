class Player {

    private  final String name;
    private final Sign sign;


    Player(String name, Sign sign) {
        this.name = name;
        this.sign =sign;
    }

    public Sign getSign() {
        return sign;
    }

    public String getName() {
        return name;
    }
}
