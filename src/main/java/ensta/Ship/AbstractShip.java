package ship;

public abstract class AbstractShip 
{

    private char label;
    private String designation;
    private int size;
    private Direction direction;

    private int strikeCount;

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

    public void addStrike() {
        strikeCount++;
    }

    public boolean isSunk() {
        if (strikeCount >= size) {
            return true;
        }
        return false;
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
