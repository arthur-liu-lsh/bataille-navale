package ship;

public class Destroyer extends AbstractShip {

    public Destroyer() {
        super('D', "Destroyer", 2, Direction.EAST);
    }

    public Destroyer(Direction newDirection) {
        super('D', "Destroyer", 2, newDirection);
    }
}