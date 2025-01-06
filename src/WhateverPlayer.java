import java.util.Random;

/**
 * @author Amit Halbreich, 208917393
 * A whatever player - type of a Tic Tac Toe player.
 * This class represents a whatever player which competes in TicTacToe games and tournaments.
 * The player represents a logical strategy when playing a turn, in several games.
 * a whatever player will randomly mark an available and valid spot in the board.
 *
 */
public class WhateverPlayer implements Player {
    private final Random random = new Random();
    private static final int DECIMAL_BASE = 10;

    @Override
    public void playTurn(Board board, Mark mark)
    {
        int randomCoord = 0;
        int [] availableCoordinates = new int[board.getSize()* board.getSize()];
        int availableCoordinatesSize = 0;
        for (int row=0; row <board.SIZE; row++) {
            for (int col=0; col <board.SIZE; col++) {
                if (board.getMark(row, col) == Mark.BLANK) {
                    availableCoordinates[availableCoordinatesSize++] = DECIMAL_BASE * row + col;
                }
            }
        }
        if (!isBoardFull(board)) {
            randomCoord = random.nextInt(availableCoordinatesSize);
        }
        int col = availableCoordinates[randomCoord] % DECIMAL_BASE;
        int row = availableCoordinates[randomCoord] / DECIMAL_BASE;
        board.putMark(mark, row, col);
    }

    private boolean isBoardFull (Board board) {
        int blankCounter = 0;
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                if (board.getMark(row,col) == Mark.BLANK) {
                    blankCounter++;
                }
            }
        }
        return blankCounter == 0;
    }
}
