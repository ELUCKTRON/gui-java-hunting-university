package gameBuilder;

import hunting.gameBuilder.GameBuilder;
import hunting.util.Role;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class gameBuilderTest {

        /**
         * testing constructor parameters
         */
        @Test
        public void constructorTest() {

                assertThrows(IllegalArgumentException.class, () -> new GameBuilder(3, -2));
                assertThrows(IllegalArgumentException.class, () -> new GameBuilder(4, 10));

        }

        /**
         * testing method which decides base on the turn which role should play
         */
        @Test
        public void varifyPlayerTurnTest() {

                GameBuilder gameBuilder = new GameBuilder(3, 12);

                // since its first turn it should be hunter
                assertTrue(gameBuilder.varifyPlayerTurn(Role.HUNTER));
        }

        /**
         * method which run the program ( turn move and everything )
         */
        @Test
        public void runTest() {
                GameBuilder gameBuilder = new GameBuilder(3, 12);

                assertThrows(IllegalArgumentException.class, () -> gameBuilder.run(null, null));

                assertTrue(gameBuilder.run(gameBuilder.getGameBoard().getFugitive(),
                                gameBuilder.getGameBoard().getPositions()[0][1]));
                assertEquals(gameBuilder.getGameBoard().getFugitive(),
                                gameBuilder.getGameBoard().getPositions()[0][1].getPlayer());
        }

        // checking if there is a winner at this part of the game and if it is who won
        // and stoping the game
        @Test
        public void winnerTest() {

                GameBuilder gameBuilder = new GameBuilder(3, 12);

                // null for winner method means game is to be continued
                assertNull(gameBuilder.winner());
                assertFalse(gameBuilder.isGameOver);

                // simulating the moves
                gameBuilder.run(gameBuilder.getGameBoard().getPositions()[0][0].getPlayer(),
                                gameBuilder.getGameBoard().getPositions()[1][0]);
                gameBuilder.run(gameBuilder.getGameBoard().getFugitive(),
                                gameBuilder.getGameBoard().getPositions()[2][1]);
                gameBuilder.run(gameBuilder.getGameBoard().getPositions()[1][0].getPlayer(),
                                gameBuilder.getGameBoard().getPositions()[1][1]);

                assertEquals("Hunters Won", gameBuilder.winner());
                assertTrue(gameBuilder.isGameOver);
        }

}
