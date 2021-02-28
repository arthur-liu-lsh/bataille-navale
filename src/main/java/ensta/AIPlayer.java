package ensta;
import java.io.Serializable;
import java.util.*;

import ship.*;

public class AIPlayer extends Player {
    /* **
     * Attribut
     */
    private BattleShipsAI ai;

    /* **
     * Constructeur
     */
    public AIPlayer(Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
        super(ownBoard, opponentBoard, ships);
        ai = new BattleShipsAI(ownBoard, opponentBoard);
    }

    /**
     * Makes AI put ships randomly on their board.
     * Uses BattleShipsAI.putShips()
     */
    public void putShips() {
        ai.putShips(super.getShips());
    }

    /**
     * Makes AI send a hit on AI generated coordinates.
     * Uses BattleShipsAI.sendHit()
     */
    public Hit sendHit(int[] coords) {
        return ai.sendHit(coords);
    }
}
