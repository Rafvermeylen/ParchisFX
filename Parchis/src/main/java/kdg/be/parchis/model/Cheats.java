package kdg.be.parchis.model;

public class Cheats {
    public static boolean activated = false;
    public static void clickButton(){
        activated = !activated;
    }

    public static boolean getActivated(){
        return activated;
    }
}
