package ensta;

import ship.*;

import java.lang.reflect.Array;
import java.util.*;

public class TestGame {

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void main( String[] args )
    {
        Board board = new Board("myBoard", 10);
        int size = board.getSize();
        
        board.print();
        
        AbstractShip[] ships = new AbstractShip[5];
        
        ships[0] = new Carrier();
        ships[1] = new Battleship();
        ships[2] = new Submarine();
        ships[3] = new Submarine();
        ships[4] = new Destroyer();

        BattleShipsAI ai = new BattleShipsAI(board, board);
        
        int destroyedShips = 0;

        ai.putShips(ships);

        board.print();

        int[] coords = new int[2];
        Hit hit;

        while (destroyedShips < Array.getLength(ships)) {
            hit = ai.sendHit(coords);
                if (hit.toInt() < 0) {
                    System.out.println(hit.toString());
                }
                if (hit.toInt() > 0) {
                    destroyedShips++;
                }
            board.print();
        } 


    }
}