/**
 * @author Amit Halbreich, 208917393
 * a Game that witholds a Tic Tac Toe Instance
 * This class is responsible for Game instance - runs a single game and holds information about the game.
 * This class is responsible for all game rules, decides the win streak and declares the winner of a single game.
 * This class is an infrastructure to run the Tic Tac Toe tournaments.
 *
 */
public class Game {

    private final Player playerX;
    private final Player playerO;
    private final Renderer RENDERER;
    private int SIZE;
    private int winStreak = 3;
    private boolean gameInProgress = false;
    private int marksCounter = 0;

    private static final int NUM_OF_PLAYERS = 2;
    private Mark winnerMark = Mark.BLANK;

    /**
     * Constructor for game - constructs a new Game with the given players and renderer type.
     *
     * @param playerX  represents the player which marks X
     * @param playerO  represents the player which marks O
     * @param renderer represent the renderer console\none
     */
    public Game(Player playerX, Player playerO, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.RENDERER = renderer;
    }

    /**
     * Constructor for game - constructs a new Game with the given players, size, win streak and renderer type.
     *
     * @param playerX  represents the player which marks X
     * @param playerO  represents the player which marks O
     * @param size represents board size n
     * @param winStreak represents the win streak required to win a game
     * @param renderer represent the renderer console\none
     */
    public Game(Player playerX, Player playerO,int size, int winStreak, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        Board myBoard = new Board(size);
        this.SIZE = size;
        if (winStreak > myBoard.SIZE || winStreak <= 0) {
            this.winStreak = size;
        }
        else {
            this.winStreak = winStreak;
        }
        this.RENDERER = renderer;
    }

    /**
     * Getter for win streak
     * @return winStreak length
     */
    public int getWinStreak() {
        return this.winStreak;
    }
    /**
     * run method - a method that runs one round of the game.
     *
     * @return a mark that represents the game result {Win - X/O, Draw - BLANK}
     */
    public Mark run() {
        Board gameBoard = new Board(this.SIZE);
        Player[] players = {this.playerX, this.playerO};
        Mark[] marks = {Mark.X, Mark.O};
        int playerSelector = 0;
        Mark gameWinner = Mark.BLANK;
        this.gameInProgress = true;
        while (!this.gameFinished()) {
            this.RENDERER.renderBoard(gameBoard);
            // deciding which player supposed to play
            players[playerSelector % NUM_OF_PLAYERS].playTurn(gameBoard,
                    marks[playerSelector % NUM_OF_PLAYERS]);
            this.marksCounter++;
            // check if there is a winner after a player played a turn
            decideWinnerIdentity(gameBoard, marks[playerSelector % NUM_OF_PLAYERS],0,0);
            playerSelector++;
            gameWinner = this.getWinner();
        }
        this.RENDERER.renderBoard(gameBoard);
        this.gameInProgress = false;
        return gameWinner;
    }

    /**
     * isBoardFull function - check if the board is full of marks.
     */
    private boolean isBoardFull() {
        return this.marksCounter == SIZE * SIZE;
    }

    /**
     * getWinner - returns the mark X/O if there is a winner, or BLANK if the game is a tie
     */
    private Mark getWinner() {
        return this.winnerMark;
    }

    /**
     * setWinner - sets the winner of the game - assigns X/O if there is a winner or BLANK if there is a draw
     */
    private void setWinner(Mark mark) {
        this.winnerMark = mark;
    }

    /**
     * gameFinished - a method that checks if the game is finished - Tie/WinX/WinO will change the state of the game
     * to "Not inProgress"
     *
     * @return true if tha game is finished (can be ended with a win/draw), false otherwise
     */
    private boolean gameFinished() {
        return !gameInProgress;
    }

    /**
     * Finds the row's index.
     * @param Coordinate A coordinate on the board.
     * @return Returns the row's index of a coordinate in the board.
     */
    private int extractRow(int Coordinate) {
        return Coordinate / 10;
    }

    /**
     * Finds the column's index.
     * @param Coordinate A coordinate on the board.
     * @return Returns the column's index of a coordinate in the board.
     */
    private int extractCol(int Coordinate) {
        return Coordinate % 10;
    }

    /**
     * Checks if a move is a valid move
     * @param board Game board
     * @param Coordinate A coordinate on the board.
     * @return true if the coordinate is valid, false otherwise.
     */
    private boolean isLegalMove(Board board, int Coordinate) {
        int row = extractRow(Coordinate);
        int col = extractCol(Coordinate);
        return row >= 0 && col >= 0 && row < board.getSize() && col < board.getSize();
    }

