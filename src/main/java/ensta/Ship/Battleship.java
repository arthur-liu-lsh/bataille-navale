package ship;

public class Battleship extends AbstractShip {

    public Battleship() {
        super('B', "Battleship", 4, Direction.EAST);
    }

    public Battleship(Direction newDirection) {
        super('B', "Battleship", 4, newDirection);
    }
}