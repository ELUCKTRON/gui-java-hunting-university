package board;

import hunting.board.Board;
import hunting.util.PlayerPosition;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTest {

    /**
     * check creating of the board and player and positions on the board
     */
    @Test
    public void constructorTest() {

        assertThrows(IllegalArgumentException.class, () -> new Board(4));
        assertThrows(IllegalArgumentException.class, () -> new Board(0));
        assertThrows(IllegalArgumentException.class, () -> new Board(-1));

        Board board = new Board(3);

        assertEquals(3, board.getPositions().length);
        assertEquals(3, board.getPositions()[0].length);

        assertEquals("H", board.getPositions()[0][0].getValue());
        assertEquals("F", board.getPositions()[1][1].getValue());

        assertEquals(board.getPositions()[1][1], board.getFugitive().getPlayerPosition());

    }

    /**
     * checking where player can move is correct and exception if input arg is null
     */
    @Test
    public void VarifiedPositionTest() {

        Board board = new Board(3);
        assertThrows(IllegalArgumentException.class, () -> board.verifiedPositions(null));

        // varified position for player fiugitive at 1 1 is 0 1 ,
        // 1 0 , 1 2
        // 2 1

        ArrayList<PlayerPosition> expected = new ArrayList<>();
        PlayerPosition pos1 = new PlayerPosition(0, 1);
        PlayerPosition pos2 = new PlayerPosition(1, 0);
        PlayerPosition pos3 = new PlayerPosition(1, 2);
        PlayerPosition pos4 = new PlayerPosition(2, 1);

        expected.add(pos1);
        expected.add(pos2);
        expected.add(pos3);
        expected.add(pos4);

        assertEquals(expected, board.verifiedPositions(board.getFugitive()));

    }

}
