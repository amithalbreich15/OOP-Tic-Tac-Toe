import java.util.Scanner;

/**
 * @author Amit Halbreich, 208917393
 * a Human player - type of player that is played by a human being.
 * This class represents a human player which competes in a Tic Tac Toe game.
 * The player represents a logical strategy when playing a turn, in several games.
 *
 */
public class HumanPlayer implements Player {

    private static final int DECIMAL_BASE = 10;
    private static final String MARK = "<mark>";
    private static final String INVALID_INPUT = "Invalid coordinates, type again: ";
    private static final String INPUT_REQUEST = "Player <mark>, type coordinates: ";

    /**
     * Constructor for human player.
     * Also can use default constructor.
     */
    public HumanPlayer() { }

    /**
     * playTurn function - a function in which the player plays his turn.
     * the player is being asked to insert coordinates, in which he wants to put his mark until they are valid and free.
     *
     * @param board represents the game board, in which we need to put the player's mark
     * @param mark  represents the given mark {X,O} to put
     */
    @Override
    public void playTurn(Board board, Mark mark)
    {
        Scanner userInput = new Scanner(System.in);
        System.out.println(INPUT_REQUEST.replaceAll(MARK, mark.toString()));
        int userCoordinatesChoice = userInput.nextInt();
        int col = userCoordinatesChoice % DECIMAL_BASE;
        int row = userCoordinatesChoice / DECIMAL_BASE;
        while (!board.putMark(mark, row, col)) {
            System.out.println(INVALID_INPUT);
            userCoordinatesChoice = userInput.nextInt();
            col = userCoordinatesChoice % DECIMAL_BASE;
            row = userCoordinatesChoice / DECIMAL_BASE;
        }
    }
}
