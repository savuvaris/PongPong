package game;

import ball.Ball;
import paddle.Paddle;
import java.util.Random;

public class Game {

    public static Paddle player1;
    public static Paddle player2;
    public static Paddle player3;
    public static Paddle player4;
    public static Ball ball;

    int ballSize = 16;
    double ballSpeed = 1.5;
    Random randomGen = new Random();

    public Game() {
        player1 = new Paddle(10, 60, false);
        player2 = new Paddle(GameCanvas.playAreaWidth - 20, 60, false);
        player3 = new Paddle(60, 10, true);
        player4 = new Paddle(60, GameCanvas.playAreaHeight - 20, true);
        ball = new Ball(GameCanvas.playAreaWidth / 2, GameCanvas.playAreaHeight / 2);
    }

    public void update() {
        player1.update();
        player2.update();
        player3.update();
        player4.update();
        collision();
        ball.update();
    }

    // Collisions
    public void collision() {
        // Check wall collisions
        // After collision 
        // - return to center
        // - wait for space button (not done)
        // - start in random direction (not done)
        if (ball.getXPosition() <= 0) {
            ball.setXPosition(GameCanvas.playAreaWidth / 2);
            ball.setYPosition(GameCanvas.playAreaHeight / 2);
        } else if (ball.getXPosition() + ballSize >= GameCanvas.playAreaWidth) {
            ball.setXPosition(GameCanvas.playAreaWidth / 2);
            ball.setYPosition(GameCanvas.playAreaHeight / 2);
        }
        if (ball.getYPosition() <= 0) {
            ball.setXPosition(GameCanvas.playAreaWidth / 2);
            ball.setYPosition(GameCanvas.playAreaHeight / 2);
        } else if (ball.getYPosition() + ballSize >= GameCanvas.playAreaHeight) {
            ball.setXPosition(GameCanvas.playAreaWidth / 2);
            ball.setYPosition(GameCanvas.playAreaHeight / 2);
        }

        // Check paddle collisions
        // Add some random behavior to the ball as it bounces from the paddle
        double randomNum1 = (((randomGen.nextDouble()) * 2) - 1) / 5;
        double randomNum2 = (((randomGen.nextDouble()) * 2) - 1) / 5;
        if (ball.boundingBox.intersects(player1.boundingBox)) {
            ball.setXSpeed(ballSpeed + randomNum1);
        }
        if (ball.boundingBox.intersects(player2.boundingBox)) {
            ball.setXSpeed(-ballSpeed + randomNum1);
        }
        if (ball.boundingBox.intersects(player3.boundingBox)) {
            ball.setYSpeed(ballSpeed + randomNum2);
        }
        if (ball.boundingBox.intersects(player4.boundingBox)) {
            ball.setYSpeed(-ballSpeed + randomNum2);
        }
    }
}
