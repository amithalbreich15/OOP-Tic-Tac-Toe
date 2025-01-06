/**
 * @author Amit Halbreich, 208917393
 * a PlayerFactory - creates player of certain types.
 * a design pattern that builds a player of these special types [human, whatever, clever, genius]
 */
public class PlayerFactory {
    private static final String WHATEVER_PLAYER = "whatever";
    private static final String CLEVER_PLAYER = "clever";
    private static final String GENIUS_PLAYER = "genius";
    private static final String HUMAN_PLAYER = "human";

    /**
     * buildPlayer function - builds a player according to the given playerType string - also not key sensitive.
     *
     * @param playerType represents a given player type that need to be constructed.
     * @return return a player if playerType is in [whatever, clever, genius, human], null otherwise
     */
    public Player buildPlayer(String playerType) {
        playerType = playerType.toLowerCase();
        Player player = null;
        switch (playerType) {
            case WHATEVER_PLAYER:
                player = new WhateverPlayer();
                break;
            case CLEVER_PLAYER:
                player = new CleverPlayer();
                break;
            case GENIUS_PLAYER:
                player = new GeniusPlayer();
                break;
            case HUMAN_PLAYER:
                player = new HumanPlayer();
                break;
            default:
                break;
        }
        return player;
    }

}

