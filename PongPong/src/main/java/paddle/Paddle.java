package paddle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import game.GameCanvas;

/**
 *
 * @author Tomi
 */
public class Paddle {

    int x, y;
    boolean rotate; // Rotate by 90 degrees for the top/bottom paddles
    int width = 10;

    /**
     *  Height is used to calculate how far the paddle can travel before hitting the edges of the play area.
     */
    public static int height = 80;
    int speed = 2;
    Color paddleColor;

    /**
     *  Bounding box to check collisions
     */
    public Rectangle boundingBox;

    boolean goUp = false;
    boolean goDown = false;
    boolean goLeft = false;
    boolean goRight = false;

    /**
     *
     * @return
     */
    public int getXPosition() {
        return x;
    }

    /**
     *
     * @return
     */
    public int getYPosition() {
        return y;
    }

        /**
     *
     * @return
     */
    public void setXPosition(int x) {
        this.x = x;
    }

    /**
     *
     * @return
     */
    public void setYPosition(int y) {
        this.y = y;
    }
    
    /**
     *
     * @param goUp
     */
    public void goUp(boolean goUp) {
        this.goUp = goUp;
    }

    /**
     *
     * @param goDown
     */
    public void goDown(boolean goDown) {
        this.goDown = goDown;
    }

    /**
     *
     * @param goLeft
     */
    public void goLeft(boolean goLeft) {
        this.goLeft = goLeft;
    }

    /**
     *
     * @param goRight
     */
    public void goRight(boolean goRight) {
        this.goRight = goRight;
    }

    /**
     *
     * @param paddleColor
     */
    public void setColor(Color paddleColor) {
        this.paddleColor = paddleColor;
    }
    
    /**
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
     *  Set the new location. Also set the orientation of the paddle.
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
