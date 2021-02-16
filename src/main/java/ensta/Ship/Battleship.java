package ship;

public class Battleship extends AbstractShip {

    public Battleship() {
        super('B', "Battleship", 3, Direction.EAST);
    }

    public Battleship(Direction newDirection) {
        super('B', "Battleship", 3, newDirection);
    }
}