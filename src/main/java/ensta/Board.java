package ensta;

import ship.*;

public class Board implements IBoard{
    private String name;
    private char[][] map;
    private boolean[][] hits;

    Board(String newName, int size) {
        name = newName;
        map = new char[size][size];
        hits = new boolean[size][size];

        for(int i = 0; i<size; i++) {
            for(int j = 0; j<size; j++) {
                map[i][j] = '0';
                hits[i][j] = false;
            }
        }
    }

    Board(String newName) {
        name = newName;
        map = new char[10][10];
        hits = new boolean[10][10];
        
        for(int i = 0; i<10; i++) {
            for(int j = 0; j<10; j++) {
                map[i][j] = '0';
                hits[i][j] = false;
            }
        }
    }

    String getName() {
        return name;
    }

    char[][] getMap() {
        return map;
    }

    boolean[][] getHits() {
        return hits;
    }

    void setName(String newName) {
        name = newName;
    }

    void setMap(char[][] newMap) {
        map = newMap;
    }

    void setHits(boolean[][] newHits) {
        hits = newHits;
    }

    private String repeatSpace(int iterations) {
        String spaces = "";
        for (int i = 0; i<iterations; i++) {
            spaces += " ";
        }
        return spaces;
    }

    void print() {
        String toPrint = "";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int height = map.length;
        int width = map[0].length;

        toPrint += "Navires :";
        toPrint += repeatSpace(width*2-3);
        toPrint += "Frappes :\n   ";

        for (int j = 0; j < width; j++) {
            toPrint += alphabet.charAt(j%26) + " ";
        }

        toPrint += repeatSpace(6);

        for (int j = 0; j < width; j++) {
            toPrint += alphabet.charAt(j%26) + " ";
        }

        toPrint += "\n";


        for (int i = 0; i < height; i++) {
            toPrint += Integer.toString(i+1);
            if(i+1 < 10) {
                toPrint += " ";
            }
            toPrint += " ";

            for (int j = 0; j < width; j++) {
                char character = (map[i][j] == '0') ? '.' : map[i][j];
                toPrint += character + " ";
            }
            
            toPrint += "   ";
            toPrint += Integer.toString(i+1);
            toPrint += " ";
            if(i+1 < 10) {
                toPrint += " ";
            }
            for (int j = 0; j < width; j++) {
                char character = !hits[i][j] ? '.' : 'X';
                toPrint += character + " ";
            }
            toPrint += "\n";
        }
    System.out.print(toPrint);
    }

    public int getSize() {
        return map.length;
    }

    public void putShip(AbstractShip ship, int x, int y) {
        try {
            Direction direction = ship.getDirection();

            if (x < 0 || y < 0) {
                throw new Exception("Invalid coordinates (exceeds map boundaries, negative): " + ship.getDesignation());
            }

            int mapSize = getSize();

            if (x >= mapSize || y >= mapSize) {
                throw new Exception("Invalid coordinates (exceeds map boundaries, > mapSize): " + ship.getDesignation());
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

            if (x + horizontal*shipSize < 0 || x + horizontal*shipSize >= mapSize || y + vertical*shipSize < 0 || y + vertical*shipSize >= mapSize) {
                throw new Exception("Invalid coordinates (exceeds map boundaries, ship partially outside map): " + ship.getDesignation());
            }

            for (int i = 0; i < shipSize; i++) {
                if (map[y + i*vertical][x + i*horizontal] != '0') {
                    throw new Exception("Invalid coordinates (ships overlapping): " + ship.getDesignation());
                } 
                map[y + i*vertical][x + i*horizontal] = ship.getLabel();
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean hasShip(int x, int y) {
        if (map[x][y] != '0') {
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