package ball;

import ball.Ball;
import junit.framework.TestCase;

public class BallTest extends TestCase {

    public BallTest(String testName) {
        super(testName);
    }

    // Ball class tests
    public void testBallGetXPosition() {
        System.out.println("Test getPosition and Ball constructor");
        Ball instance = new Ball(10, 20);
        assertEquals(10, instance.getXPosition());
    }

    public void testBallGetYPosition() {
        System.out.println("Test getPosition and Ball constructor");
        Ball instance = new Ball(10, 20);
        assertEquals(20, instance.getYPosition());
    }

    public void testBallGetXPosition2() {
        System.out.println("Test getPosition and setPosition");
        Ball instance = new Ball(10, 20);
        instance.setXPosition(11);
        assertEquals(11, instance.getXPosition());
    }

    public void testBallGetYPosition2() {
        System.out.println("Test getPosition and setPosition");
        Ball instance = new Ball(10, 20);
        instance.setYPosition(21);
        assertEquals(21, instance.getYPosition());
    }

    public void testBallGetXSpeed() {
        System.out.println("Test getSpeed and Ball constructor");
        Ball instance = new Ball(10, 20);
        assertEquals(1.5, instance.getXSpeed());
    }

    public void testBallGetYSpeed() {
        System.out.println("Test getSpeed and Ball constructor");
        Ball instance = new Ball(10, 20);
        assertEquals(1.5, instance.getYSpeed());
    }

    public void testBallGetXSpeed2() {
        System.out.println("Test getSpeed and setSpeed");
        Ball instance = new Ball(10, 20);
        instance.setXSpeed(5);
        assertEquals(5.0, instance.getXSpeed());
    }

    public void testBallGetYSpeed2() {
        System.out.println("Test getSpeed and setSpeed");
        Ball instance = new Ball(10, 20);
        instance.setYSpeed(6);
        assertEquals(6.0, instance.getYSpeed());
    }

}
