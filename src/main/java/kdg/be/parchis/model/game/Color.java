package kdg.be.parchis.model.game;

public enum Color {
    YELLOW("yellow"), RED("red"), BLUE("blue"), GREEN("green");

    private final String color;

    Color(String name) {
        this.color = name;
    }

    public String getName() {
        return color;
    }
}
