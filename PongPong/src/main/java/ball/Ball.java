package ball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {

    double x, y;
    int ballSize = 16;
    double speedBase = 1.5;
    public double speedX, speedY;
    Random randomGen = new Random();
    public Rectangle boundingBox;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        speedX = speedBase;
        speedY = speedBase;
        boundingBox = new Rectangle(x, y, ballSize, ballSize);
        boundingBox.setBounds((int)Math.round(this.x), (int)Math.round(this.y), this.ballSize, this.ballSize);
    }

    public void setXPosition(int x) {
        this.x = x;
    }

    public void setYPosition(int y) {
        this.y = y;
    }

    public int getXPosition() {
        return (int)Math.round(this.x);
    }

    public int getYPosition() {
        return (int)Math.round(this.y);
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

    public void update() {
        boundingBox.setBounds((int)Math.round(this.x), (int)Math.round(this.y), this.ballSize, this.ballSize);
        x += speedX;
        y += speedY;
    }

    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval((int)Math.round(this.x), (int)Math.round(this.y), ballSize, ballSize);
    }

}
