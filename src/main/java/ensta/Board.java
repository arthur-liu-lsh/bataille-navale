package ensta;

import ship.*;

public class Board implements IBoard{
    private String name;
    private ShipState[][] ships;
    private Boolean[][] hits;

    Board(String newName, int size) {
        name = newName;
        ships = new ShipState[size][size];
        hits = new Boolean[size][size];

        for(int i = 0; i<size; i++) {
            for(int j = 0; j<size; j++) {
                ships[i][j] = null;
                hits[i][j] = null;
            }
        }
    }

    Board(String newName) {
        name = newName;
        ships = new ShipState[10][10];
        hits = new Boolean[10][10];
        
        for(int i = 0; i<10; i++) {
            for(int j = 0; j<10; j++) {
                ships[i][j] = null;
                hits[i][j] = null;
            }
        }
    }

    String getName() {
        return name;
    }

    ShipState[][] getShips() {
        return ships;
    }

    Boolean[][] getHits() {
        return hits;
    }

    void setName(String newName) {
        name = newName;
    }

    void setShips(ShipState[][] newShips) {
        ships = newShips;
    }

    void setHits(Boolean[][] newHits) {
        hits = newHits;
    }

    private void repeatSpace(int iterations) {
        String spaces = "";
        for (int i = 0; i<iterations; i++) {
            spaces += " ";
        }
        System.out.print(spaces);
    }

    void print() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int height = ships.length;
        int width = ships[0].length;
    
        System.out.print("Navires :");
        repeatSpace(width*2-3);
        System.out.print("Frappes :\n   ");
    
        for (int j = 0; j < width; j++) {
            System.out.print(alphabet.charAt(j%26) + " ");
        }
    
        repeatSpace(6);
    
        for (int j = 0; j < width; j++) {
            System.out.print(alphabet.charAt(j%26) + " ");
        }
    
        System.out.println();
    
    
        for (int i = 0; i < height; i++) {
            System.out.print(Integer.toString(i+1));
            if(i+1 < 10) {
                System.out.print(" ");
            }
            System.out.print(" ");
    
            for (int j = 0; j < width; j++) {
                if (ships[i][j] == null) {
                    System.out.print(". ");
                }
                else {
                    System.out.print(ships[i][j].toString() + " ");
                }
            }
            
            System.out.print("   ");
            System.out.print(Integer.toString(i+1));
            System.out.print(" ");
            if(i+1 < 10) {
                System.out.print(" ");
            }
            for (int j = 0; j < width; j++) {
                if (hits[i][j] == null) {
                    System.out.print(". ");
                }
                else if (hits[i][j]) {
                    System.out.print(ColorUtil.colorize("X ", ColorUtil.Color.RED));
                }
                else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

    public int getSize() {
        return ships.length;
    }

    public void putShip(AbstractShip ship, int x, int y) {
        try {
            Direction direction = ship.getDirection();

            if (x < 0 || y < 0) {
                throw new Exception("Invalid coordinates (exceeds ships boundaries, negative): " + ship.getDesignation());
            }

            int shipsSize = getSize();

            if (x >= shipsSize || y >= shipsSize) {
                throw new Exception("Invalid coordinates (exceeds ships boundaries, > shipsSize): " + ship.getDesignation());
            }

            int shipSize = ship.getSize();

            int vertical;
            int horizontal;

            switch (direction) {
                case EAST:
                    vertical = 0;
                    horizontal = 1;
                    break;
                case WEST:
                    vertical = 0;
                    horizontal = -1;
                    break;
                case NORTH:
                    vertical = -1;
                    horizontal = 0;
                    break;
                case SOUTH:
                    vertical = 1;
                    horizontal = 0;
                    break;
                default:
                    vertical = 0;
                    horizontal = 0;
            }

            if (x + horizontal*shipSize < 0 || x + horizontal*shipSize >= shipsSize || y + vertical*shipSize < 0 || y + vertical*shipSize >= shipsSize) {
                throw new Exception("Invalid coordinates (exceeds ships boundaries, ship partially outside ships): " + ship.getDesignation());
            }

            for (int i = 0; i < shipSize; i++) {
                if (ships[y + i*vertical][x + i*horizontal] != null) {
                    throw new Exception("Invalid coordinates (ships overlapping): " + ship.getDesignation());
                } 
                ships[y + i*vertical][x + i*horizontal] = new ShipState(ship);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean hasShip(int x, int y) {
        if (ships[x][y] != null) {
            return true;
        }
        return false;
    }

    public void setHit(boolean hit, int x, int y) {
        hits[x][y] = hit;
    }

    public Boolean getHit(int x, int y) {
        return hits[x][y];
    }

}