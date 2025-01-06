/**
 * @author Amit Halbreich, 208917393
 * class for Tic Tac Toe Tournament.
 * this class represents a Tic Tac Toe Tournament - running multiple games time after time.
 * a Tournament will be a sequence of tic tac toe rounds between two players, with a render type.
 * during the tournament, the players wil be swapped in each round:
 * if in the first round the first player mark was X, in the second round its mark wil be O.
 */

public class Tournament {

    private final Player firstPlayer;
    private final Player secondPlayer;
    private final Renderer RENDERER;
    private int roundCounter = 0;

    private static final int FIRST_PLAYER = 0;
    private static final int SECOND_PLAYER = 1;
    private static final int DEFAULT_PLAYERS_NUM = 2;

    // arguments and constants
    /**
     * represents the valid num of program arguments
     */
    public static final int NUM_ARGS = 6;
    /**
     * represents the rounds index in the program arguments
     */
    public static final int ROUNDS_ARG = 0;
    /**
     * represents the board's size in the program arguments
     */
    public static final int SIZE_ARG = 1;
    /**
     * represents the win streak count in the program arguments
     */
    public static final int WIN_STREAK_ARG = 2;
    /**
     * represents the render type index in the program arguments
     */
    public static final int RENDERER_ARG = 3;
    /**
     * represents the first player index in the program arguments
     */
    public static final int PLAYER_ONE_ARG = 4;
    /**
     * represents the second player index in the program arguments.
     */
    public static final int PLAYER_TWO_ARG = 5;
    /**
     * represents a valid usage of the command line arguments - will be printed in case on wrong input.
     */
    public static final String USAGE_MSG = "Usage: Java Tournament [round count] [size] [win_streak]" +
            " [render target: console/none]\n" +
            "[player: human/whatever/clever/genius]⨉2 ";
    /**
     * represents error messages that will be printed in case on wrong input in cmd arguments.
     */
    private static final String INVALID_PLAYER_TYPE_ERR = "Choose a player,and start again\n" +
            "The players: [human,clever,whatever,genius]";
    private static final String INVALID_RENDERER_ERR = "Invalid Renderer type, Renderer type must be [console,none]" +
            " please try again.";
    private static final String BOARD_SIZE_ERR = "Invalid board size, board size must be greater than 0, " +
            "please try again.";
    private static final String ROUNDS_ERR = "Invalid rounds given, tournament rounds must be greater than 0," +
            " please try again.";
    private static final String WIN_STREAK_ERR = "Invalid win streak given, win streak must be greater than 0," +
            " please try again.";

    /**
     * Constructor for Tournament type.
     * @param rounds - Number of rounds in a tournament.
     * @param renderer - Renderer type.
     * @param players - Array of player Objects.
     */
    public Tournament(int rounds, Renderer renderer, Player[] players) {
        this.firstPlayer = players[FIRST_PLAYER];
        this.secondPlayer = players[SECOND_PLAYER];
        this.RENDERER = renderer;
        this.roundCounter = rounds;
    }

    /**
     * playTournament function - runs a tic tac toe tournament.
     * the tournament will run (the rounds amount that the user inserted as an input) times
     * the tournament results will be printed at the end.
     */
    public void playTournament(int size, int winStreak, String[] playerNames) {
        Game game;
        int firstPlayerWins = 0;
        int secondPlayerWins = 0;
        int tiesCount = 0;
        int playerSelector = 2;
        int roundCount = 0;
        while (roundCount < this.roundCounter) {
            game = buildGame(this.firstPlayer, this.secondPlayer, size, winStreak, this.RENDERER,  playerSelector);
            Mark result = game.run();
            roundCount++;
            playerSelector++;
            if ((result == Mark.X && playerSelector % DEFAULT_PLAYERS_NUM == 0)
                    || (result == Mark.O && playerSelector % 2 == 1))
            {
                firstPlayerWins++;
            }
            else if ((result == Mark.X && playerSelector % DEFAULT_PLAYERS_NUM == 1)
                    || (result == Mark.O && playerSelector % 2 == 0)) {
                secondPlayerWins++;
            }
            else {
                tiesCount++;
            }
        }
        printTournamentResults(playerNames, tiesCount, firstPlayerWins, secondPlayerWins);
    }

