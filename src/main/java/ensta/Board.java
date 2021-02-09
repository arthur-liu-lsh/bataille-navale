package ensta;
public class Board {
    private String name;
    private char[][] map;
    private boolean[][] hits;

    Board(String newName, int height, int width) {
        name = newName;
        map = new char[height][width];
        hits = new boolean[height][width];

        for(int i = 0; i<height; i++) {
            for(int j = 0; j<width; j++) {
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
                toPrint += "." + " ";
            }
            
            toPrint += "   ";
            toPrint += Integer.toString(i+1);
            toPrint += " ";
            if(i+1 < 10) {
                toPrint += " ";
            }
            for (int j = 0; j < width; j++) {
                toPrint += "." + " ";
            }
            toPrint += "\n";
        }
    System.out.print(toPrint);
    }
}