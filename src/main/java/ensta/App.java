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
        Board board = new Board("myBoard", 10, 10);
        board.print();

        Submarine SS1 = new Submarine(Submarine.Direction.WEST);
        System.out.println(SS1.getDesignation());
    }
}
