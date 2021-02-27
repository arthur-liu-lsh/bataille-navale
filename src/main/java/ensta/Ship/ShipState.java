package ship;

public class ShipState {
    private AbstractShip targetShip;
    private boolean struck = false;

    public ShipState(AbstractShip newTargetShip) {
        targetShip = newTargetShip;
    }

    public void addStrike() {
        struck = true;
        targetShip.addStrike();
    }

    public boolean isStruck() {
        return struck;
    }

    public String toString() {
        return Character.toString(targetShip.getLabel());
    }

    public boolean isSunk() {
        return targetShip.isSunk();
    }

    public AbstractShip getShip() {
        return targetShip;
    }

}