    /**
     * buildGame function - Constructs a new game for the tournament, determined by playerSelector.
     * in each round, the players will be swapped.
     */
    private Game buildGame(Player firstPlayer, Player secondPlayer, int size, int winStreak,
                           Renderer renderer, int playerSelector) {
        if (playerSelector % DEFAULT_PLAYERS_NUM == 1) {
            return new Game(firstPlayer,secondPlayer,size, winStreak, renderer);
        }
        return new Game(secondPlayer,firstPlayer,size, winStreak,  renderer);
    }
    /**
     * areArgumentsValid - a static function - helper for main.
     * this function checks if the given arguments are valid and returns error if they are invalid.
     */
    private static boolean areArgumentsValid(String[] args) {
        if (args.length != NUM_ARGS) {
            System.err.println(USAGE_MSG);
            return false;
        }
        int boardSize = Integer.parseInt(args[SIZE_ARG]);
        int winStreakNum = Integer.parseInt(args[WIN_STREAK_ARG]);
        int roundsNum = Integer.parseInt(args[ROUNDS_ARG]);
        if (boardSize <= 0) {
            System.err.println(BOARD_SIZE_ERR);
            return false;
        }
        if (winStreakNum <= 0) {
            System.err.println(ROUNDS_ERR);
            return false;
        }
        if (roundsNum <= 0) {
            System.err.println(WIN_STREAK_ERR);
            return false;
        }
        return true;
    }

    /**
     * printTournamentResults function - prints the tournament result with a suitable format.
     * @param playerNames - the 2 player names
     * @param tiesCount - Counter for ties
     * @param firstPlayerWins - Counter for first player wins
     * @param secondPlayerWins  Counter for second player wins
     */
    private void printTournamentResults(String[] playerNames, int tiesCount,
                                        int firstPlayerWins, int secondPlayerWins) {
        System.out.println("######## Results #########");
        System.out.println("Player 1, " + playerNames[0] + " won: " + firstPlayerWins + " rounds");
        System.out.println("Player 2, " + playerNames[1] + " won: " + secondPlayerWins + " rounds");
        System.out.println("Ties: " + tiesCount);
    }

    /**
     * The main function of the program.
     * This function initialized a TicTacToe tournament, and runs it.
     *
     * @param args Represents the program arguments
     */
    public static void main(String[] args) {
        if (areArgumentsValid(args)) {
            // Building player and renderer factories
            PlayerFactory playerFactory = new PlayerFactory();
            RendererFactory rendererFactory = new RendererFactory();
            // Reading and interpreting the input arguments and save them to variables for later use.
            int boardSize = Integer.parseInt(args[SIZE_ARG]);
            int winStreak = Integer.parseInt(args[WIN_STREAK_ARG]);
            int rounds = Integer.parseInt(args[ROUNDS_ARG]);
            Player firstPlayer = playerFactory.buildPlayer(args[PLAYER_ONE_ARG]);
            Player secondPlayer = playerFactory.buildPlayer(args[PLAYER_TWO_ARG]);
            String[] playerNames = new String[]{args[PLAYER_ONE_ARG], args[PLAYER_TWO_ARG]};
            Renderer renderer;
            renderer = rendererFactory.buildRenderer(args[RENDERER_ARG],Integer.parseInt(args[SIZE_ARG]));
            // checking if the user has inserted an invalid renderer type
            if (renderer == null) {
                System.err.println(INVALID_RENDERER_ERR);
                return;
            }
            // checking if the user has inserted an invalid player name or type
            if (firstPlayer == null || secondPlayer == null) {
                System.err.println(INVALID_PLAYER_TYPE_ERR);
                return;
            }
            // creating a new Tournament and run it by PlayTournament
            Player[] players = {firstPlayer, secondPlayer};
            Tournament newTournament = new Tournament(rounds, renderer, players);
            newTournament.playTournament(boardSize, winStreak, playerNames);
        }

    }
}

