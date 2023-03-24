package kdg.be.parchis.model.game;

public enum Color {
    /**
     * Enumiration that contains the four possible colors. Also returns the name of the color in lowercase.
     * Authors: Rui Daniel Gomes Vieira & Raf Vermeylen
     */
    YELLOW("yellow"), RED("red"), BLUE("blue"), GREEN("green");

    private final String color;

    Color(String name) {
        this.color = name;
    }

    public String getName() {
        return color;
    }
}
