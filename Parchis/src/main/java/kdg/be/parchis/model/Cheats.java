package kdg.be.parchis.model;

public class Cheats {
    private boolean activated;
    public Cheats(){
        activated = false;
    }
    public void clickButton(){
        activated = !activated;
    }

    public boolean getActivated(){
        return activated;
    }
}
