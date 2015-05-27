package pongpong;

import PaddleControl.PaddleControl;
import Paddle.Paddle;
import Ball.Ball;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;
import javax.swing.JFrame;

public class PongPong extends Canvas implements Runnable {

    public static Paddle player1;
    public static Paddle player2;
    public static Paddle player3;
    public static Paddle player4;
    public static Ball ball;
    PaddleControl pc;
    JFrame frame;
    public final int screenWidth = 600;
    public final int screenHeight = screenWidth;
    public final Dimension gameScreenSize = new Dimension(screenWidth, screenHeight);
    public final String gameTitle = "PongPong";
    int ballSize = 16;
    double ballSpeed = 1.5;
    Random randomGen = new Random();
    static boolean gameRunning = false;

    public void run() {
        while (gameRunning) {
            update();
            render();
            // Control framerate
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void start() {
        gameRunning = true;
        new Thread(this).start();
    }

    public static synchronized void stop() {
        gameRunning = false;
        System.exit(0);
    }

    public PongPong() {
        frame = new JFrame();

        setMinimumSize(gameScreenSize);
        setPreferredSize(gameScreenSize);
        setMaximumSize(gameScreenSize);

        frame.add(this, BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle(gameTitle);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        pc = new PaddleControl(this);

        player1 = new Paddle(10, 60, false);
        player2 = new Paddle(getWidth() - 20, 60, false);
        player3 = new Paddle(60, 10, true);
        player4 = new Paddle(60, getHeight() - 20, true);
        ball = new Ball(getWidth() / 2, getHeight() / 2);
    }

    public void update() {
        player1.update(this);
        player2.update(this);
        player3.update(this);
        player4.update(this);
        collision();
        ball.update(this);
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        // Draw background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw players
        player1.render(g);
        player2.render(g);
        player3.render(g);
        player4.render(g);
        // Draw ball
        ball.render(g);

        g.dispose();
        bs.show();
    }

    // Collisions
    public void collision() {

        // Check wall collisions
        // After collision 
        // - return to center
        // - wait for space button (not done)
        // - start in random direction (not done)
        if (ball.getXPosition() <= 0) {
            ball.setXPosition(getWidth() / 2);
            ball.setYPosition(getHeight() / 2);
        } else if (ball.getXPosition() + ballSize >= getWidth()) {
            ball.setXPosition(getWidth() / 2);
            ball.setYPosition(getHeight() / 2);
        }
        if (ball.getYPosition() <= 0) {
            ball.setXPosition(getWidth() / 2);
            ball.setYPosition(getHeight() / 2);
        } else if (ball.getYPosition() + ballSize >= getHeight()) {
            ball.setXPosition(getWidth() / 2);
            ball.setYPosition(getHeight() / 2);
        }

        // Check paddle collisions
        // Add some random behavior to the ball as it bounces from the paddle
        // to prevent a stable situation.
        double randomNum = (((randomGen.nextDouble()) * 2) - 1) / 5;
        if (ball.boundingBox.intersects(player1.boundingBox)) {
            ball.setXSpeed(ballSpeed + randomNum);
         }
        if (ball.boundingBox.intersects(player2.boundingBox)) {
            ball.setXSpeed(-ballSpeed + randomNum);
        }
        if (ball.boundingBox.intersects(player3.boundingBox)) {
            ball.setYSpeed(ballSpeed + randomNum);
        }
        if (ball.boundingBox.intersects(player4.boundingBox)) {
            ball.setYSpeed(-ballSpeed + randomNum);
        }
    }

    // Start
    public static void main(String[] args) {
        PongPong game = new PongPong();
        game.start();
    }
}
