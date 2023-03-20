/*
This code is used to make an enum for the different colors. We make the colors lowercase, as to make it a bit easier to
write and compare.
*/

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
