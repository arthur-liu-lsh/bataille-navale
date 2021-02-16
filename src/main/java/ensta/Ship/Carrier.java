package ship;

public class Carrier extends AbstractShip {

    public Carrier() {
        super('C', "Carrier", 4, Direction.EAST);
    }

    public Carrier(Direction newDirection) {
        super('C', "Carrier", 4, newDirection);
    }
}