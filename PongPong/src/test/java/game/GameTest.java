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
        Game instance = new Game(4);

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

    @Test
    public void testScore1P() {
        System.out.println("testScore1P");
        Game instance = new Game(1);

        // Move ball to paddle
        instance.ball.setXPosition(100);
        instance.ball.setYPosition(100);
        instance.player1.setXPosition(100);
        instance.player1.setYPosition(100);
        instance.ball.update();
        instance.player1.update();

        // Check for score
        assertEquals(0, instance.p1score);
        instance.paddleCollision();
        instance.filter();
        instance.countScore(1);
        assertEquals(1, instance.p1score);

        // Move ball to wall
        instance.ball.setXPosition(0);
        instance.ball.update();
        instance.player1.update();
        instance.paddleCollision();
        instance.filter();
        instance.wallCollision();
        instance.ball.setXPosition(0);
        instance.countScore(1);

        // Check for score
        assertEquals(1, instance.p1score);
        instance.wallCollision();
        instance.countScore(1);
        assertEquals(0, instance.p1score);
    }

    @Test
    public void testScore2P() {
        System.out.println("testScore2P");
        Game instance = new Game(2);

        // Move ball to wall
        instance.ball.setXPosition(0);
        instance.ball.setYPosition(100);
        // Check for score
        assertEquals(0, instance.p1score);
        assertEquals(0, instance.p2score);
        instance.wallCollision();
        instance.countScore(2);
        assertEquals(0, instance.p1score);
        assertEquals(1, instance.p2score);
    }

    @Test
    public void testScore4P() {
        System.out.println("testScore4P");
        Game instance = new Game(4);

        // Move ball to wall
        instance.ball.setXPosition(100);
        instance.ball.setYPosition(0);
        // Check for score
        assertEquals(0, instance.p1score);
        assertEquals(0, instance.p2score);
        assertEquals(0, instance.p3score);
        assertEquals(0, instance.p4score);
        instance.wallCollision();
        instance.countScore(2);
        assertEquals(0, instance.p1score);
        assertEquals(1, instance.p2score);
        assertEquals(0, instance.p3score);
        assertEquals(0, instance.p4score);
    }

    /**
     * Test of wallCollision method, of class Game.
     */
    @Test
    public void testWallCollision1() {
        System.out.println("leftwallCollision");
        Game instance = new Game(4);

        // Move ball to wall
        instance.ball.setXPosition(0);

        // Check for wall collision
        assertFalse(instance.leftWallCollision);
        instance.wallCollision();
        assertTrue(instance.leftWallCollision);
    }

    @Test
    public void testWallCollision2() {
        System.out.println("topwallCollision");
        Game instance = new Game(4);

        // Move ball to wall
        instance.ball.setYPosition(0);

        // Check for wall collision
        assertFalse(instance.topWallCollision);
        instance.wallCollision();
        assertTrue(instance.topWallCollision);
    }

    @Test
    public void testWallCollision3() {
        System.out.println("rightwallCollision");
        Game instance = new Game(4);

        // Move ball to wall
        instance.ball.setXPosition(playAreaWidth);

        // Check for wall collision
        assertFalse(instance.rightWallCollision);
        instance.wallCollision();
        assertTrue(instance.rightWallCollision);
    }

    @Test
    public void testWallCollision4() {
        System.out.println("bottomwallCollision");
        Game instance = new Game(4);

        // Move ball to wall
        instance.ball.setYPosition(playAreaHeight);

        // Check for wall collision
        assertFalse(instance.bottomWallCollision);
        instance.wallCollision();
        assertTrue(instance.bottomWallCollision);
    }

    /**
     * Test of paddleCollision method, of class Game.
     */
    @Test
    public void paddleCollision() {
        System.out.println("paddleCollision");
        Game instance = new Game(4);

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
