package game;

import static junit.framework.TestCase.assertEquals;
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
        assertEquals(60, instance.player1.getYPosition());
        assertEquals(60, instance.player2.getYPosition());
        assertEquals(60, instance.player3.getXPosition());
        assertEquals(60, instance.player4.getXPosition());
        instance.update();
        assertEquals(62, instance.player1.getYPosition());
        assertEquals(62, instance.player2.getYPosition());
        assertEquals(62, instance.player3.getXPosition());
        assertEquals(62, instance.player4.getXPosition());
    }

    /**
     * Test of collision method, of class Game.
     */
    @Test
    public void testCollision() {
        System.out.println("collision");
        Game instance = new Game();
        instance.collision();
    }

}
