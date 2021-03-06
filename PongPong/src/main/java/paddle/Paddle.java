package paddle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import game.GameCanvas;

/**
 *
 * @author Tomi
 * Paddle is the class for the player controllable paddle
 */
public class Paddle {

    /**
     * Paddle location in x
     */
    int x;
    /**
     * Paddle location in y
     */
    int y;
    boolean rotate; // Rotate by 90 degrees for the top/bottom paddles
    /**
     * Paddle width
     */
    int width = 10;

    /**
     * Height is used to calculate how far the paddle can travel before hitting
     * the edges of the play area.
     */
    public static int height = 80;
    int speed = 2;
    Color paddleColor;

    /**
     * Bounding box to check collisions
     */
    public Rectangle boundingBox;

    boolean goUp = false;
    boolean goDown = false;
    boolean goLeft = false;
    boolean goRight = false;

    public int getXPosition() {
        return x;
    }

    public int getYPosition() {
        return y;
    }

    public void setXPosition(int x) {
        this.x = x;
    }

    public void setYPosition(int y) {
        this.y = y;
    }

    public void goUp(boolean goUp) {
        this.goUp = goUp;
    }

    public void goDown(boolean goDown) {
        this.goDown = goDown;
    }

    public void goLeft(boolean goLeft) {
        this.goLeft = goLeft;
    }

    public void goRight(boolean goRight) {
        this.goRight = goRight;
    }

    public void setColor(Color paddleColor) {
        this.paddleColor = paddleColor;
    }

    /**
     * Paddle constructor
     *
     * @param x
     * @param y
     * @param rotate
     */
    public Paddle(int x, int y, boolean rotate) {
        this.x = x;
        this.y = y;
        this.rotate = rotate;

        if (rotate) {
            boundingBox = new Rectangle(x, y, height, width);
            boundingBox.setBounds(x, y, height, width);
        } else {
            boundingBox = new Rectangle(x, y, width, height);
            boundingBox.setBounds(x, y, width, height);
        }
    }

    /**
     * Set the new location. Also set the orientation of the paddle.
     */
    public void update() {
        if (rotate) {
            boundingBox.setBounds(x, y, height, width);
        } else {
            boundingBox.setBounds(x, y, width, height);
        }

        if (rotate) {
            if (goLeft && x > 0) {
                x -= speed;
            }
            if (goRight && x < GameCanvas.playAreaWidth - height) { // height is actually width, when paddle is rotated...
                x += speed;
            }
        } else {
            if (goUp && y > 0) {
                y -= speed;
            }
            if (goDown && y < GameCanvas.playAreaHeight - height) {
                y += speed;
            }
        }
    }

    /**
     * Draw the paddle
     *
     * @param g
     */
    public void render(Graphics g) {
        g.setColor(paddleColor);
        if (rotate) {
            g.fillRect(x, y, height, width);
        } else {
            g.fillRect(x, y, width, height);
        }
    }
}
