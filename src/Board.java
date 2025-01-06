/**
 * @author Amit Halbreich, 208917393
 * a game Board.
 * This class is demonstrates a board of Tic Tac Toe game.
 * The board will display the players their marks.
 * The board will have the the option to mark X and O marks according to players choice.
 * The board only "knows" its size but not the win Streak. It knows which coordinates are legal and which aren't.
 * The content of the board changes as the game proceeds.
 *
 */
public class Board {
    /**
     * represents the board size - the board will contain size X size entries
     */
    public int SIZE = 4;

    private final Mark[][] boardMarks;

    /**
     * Constructor for Board Object with default size of 4 - a new Board and fills its spots with Blank marks initially.
     */
    public Board() {
        this.boardMarks = new Mark[SIZE][SIZE];
        // Initializes the board with BLANK marks for each coordinate
        initializeBoard();
    }

    /**
     * Constructor for Board Object with size of the user's choice - a new Board and fills its spots
     * with Blank marks initially.
     */
    public Board(int size) {
        this.SIZE = size;
        this.boardMarks = new Mark[SIZE][SIZE];
        // Initializes the board with BLANK marks for each coordinate
        initializeBoard();
    }

    /**
     * A getter method for Board size.
     * @return Board's size - represented by 'n' in the exercise's description.
     */
    public int getSize() {
        return SIZE;
    }

    /**
     * A getter method for board Marks 2-dim array.
     * @return a 2-dim arrray which hold Marks which represented the board output to be printed by the renderer.
     */
    Mark[][] getBoard() {
        return this.boardMarks;
    }

    /**
     * putMark method - puts the given mark in the given coordinates, only if its valid/available to put the mark in
     *
     * @param mark represents the given mark {X,O} to put
     * @param row  represents the given row in the coordinates
     * @param col  represents the given column in the coordinates
     * @return true if the coordinates were valid/available, false otherwise
     */
    public boolean putMark(Mark mark, int row, int col) {
        if (row < 0 || col < 0 || row >= SIZE || col >= SIZE) {
            return false;
        }
        if (this.boardMarks[row][col] != Mark.BLANK) {
            return false;
        }
        this.boardMarks[row][col] = mark;
        return true;
    }

    /**
     * getMark method - returns the Mark located in the given coordinates (row,col).
     * @param row represents the given row in the coordinates
     * @param col represents the given column in the coordinates
     * @return the mark {X,O} if the given coordinates were valid, BLANK otherwise
     */
    public Mark getMark(int row, int col) {
        if (row < 0 || col < 0 || row >= SIZE || col >= SIZE) {
            return Mark.BLANK;
        }
        return this.boardMarks[row][col];
    }

    /**
     * initializeBoard - private method which initializes the board to be full of BLANK marks
     */
    private void initializeBoard() {
        for (int row = 0; row < this.SIZE; row++) {
            for (int col = 0; col < this.SIZE; col++) {
                this.boardMarks[row][col] = Mark.BLANK;
            }
        }
    }

}
