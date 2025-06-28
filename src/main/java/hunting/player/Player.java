package hunting.player;

import hunting.board.Board;
import hunting.util.PlayerPosition;
import hunting.util.Role;

import java.util.ArrayList;

/**
 * Players who play on the board
 * have attrebute PlayerPositions role and board which they can play on
 */
public class Player {

    private PlayerPosition playerPosition;
    private final Role role;

    private final Board board;

    public PlayerPosition getPlayerPosition() {
        return playerPosition;
    }


    public Role getRole() {
        return role;
    }


    /**
     * constructing the PLayer and giving the initial values
     * @param playerPosition
     * @param role
     * @param board
     */
    public Player(PlayerPosition playerPosition, Role role , Board board){
        if( playerPosition == null || role == null || board == null){
            throw new IllegalArgumentException("Arguments cannot be null");
        }

        this.playerPosition = playerPosition;
        this.role = role;
        this.board = board;

    }

    /**
     * moving player to new position if its posible
     * and giving it position new values base on player role
     * and setting the old values to "-"
     * @param position
     * @return if its not posible return false if its posible to move return true
     */

    public boolean play(PlayerPosition position){
        boolean result = false;

        ArrayList<PlayerPosition> available = this.board.verifiedPositions(this);
        if (position == null || !position.getValue().equals("-") ) { return false; }

        else if(available.contains(position) && position.getValue().equals("-")){
            this.playerPosition.setValue("-");
            this.playerPosition = position;
            position.setValue(this.getRole().toString().substring(0,1));
            position.setPlayer(this);
            result = true;
        }

        return result;
    }










}
