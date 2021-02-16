package ship;

public class Submarine extends AbstractShip {

    public Submarine() {
        super('S', "Submarine", 3, Direction.EAST);
    }

    public Submarine(Direction newDirection) {
        super('S', "Submarine", 3, newDirection);
    }
}