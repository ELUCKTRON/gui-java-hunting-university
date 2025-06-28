package Player;

import hunting.board.Board;
import hunting.player.Player;
import hunting.util.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class PlayerTest {

    /**
     * check if any element is null
     */
    @Test
    public void constructorTest(){

        assertThrows(IllegalArgumentException.class,()->new Player(null, Role.FUGITIVE,null) );


    }

    /**
     * checking movement of player basse on position giving to them
     * and which checks if position is null or some player on it shouldnt move
     * and should return false
     */
    @Test
    public void playTest(){

        Board board = new Board(3);

        Player player = board.getPositions()[0][0].getPlayer(); // which will be hunter

        assertTrue(player.play(board.getPositions()[0][1]));

        assertEquals(board.getPositions()[0][1],player.getPlayerPosition());

        assertFalse(player.play(board.getPositions()[1][1]));   // moving where another player (which is figutive is in) which shouldnt happend
        assertEquals(board.getPositions()[0][1],player.getPlayerPosition());

        assertFalse(player.play(null));
        assertEquals(board.getPositions()[0][1],player.getPlayerPosition());

    }



}
