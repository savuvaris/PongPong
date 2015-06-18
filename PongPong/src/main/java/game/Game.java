package game;

import ball.Ball;
import paddle.Paddle;
import java.util.Random;
import java.awt.Color;
import startMenu.StartMenu;

/**
 *
 * @author Tomi
 * Class creates the players and the ball. It updates the positions and checks for collisions.
 */
public class Game {

    public static Paddle player1;
    public static Paddle player2;
    public static Paddle player3;
    public static Paddle player4;
    public static Ball ball;

    public int p1score;
    public int p2score;
    public int p3score;
    public int p4score;

    int numberOfPlayers;
    int ballSize = 16;
    double ballSpeed = 1.2;
    Random randomGen = new Random();
    public boolean wallCollisionCheck = false;
    boolean leftWallCollision = false;
    boolean rightWallCollision = false;
    boolean topWallCollision = false;
    boolean bottomWallCollision = false;
    public boolean paddleCollisionCheck = false;
    boolean oldpaddleCollisionCheck = false;
    boolean filteredPaddleCollisionCheck = false;

    /**
     * Game creates the players and the ball
     *
     * @param numberOfPlayers
     */
    public Game(int numberOfPlayers) {
        p1score = 0;
        p2score = 0;
        p3score = 0;
        p4score = 0;
        this.numberOfPlayers = numberOfPlayers;
        // Create paddles and set color according to the number of players
        // left
        player1 = new Paddle(20, GameCanvas.playAreaHeight / 2 - Paddle.height / 2, false);
        // right
        player2 = new Paddle(GameCanvas.playAreaWidth - 30, GameCanvas.playAreaHeight / 2 - Paddle.height / 2, false);
        // top
        player3 = new Paddle(GameCanvas.playAreaWidth / 2 - Paddle.height / 2, 20, true);
        // bottom
        player4 = new Paddle(GameCanvas.playAreaWidth / 2 - Paddle.height / 2, GameCanvas.playAreaHeight - 30, true);

        // Set colors according to the number of players
        if (numberOfPlayers == 1) {
            player1.setColor(Color.RED);
            player2.setColor(Color.RED);
            player3.setColor(Color.RED);
            player4.setColor(Color.RED);
        }
        if (numberOfPlayers == 2) {
            player1.setColor(Color.RED);
            player2.setColor(Color.BLUE);
            player3.setColor(Color.RED);
            player4.setColor(Color.BLUE);
        }
        if (numberOfPlayers == 4) {
            player1.setColor(Color.RED);
            player2.setColor(Color.GREEN);
            player3.setColor(Color.BLUE);
            player4.setColor(Color.YELLOW);
        }
        // Create ball
        ball = new Ball(GameCanvas.playAreaWidth / 2, GameCanvas.playAreaHeight / 2);
    }

    /**
     * Reset game when starting a new one
     */
    public void resetGame(int numberOfPlayers) {
        p1score = 0;
        p2score = 0;
        p3score = 0;
        p4score = 0;
        this.numberOfPlayers = numberOfPlayers;
        player1.setXPosition(20);
        player1.setYPosition(GameCanvas.playAreaHeight / 2 - Paddle.height / 2);
        player2.setXPosition(GameCanvas.playAreaWidth - 30);
        player2.setYPosition(GameCanvas.playAreaHeight / 2 - Paddle.height / 2);
        player3.setXPosition(GameCanvas.playAreaWidth / 2 - Paddle.height / 2);
        player3.setYPosition(20);
        player4.setXPosition(GameCanvas.playAreaWidth / 2 - Paddle.height / 2);
        player4.setYPosition(GameCanvas.playAreaHeight - 30);
        ball.setXPosition(GameCanvas.playAreaWidth / 2);
        ball.setYPosition(GameCanvas.playAreaHeight / 2);
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
        filter();
        countScore(numberOfPlayers);
        ball.update();
    }

    /**
     * Add some filtering to the paddleCollisionCheck to shorten the duration
     * into one frame. For some reason paddleCollisionCheck is active for 3 frames.
     */
    public void filter() {
        if (!oldpaddleCollisionCheck && paddleCollisionCheck) {
            filteredPaddleCollisionCheck = true;
        } else {
            filteredPaddleCollisionCheck = false;
        }
        oldpaddleCollisionCheck = paddleCollisionCheck;
    }

    /**
     * Check scores
     *
     * @param numberOfPlayers
     */
    public void countScore(int numberOfPlayers) {
        switch (numberOfPlayers) {
            case 1:
                if (filteredPaddleCollisionCheck) {
                    p1score++;
                }
                if (leftWallCollision || topWallCollision || rightWallCollision || bottomWallCollision) {
                    p1score--;
                }
                break;
            case 2:
                if (leftWallCollision || topWallCollision) {
                    p2score++;
                }
                if (rightWallCollision || bottomWallCollision) {
                    p1score--;
                }
                break;
            case 4:
                if (leftWallCollision) {
                    p2score++;
                    p3score++;
                    p4score++;
                }
                if (rightWallCollision) {
                    p1score++;
                    p2score++;
                    p4score++;
                }
                if (topWallCollision) {
                    p1score++;
                    p3score++;
                    p4score++;
                }
                if (bottomWallCollision) {
                    p1score++;
                    p2score++;
                    p3score++;
                }
                break;
            default:
                break;
        }
    }

    /**
     * Check for wall collisions.
     */
    public void wallCollision() {
        leftWallCollision = false;
        rightWallCollision = false;
        topWallCollision = false;
        bottomWallCollision = false;
        // Score against player1 (left side)
        if (ball.getXPosition() <= 0) {
            ball.setXPosition(GameCanvas.playAreaWidth / 2);
            ball.setYPosition(GameCanvas.playAreaHeight / 2);
            leftWallCollision = true;
            // Score against player 3 (right side)
        } else if (ball.getXPosition() + ballSize >= GameCanvas.playAreaWidth) {
            ball.setXPosition(GameCanvas.playAreaWidth / 2);
            ball.setYPosition(GameCanvas.playAreaHeight / 2);
            rightWallCollision = true;
        }
        // Score against player 2 (top side)
        if (ball.getYPosition() <= 0) {
            ball.setXPosition(GameCanvas.playAreaWidth / 2);
            ball.setYPosition(GameCanvas.playAreaHeight / 2);
            topWallCollision = true;
            // Score against player 4 (bottom side)
        } else if (ball.getYPosition() + ballSize >= GameCanvas.playAreaHeight) {
            ball.setXPosition(GameCanvas.playAreaWidth / 2);
            ball.setYPosition(GameCanvas.playAreaHeight / 2);
            bottomWallCollision = true;
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
