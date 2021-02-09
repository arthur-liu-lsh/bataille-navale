package ensta;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Board board = new Board("myBoard", 13, 28);
        board.print();
    }
}
