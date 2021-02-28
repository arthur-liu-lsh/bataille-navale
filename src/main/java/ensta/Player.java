package ensta;
import java.io.Serializable;
import java.util.List;

import ship.*;

public class Player {
    /* **
     * Attributs
     */
    protected Board board;
    protected Board opponentBoard;
    protected int destroyedCount;
    protected AbstractShip[] ships;
    protected boolean lose;

    /* **
     * Constructeur
     */
    public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
        this.board = board;
        this.ships = ships.toArray(new AbstractShip[0]);
        this.opponentBoard = opponentBoard;
    }

    /* **
     * Méthodes
     */

    /**
     * Read keyboard input to get ships coordinates. Place ships on given coodrinates.
     */
    public void putShips() {
        boolean done = false;
        int i = 0;

        do {
            AbstractShip s = ships[i];
            
            boolean ok = false;
            InputHelper.ShipInput res = null;
            do {
                String msg = String.format("placer %d : %s(%d)", i + 1, s.getDesignation(), s.getSize());
                System.out.println(msg);
                res = InputHelper.readShipInput();
                try {
                    Direction newDirection;
                    switch (res.orientation) {
                        case "n":
                            newDirection = Direction.NORTH;
                            break;
                        case "s":
                            newDirection = Direction.SOUTH;
                            break;
                        case "e":
                            newDirection = Direction.EAST;
                            break;
                        case "w":
                            newDirection = Direction.WEST;
                            break;
                        default:
                            newDirection = Direction.EAST;
                    }

                    s.setDirection(newDirection);
                    ok = true;
                }
                catch (Exception e) {
                    System.out.println(e);
                }
                
            } while (!canPutShip(s, res.x, res.y));
            board.putShip(s, res.x, res.y);
            ++i;
            done = i == 5;

            board.print();
        } while (!done);
    }

    /**
     * Checks if a ship can be placed at given coordinates.
     * @param ship The ship to place on the board.
     * @param x
     * @param y
     * @return true if the ship can be placed, false otherwise.
     */
    private boolean canPutShip(AbstractShip ship, int x, int y) {
        Direction o = ship.getDirection();
        int dx = 0, dy = 0;
        if (o == Direction.EAST) {
            if (x + ship.getSize() >= board.getSize()) {
                return false;
            }
            dx = 1;
        } else if (o == Direction.SOUTH) {
            if (y + ship.getSize() >= board.getSize()) {
                return false;
            }
            dy = 1;
        } else if (o == Direction.NORTH) {
            if (y + 1 - ship.getSize() < 0) {
                return false;
            }
            dy = -1;
        } else if (o == Direction.WEST) {
            if (x + 1 - ship.getSize() < 0) {
                return false;
            }
            dx = -1;
        }

        int ix = x;
        int iy = y;

        for (int i = 0; i < ship.getSize(); ++i) {
            if (board.hasShip(ix, iy)) {
                return false;
            }
            ix += dx;
            iy += dy;
        }

        return true;
    }

    /**
     * Sends a hit on player input coordinates.
     */
    public Hit sendHit(int[] coords) {
        boolean done = false;
        Hit hit = null;

        do {
            System.out.println("où frapper?");
            InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
            hit = this.opponentBoard.sendHit(hitInput.x, hitInput.y);
            coords[0] = hitInput.x;
            coords[1] = hitInput.y;
            done = true;
        } while (!done);
        return hit;
    }


    public AbstractShip[] getShips() {
        return ships;
    }

    public void setShips(AbstractShip[] ships) {
        this.ships = ships;
    }
}
