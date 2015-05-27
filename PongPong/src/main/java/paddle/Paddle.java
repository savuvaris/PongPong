package paddle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import game.GameCanvas;

public class Paddle {

    int x, y;
    boolean rotate; // Rotate by 90 degrees for the top/bottom paddles
    int width = 10;
    int height = 80;
    int speed = 2;

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

    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        if (rotate) {
            g.fillRect(x, y, height, width);
        } else {
            g.fillRect(x, y, width, height);
        }
    }
}
