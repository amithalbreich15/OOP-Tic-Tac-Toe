import java.util.Random;

/**
 * @author Amit Halbreich, 208917393
 * a genius player - type of Tic Tac Toe player.
 * This class represents a genius player in a Tic Tac Toe game.
 * The player represents a logical strategy when playing a turn, in several games.
 * a genius player should win a clever player most of the time. It is the most sophisticated player in the game, unless
 * a human defeats it ;).
 * Genius player will first try to block other opponent's win streak (for rows first) if he is starting 2nd.
 * this is a defending strategy. If genius starts the game (I check if all marks on the board are blank) he will try
 * to put the marks on the Diagonal main line and if he gets blocked by clever or what ever he will try to put his mark
 * vertically to create a streak. If none of the above happens he will put a mark in a random coordinate on the board.
 * If genius starts second and he already blocked the opponent (he will try to block clever's horizontal streak) he will
 * try to create his own diagonal or vertical streak in order to win.
 *
 */
public class GeniusPlayer implements Player {

    private int gameTactic = 1;
    private final Random random = new Random();

    /**
     * This method represents Genius Player strategy:
     * Genius player will first try to block other opponent's win streak (for rows first) if he is starting 2nd.
     * this is a defending strategy. If genius starts the game (I check if all marks on the board are blank)
     *  he will try to put the marks on the Diagonal main line and if he gets blocked by clever or what ever he
     *  will try to put his mark vertically to create a streak. If none of the above happens he will put a mark
     *  in a random coordinate on the board. If genius starts second and he already blocked the opponent (he will try to
     *  block clever's horizontal streak) he will try to create his own diagonal or vertical streak in order to win.
     * @param board represents the game board, in which we need to put the player's mark
     * @param mark  represents the given mark {X,O} to put
     */
    @Override
    public void playTurn(Board board, Mark mark)
    {
        boolean result = false;
        int randomCoord =0;
        int [] availableCoordinates = new int[board.getSize()* board.getSize()];
        int availableCoordinatesSize = 0;
        Mark opponentMark = Mark.O;
        if (mark == Mark.O) {
            opponentMark = Mark.X;
        }
        outerloop:
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                if (board.getMark(row,col) == opponentMark && gameTactic == 1){
                    if (col + 1 < board.SIZE){
                        result = board.putMark(mark,row, ++col);
                        gameTactic = 2;
                    }
                    if (!result)
                        break outerloop;
                }
                else if ( (row == col && gameTactic == 2) || isBoardEmpty(board))
                {
                    if (col +1 <board.SIZE && row +1 <board.SIZE){
                        while(true) {
                            result = board.putMark(mark,row,col);
                            col++;
                            row++;
                            if (board.getMark(row, col) == opponentMark && !result)  {
                                result = board.putMark(mark,row,col - 1);
                                gameTactic = 2;
                                break outerloop;
                            }
                            if ( result || row >= board.SIZE || col >=board.SIZE) {
                                gameTactic = 1;
                                break outerloop;
                            }
                        }
                    }
                }
            }
        }
        for (int row=0; row <board.SIZE; row++) {
            for (int col = 0; col < board.SIZE; col++) {
                if (board.getMark(row, col) == Mark.BLANK) {
                    availableCoordinates[availableCoordinatesSize++] = 10 * row + col;
                }
            }
        }
        if (availableCoordinatesSize != 0){
            randomCoord = random.nextInt(availableCoordinatesSize);
        }
        int col = availableCoordinates[randomCoord] % 10;
        int row = availableCoordinates[randomCoord] / 10;
        if (!result) {
            board.putMark(mark, row, col);
        }
    }

    /**
     * This method checks if the board is empty of marks - verifies if it is genius's first turn.
     * @param board Game board.
     * @return true if Board is empty ,false otherwise
     */
    private boolean isBoardEmpty(Board board) {
        int blankCounter = 0;
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                if (board.getMark(row,col) == Mark.BLANK) {
                    blankCounter++;
                }
            }
        }
        return blankCounter == board.getSize() * board. getSize();
    }
}
