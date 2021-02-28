package ensta;
import java.io.*;
import java.util.*;

import ship.*;

public class Game {

    /* ***
     * Constante
     */
    public static final File SAVE_FILE = new File("savegame.dat");

    /* ***
     * Attributs
     */
    private Player player1;
    private Player player2;
    private Scanner sin;

    /* ***
     * Constructeurs
     */
    public Game() {}

    /**
     * Initializes the game.
     * Let Player 1 and Player 2 place their ships.
     * Must be called before run()
     */
    public Game init() {
        if (!loadSave()) {
            // init attributes
            sin = new Scanner(System.in);
            System.out.println("entre ton nom:");
            String playerName = sin.nextLine();
            System.out.println("Bonjour capitaine " + playerName + " !");

            Board b1, b2;
            b1 = new Board(playerName, 10);
            b2 = new Board("Computer", 10);

            List<AbstractShip> ships1 = createDefaultShips();
            List<AbstractShip> ships2 = createDefaultShips();

            player1 = new Player(b1, b2, ships1);
            player2 = new AIPlayer(b2, b1, ships2);

            b1.print();
            // place player ships
            player1.putShips();
            player2.putShips();
        }
        return this;
    }

    /* ***
     * Méthodes
     */
    /**
     * Run the game. The game stops when one player's ships have all been sunk.
     * Must only be called after init()
     */
    public void run() {
        int[] coords = new int[2];
        Board b1 = player1.board;
        Hit hit;

        // main loop
        b1.print();
        boolean done;
        do {
            hit = player1.sendHit(coords);
            boolean strike = hit != Hit.MISS;
            player1.board.setHit(strike, coords[0], coords[1]);
            
            done = updateScore();

            b1.print();
            System.out.println(makeHitMessage(false /* outgoing hit */, coords, hit));

            save();

            if (!done && !strike) {
                do {
                    hit = player2.sendHit(coords);

                    strike = hit != Hit.MISS;
                    if (strike) {
                        b1.print();
                    }
                    System.out.println(makeHitMessage(true /* incoming hit */, coords, hit));
                    done = updateScore();

                    if (!done) {
                        save();
                    }
                } while(strike && !done);
            }

        } while (!done);

        SAVE_FILE.delete();
        System.out.println(String.format("Joueur %d gagne", player1.lose ? 2 : 1));
        sin.close();
    }


    private void save() {
        // try {
        //     // TODO bonus 2 : uncomment
        //     //  if (!SAVE_FILE.exists()) {
        //     //      SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
        //     //  }

        //     // TODO bonus 2 : serialize players

        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
    }

    private boolean loadSave() {
        // if (SAVE_FILE.exists()) {
        //     try {
        //         // TODO bonus 2 : deserialize players

        //         return true;
        //     } catch (IOException | ClassNotFoundException e) {
        //         e.printStackTrace();
        //     }
        // }
        return false;
    }

    /** Updates the number of destroyed ships and checks if either player won the game.
     * @return true if a player won, false otherwise
     */
    private boolean updateScore() {
        for (Player player : new Player[]{player1, player2}) {
            int destroyed = 0;
            for (AbstractShip ship : player.getShips()) {
                if (ship.isSunk()) {
                    destroyed++;
                }
            }

            player.destroyedCount = destroyed;
            player.lose = destroyed == player.getShips().length;
            if (player.lose) {
                return true;
            }
        }
        return false;
    }

    /** 
     * Writes the hit coordinates, tells the player if a ship has been struck or sunk.
     * @param incoming true if the enemy fired, false otherwise
     * @param coords Hit coordinates
     * @param hit Hit data.
     */
    private String makeHitMessage(boolean incoming, int[] coords, Hit hit) {
        String msg;
        ColorUtil.Color color = ColorUtil.Color.RESET;
        switch (hit) {
            case MISS:
                msg = hit.toString();
                break;
            case STRIKE:
                msg = hit.toString();
                color = ColorUtil.Color.RED;
                break;
            default:
                msg = hit.toString() + " coulé(e)";
                color = ColorUtil.Color.RED;
        }
        msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>",
                ((char) ('A' + coords[0])),
                (coords[1] + 1), msg);
        return ColorUtil.colorize(msg, color);
    }

    /**
     * Creates a list of AbstractShip containing a carrier, a battleship, two submarines, and a destroyer. 
     */
    private static List<AbstractShip> createDefaultShips() {
        return Arrays.asList(new AbstractShip[]{new Destroyer(), new Submarine(), new Submarine(), new Battleship(), new Carrier()});
    }

    public static void main(String args[]) {
        new Game().init().run();
    }
}
