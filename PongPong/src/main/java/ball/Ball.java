package ball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Tomi
 */
public class Ball {

    double x, y;
    int ballSize = 16;
    double speedBase = 1.0;

    /**
     *  Horizontal speed
     */
    public double speedX;
    /**
     *  Vertical speed
     */
    public double speedY;
    Random randomGen = new Random();

    /**
     *  Bounding box to check for collisions.
     */
    public Rectangle boundingBox;

    /**
     *
     * @param x
     * @param y
     */
    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        speedX = 0;
        speedY = 0;
        // Bounding box for collisions
        boundingBox = new Rectangle(x, y, ballSize, ballSize);
        boundingBox.setBounds((int) Math.round(this.x), (int) Math.round(this.y), this.ballSize, this.ballSize);
    }

    /**
     *  Start the ball. This is invoked by the SPACE button
     */
    public void launch() {
        // Start in random direction
        double randomNum1 = randomGen.nextDouble();
        double randomNum2 = randomGen.nextDouble();
        if (randomNum1 <= 0.5) {
            speedX = speedBase;
        } else {
            speedX = -speedBase;
        }
        if (randomNum2 <= 0.5) {
            speedY = speedBase;
        } else {
            speedY = -speedBase;
        }
    }

    public void setXPosition(int x) {
        this.x = x;
    }

    public void setYPosition(int y) {
        this.y = y;
    }

    public int getXPosition() {
        return (int) Math.round(this.x);
    }

    public int getYPosition() {
        return (int) Math.round(this.y);
    }

    public void setXSpeed(double speedX) {
        this.speedX = speedX;
    }

    public void setYSpeed(double speedY) {
        this.speedY = speedY;
    }

    public double getXSpeed() {
        return speedX;
    }

    public double getYSpeed() {
        return speedY;
    }

    /**
     * Update new position
     */
    public void update() {
        boundingBox.setBounds((int) Math.round(this.x), (int) Math.round(this.y), this.ballSize, this.ballSize);
        x += speedX;
        y += speedY;
    }

    /**
     * Draw the ball
     * @param g
     */
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval((int) Math.round(this.x), (int) Math.round(this.y), ballSize, ballSize);
    }

}
