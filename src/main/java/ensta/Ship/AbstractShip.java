package ship;

public abstract class AbstractShip 
{
    public enum Direction {
        EAST,
        SOUTH,
        WEST,
        NORTH,
    }

    private char label;
    private String designation;
    private int size;
    private Direction direction;

    public char getLabel() {
        return label;
    }

    public String getDesignation() {
        return designation;
    }

    public int getSize() {
        return size;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction newDirection) {
        direction = newDirection;
    }

    AbstractShip() {
        label = 'S';
        designation = "Submarine";
        size = 1;
        direction = Direction.EAST;
    }

    AbstractShip(char newLabel, String newDesignation, int newSize, Direction newDirection) {
        label = newLabel;
        designation = newDesignation;
        size = newSize;
        direction = newDirection;
    }
}
