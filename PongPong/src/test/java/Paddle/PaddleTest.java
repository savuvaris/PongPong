package Paddle;

import game.GameCanvas;
import java.awt.Graphics;
import static junit.framework.TestCase.assertEquals;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import paddle.Paddle;

public class PaddleTest {

    public PaddleTest() {
    }

    public static int playAreaWidth = 500;
    public static int playAreaHeight = 500;

    @Before
    public void setUp() {
        GameCanvas.playAreaWidth = 500;
        GameCanvas.playAreaHeight = 500;
    }

    @Test
    public void testGoDown() {
        System.out.println("goDown");
        Paddle instance = new Paddle(110, 120, false);
        assertEquals(120, instance.getYPosition());
        instance.goDown(true);
        instance.update();
        assertEquals(122, instance.getYPosition());
        assertEquals(110, instance.getXPosition());
    }

    @Test
    public void testGoUp() {
        System.out.println("goUp");
        Paddle instance = new Paddle(110, 120, false);
        assertEquals(120, instance.getYPosition());
        instance.goUp(true);
        instance.update();
        assertEquals(118, instance.getYPosition());
        assertEquals(110, instance.getXPosition());
    }

    @Test
    public void testGoLeft() {
        System.out.println("goLeft");
        Paddle instance = new Paddle(110, 120, true);
        assertEquals(110, instance.getXPosition());
        instance.goLeft(true);
        instance.update();
        assertEquals(120, instance.getYPosition());
        assertEquals(108, instance.getXPosition());
    }

    @Test
    public void testGoRight() {
        System.out.println("goRight");
        Paddle instance = new Paddle(110, 120, true);
        assertEquals(110, instance.getXPosition());
        instance.goRight(true);
        instance.update();
        assertEquals(120, instance.getYPosition());
        assertEquals(112, instance.getXPosition());
    }

}
