package util;

import hunting.util.PlayerPosition;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerPositionTest {

    /**
     * checking base cases and exception handeling for position
     */
    @Test
    public void testConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new PlayerPosition(-1, -1));

        PlayerPosition position = new PlayerPosition(2, 5);
        assertEquals(2, position.getI());
        assertEquals(5, position.getJ());
        assertNull(position.getPlayer());
        assertEquals("-", position.getValue());
    }

    /**
     * testing override equals method base on values of i and j
     */
    @Test
    public void testEquals() {
        PlayerPosition pos1 = new PlayerPosition(1, 2);
        PlayerPosition pos2 = new PlayerPosition(2, 3);
        PlayerPosition pos3 = new PlayerPosition(1, 2);

        assertNotEquals(pos1, pos2);
        assertEquals(pos1, pos3);
    }
}
