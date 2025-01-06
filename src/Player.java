/**
 * @author Amit Halbreich, 208917393
 * a Player interface.
 * an interface that represents a general player - all players must have these interface methods.
 * the players that implements this interface, have to implement their own playTurn function.
 */
public interface Player {
    /**
     * playTurn function - a function in which a player decides where to put his mark.
     * each class that implements Player interface should implement this function
     * each Player type will determine and implement his own strategy
     *
     * @param board represents the game board, in which we need to put the player's mark
     * @param mark  represents the given mark {X,O} to put
     */
    void playTurn(Board board, Mark mark);

}
