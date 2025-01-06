/**
 * @author Amit Halbreich, 208917393
 * Clever player - type of a Tic Tac Toe game player.
 * This class represents a clever player competes in a Tic Tac Toe game.
 * The player represents a logical strategy when playing a turn, in several games.
 * a clever player should win a Whatever player most of the time.
 *
 */
public class CleverPlayer implements Player {
    /**
     * playTurn function - a function in which a player decides where to put his mark o the board.
     * the strategy is to mark the first available spot in the board according to rows (in order of a nested 2 loops
     * loop) that first covers rows.
     * @param board represents the game board, in which we need to put the player's mark
     * @param mark  represents the given mark {X,O} to put
     */
    @Override
    public void playTurn(Board board, Mark mark)
    {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                if (board.getMark(row, col) == Mark.BLANK) {
                    board.putMark(mark, row, col);
                    return;
                }
            }
        }
    }
}
