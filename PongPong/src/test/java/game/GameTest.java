package game;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class GameTest {

    public GameTest() {
    }

    public static int playAreaWidth = 500;
    public static int playAreaHeight = 500;

    @Before
    public void setUp() {
        GameCanvas.playAreaWidth = 500;
        GameCanvas.playAreaHeight = 500;
    }

    /**
     * Test of update method, of class Game.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Game instance = new Game();

        instance.player1.goDown(true);
        instance.player2.goDown(true);
        instance.player3.goRight(true);
        instance.player4.goRight(true);
        assertEquals(210, instance.player1.getYPosition());
        assertEquals(210, instance.player2.getYPosition());
        assertEquals(210, instance.player3.getXPosition());
        assertEquals(210, instance.player4.getXPosition());
        instance.update();
        assertEquals(212, instance.player1.getYPosition());
        assertEquals(212, instance.player2.getYPosition());
        assertEquals(212, instance.player3.getXPosition());
        assertEquals(212, instance.player4.getXPosition());
    }

    /**
     * Test of wallCollision method, of class Game.
     */
    @Test
    public void testWallCollision() {
        System.out.println("wallCollision");
        Game instance = new Game();

        // Move ball to wall
        instance.ball.setXPosition(0);

        // Check for wall collision
        assertFalse(instance.wallCollisionCheck);
        instance.wallCollision();
        assertTrue(instance.wallCollisionCheck);
    }

    /**
     * Test of paddleCollision method, of class Game.
     */
    @Test
    public void paddleCollision() {
        System.out.println("paddleCollision");
        Game instance = new Game();

        // Move ball to paddle
        instance.ball.setXPosition(100);
        instance.ball.setYPosition(100);
        instance.player1.setXPosition(100);
        instance.player1.setYPosition(100);
        instance.ball.update();
        instance.player1.update();
        // Check for paddle collision
        assertFalse(instance.paddleCollisionCheck);
        instance.paddleCollision();
        assertTrue(instance.paddleCollisionCheck);
    }
}
