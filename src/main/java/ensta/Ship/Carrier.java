package ship;

public class Carrier extends AbstractShip {

    public Carrier() {
        super('C', "Carrier", 5, Direction.EAST);
    }

    public Carrier(Direction newDirection) {
        super('C', "Carrier", 5, newDirection);
    }
}