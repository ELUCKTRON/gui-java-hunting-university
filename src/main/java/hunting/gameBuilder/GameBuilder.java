package hunting.gameBuilder;

import hunting.board.Board;
import hunting.player.Player;
import hunting.util.Role;
import hunting.util.PlayerPosition;

/**
 * a class which manages the game rules and run and stop it properly
 */
public class GameBuilder {

    private int turn;
    private final int endTurn;
    public boolean isGameOver;

    private Board gameBoard;

    public int getTurn() {
        return turn;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    /**
     * creating the board and setting up the rules base on input params and check
     * the validation of the inputs
     * 
     * @param matrixSize
     * @param endTurn
     */
    public GameBuilder(int matrixSize, int endTurn) {

        if (matrixSize <= 2 || matrixSize % 2 == 0) {
            throw new IllegalArgumentException("Board needs to be odd number and more than 2");
        }
        if (endTurn <= 0) {
            throw new IllegalArgumentException("endTurn needs to be biger than 0");
        }

        turn = 0;
        this.endTurn = endTurn;
        isGameOver = false;

        gameBoard = new Board(matrixSize);

    }

    /**
     * checking the role of player who can play that round
     * 
     * @param player
     * @return
     */
    public boolean varifyPlayerTurn(Role player) {
        Role playable = ((turn % 2 == 0) ? Role.HUNTER : Role.FUGITIVE);
        return playable == player;
    }

    /**
     * runing the command from UI and check the validity of params ,
     * and increasing the turns if it was succesful
     * 
     * @param player
     * @param newPosition
     * @return
     */
    public boolean run(Player player, PlayerPosition newPosition) {
        if (player == null || newPosition == null) {
            throw new IllegalArgumentException("player or new position is null ");
        }

        if (!isGameOver && turn != endTurn) {
            boolean didPlay = player.play(newPosition);
            if (didPlay) {
                turn++;
                return true;
            }
        }

        return false;
    }

    /**
     * the main rule for the game if some one has won the game or not or even rare
     * case if its draw
     * 
     * @return the name of winner or draw or null if game still has no victors
     *         meaning game is to be continued
     */
    public String winner() {
        if (turn == endTurn && gameBoard.verifiedPositions(gameBoard.getFugitive()).isEmpty()) {
            isGameOver = true;
            return "draw";
        } else if (turn == endTurn) {
            isGameOver = true;
            return "Fugitive Won";
        } else if (gameBoard.verifiedPositions(gameBoard.getFugitive()).isEmpty()) {
            isGameOver = true;
            return "Hunters Won";
        } else {
            return null;
        }
    }

}
