package ensta;

import ship.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Board board = new Board("myBoard", 10);
        board.print();

        Submarine SS1 = new Submarine(Direction.EAST);
        Carrier CV1 = new Carrier(Direction.SOUTH);
        Destroyer DD1 = new Destroyer(Direction.EAST);
        Battleship BB1 = new Battleship(Direction.NORTH);
        System.out.println(SS1.getDesignation());
        System.out.println(DD1.getLabel());
        System.out.println(BB1.getSize());
        System.out.println(CV1.getDirection());

        board.putShip(BB1,1,5);
        board.putShip(SS1,0,0);
        board.putShip(DD1,3,3);
        board.putShip(CV1,9,0);
        
        for(int i = 0; i < 10; i++) {
            board.sendHit(0, i);
        }
        
        for(int i = 0; i < 10; i++) {
            board.sendHit(i, i);
        }

        
        board.print();

        System.out.println("Is SS1 sunk ? " + SS1.isSunk());
        System.out.println("Is CV1 sunk ? " + CV1.isSunk());
    }
}