    /**
     * Checks if there is a win streak in Diagonal right-down sequence for any of the players.
     * @param board - Game board.
     * @param row - row index.
     * @param col - col index.
     * @return true if there is a valid win streak in this direction, false otherwise.
     */
    private boolean checkDiagonalDown(Board board, Mark mark, int row, int col) {
        int diagonalCount = 1;
        if (!isLegalMove(board, row*10*(winStreak -1) + col + (winStreak - 1)) ||
                row + (winStreak) > board.getSize() || col + (winStreak) > board.getSize()) {
            return false;
        }
        for (int i = 0; i < this.winStreak - 1; i++) {
            if (board.getBoard()[row+1][col+1] == mark && board.getMark(row,col) == mark)
            {
                diagonalCount++;
                row++;
                col++;
            }
        }
        return diagonalCount == this.winStreak;
    }

    /**
     * Checks if there is a win streak in Diagonal right-up sequence for any of the players.
     * @param board - Game board.
     * @param row - row index.
     * @param col - col index.
     * @return true if there is a valid win streak in this direction, false otherwise.
     */
    private boolean checkDiagonalUp(Board board, Mark mark, int row, int col) {
        int diagonalUpCount = 1;
        if (!isLegalMove(board, (row*10 + col)-(winStreak -1)*9) ||
                row + (winStreak) > board.getSize() || col + (winStreak) > board.getSize()) {
            return false;
        }
        for (int i = 0; i < this.winStreak - 1; i++) {
            if (board.getBoard()[row-1][col+1] == mark && board.getMark(row,col) == mark)
            {
                diagonalUpCount++;
                row--;
                col++;
            }
        }
        return diagonalUpCount == this.winStreak;
    }

    /**
     * Checks if there is a win streak in Horizontal sequence for any of the players.
     * @param board - Game board.
     * @param row - row index.
     * @param col - col index.
     * @return true if there is a valid win streak in this direction, false otherwise.
     */
    private boolean checkHorizontal(Board board, Mark mark, int row, int col) {
        int horizontalCount = 1;
        if (!isLegalMove(board, row * 10 + col + (winStreak - 1)) || col + (winStreak) > board.getSize()) {
            return false;
        }
        for (int i = 0; i < this.winStreak -1; i++) {
            if (board.getBoard()[row][col + 1] == mark && board.getMark(row, col) == mark) {
                horizontalCount++;
                col++;
            }
        }
        return horizontalCount == this.winStreak;
    }

    /**
     * Checks if there is a win streak in Vertical sequence for any of the players.
     * @param board - Game board.
     * @param row - row index.
     * @param col - col index.
     * @return true if there is a valid win streak in this direction, false otherwise.
     */
    private boolean checkVertical(Board board, Mark mark, int row, int col) {
        int verticalCount = 1;
        if (!isLegalMove(board, row*10*(winStreak - 1) + col) || row + (winStreak) > board.getSize()) {
            return false;
        }
        for (int i = 0; i < this.winStreak - 1; i++) {
            if (board.getBoard()[row+1][col] == mark && board.getMark(row,col) == mark)
            {
                row++;
                verticalCount++;
            }
        }
        return verticalCount == this.winStreak;
    }

    /**
     * Checks if there is a valid win streak any direction for any of the players.
     * @param board - Game board.
     * @param mark - The mark to check if it has a win in any directions.
     * @return true if there is a valid win streak in any of the directions listed above, false otherwise.
     */
    private boolean checkAllDirectionsWin(Board board, Mark mark) {
        for (int row = 0; row < this.SIZE; row++) {
            for (int col = 0; col < this.SIZE; col++) {
                if (checkVertical(board, mark, row, col) || checkHorizontal(board, mark, row, col) ||
                        checkDiagonalDown(board, mark, row, col) || checkDiagonalUp(board, mark, row, col)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * decideWinnerIdentity - a function that check for a possible game winner,
     * after the player puts a Mark in the board. Sets the winer of the game and stops it if it is finished.
     */
    private void decideWinnerIdentity(Board board, Mark mark, int row, int col) {
        // checking for a possible win in any direction (row, column, diagonal up, diagonal down)
        if (checkAllDirectionsWin(board, mark)) {
            setWinner(mark);
            this.gameInProgress = false;
            return;
        }
        if (isBoardFull()) {
            this.gameInProgress = false;
        }
    }
}
