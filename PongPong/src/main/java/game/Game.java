package game;

import ball.Ball;
import paddle.Paddle;
import java.util.Random;
import java.awt.Color;
import startMenu.StartMenu;

/**
 *
 * @author Tomi
 */
public class Game {

    public static Paddle player1;
    public static Paddle player2;
    public static Paddle player3;
    public static Paddle player4;
    public static Ball ball;

    int ballSize = 16;
    double ballSpeed = 1.2;
    Random randomGen = new Random();
    public boolean wallCollisionCheck = false;
    public boolean paddleCollisionCheck = false;

    /**
     * Game creates the players and the ball
     */
    public Game() {
        // Create paddles and set color according to the number of players
        player1 = new Paddle(10, GameCanvas.playAreaHeight / 2 - Paddle.height / 2, false);
        player2 = new Paddle(GameCanvas.playAreaWidth - 20, GameCanvas.playAreaHeight / 2 - Paddle.height / 2, false);
        player3 = new Paddle(GameCanvas.playAreaWidth / 2 - Paddle.height / 2, 10, true);
        player4 = new Paddle(GameCanvas.playAreaWidth / 2 - Paddle.height / 2, GameCanvas.playAreaHeight - 20, true);

        // Set colors according to the number of players
        if (StartMenu.numberOfPlayers == 1) {
            player1.setColor(Color.RED);
            player2.setColor(Color.RED);
            player3.setColor(Color.RED);
            player4.setColor(Color.RED);
        }
        if (StartMenu.numberOfPlayers == 2) {
            player1.setColor(Color.RED);
            player2.setColor(Color.BLUE);
            player3.setColor(Color.RED);
            player4.setColor(Color.BLUE);
        }
        if (StartMenu.numberOfPlayers == 4) {
            player1.setColor(Color.RED);
            player2.setColor(Color.GREEN);
            player3.setColor(Color.BLUE);
            player4.setColor(Color.YELLOW);
        }
        // Create ball
        ball = new Ball(GameCanvas.playAreaWidth / 2, GameCanvas.playAreaHeight / 2);
    }

    /**
     * Update players, check collisions and update ball
     */
    public void update() {
        player1.update();
        player2.update();
        player3.update();
        player4.update();
        wallCollision();
        paddleCollision();
        ball.update();
    }


    /**
     * Check for wall collisions.
     */
    public void wallCollision() {
        wallCollisionCheck = false;
        if (ball.getXPosition() <= 0) {
            ball.setXPosition(GameCanvas.playAreaWidth / 2);
            ball.setYPosition(GameCanvas.playAreaHeight / 2);
            wallCollisionCheck = true;
        } else if (ball.getXPosition() + ballSize >= GameCanvas.playAreaWidth) {
            ball.setXPosition(GameCanvas.playAreaWidth / 2);
            ball.setYPosition(GameCanvas.playAreaHeight / 2);
            wallCollisionCheck = true;
        }
        if (ball.getYPosition() <= 0) {
            ball.setXPosition(GameCanvas.playAreaWidth / 2);
            ball.setYPosition(GameCanvas.playAreaHeight / 2);
            wallCollisionCheck = true;
        } else if (ball.getYPosition() + ballSize >= GameCanvas.playAreaHeight) {
            ball.setXPosition(GameCanvas.playAreaWidth / 2);
            ball.setYPosition(GameCanvas.playAreaHeight / 2);
            wallCollisionCheck = true;
        }
    }
    
    /**
     * Check for paddle collisions.
     */
    public void paddleCollision() {
        paddleCollisionCheck = false;
        // Add some random behavior to the ball as it bounces from the paddle
        double randomNum1 = (((randomGen.nextDouble()) * 2) - 1) / 5;
        double randomNum2 = (((randomGen.nextDouble()) * 2) - 1) / 5;
        if (ball.boundingBox.intersects(player1.boundingBox)) {
            ball.setXSpeed(ballSpeed + randomNum1);
            paddleCollisionCheck = true;
        }
        if (ball.boundingBox.intersects(player2.boundingBox)) {
            ball.setXSpeed(-ballSpeed + randomNum1);
            paddleCollisionCheck = true;
        }
        if (ball.boundingBox.intersects(player3.boundingBox)) {
            ball.setYSpeed(ballSpeed + randomNum2);
            paddleCollisionCheck = true;
        }
        if (ball.boundingBox.intersects(player4.boundingBox)) {
            ball.setYSpeed(-ballSpeed + randomNum2);
            paddleCollisionCheck = true;
        }
    }
}
