package game;

import paddleControl.PaddleControl;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import paddle.Paddle;
import startMenu.StartMenu;

/**
 *
 * @author Tomi
 */
public class GameCanvas extends Canvas implements Runnable {

    /**
     * PaddleControl moves the paddles
     */
    public static PaddleControl pc;
    JFrame frame;

    public final int screenWidth = 600;
    public final int screenHeight = screenWidth;
    public final Dimension gameScreenSize = new Dimension(screenWidth, screenHeight);
    public final String gameTitle = "PongPong";
    public static int playAreaWidth;
    public static int playAreaHeight;

    public Graphics2D g2d;
    AffineTransform at;

    static boolean gameRunning = false;
    static boolean gamePaused = false;
    static boolean gameEnd = false;

    // Game length in seconds
    int gameLength = 5;
    int timeCount = 0;
    int timeLeft;

    Game game;

    @Override
    public void run() {
        while (gameRunning) {
            game.update();
            render();
            // Ball collided with borders, pause.
            if (game.leftWallCollision || game.topWallCollision || game.rightWallCollision || game.bottomWallCollision) {
                gameRunning = false;
                gamePaused = true;
                gameEnd = false;
            }
            // Time is up, end game
            if (timeLeft <= 0) {
                gameRunning = false;
                gamePaused = false;
                gameEnd = true;
            }
            // Control framerate
            try {
                Thread.sleep(10);
                timeCount++;
                timeLeft = gameLength - timeCount / 100;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        while (gamePaused) {
            render();
            // Check for space button
            if (pc.launch) {
                Game.ball.launch();
                gameRunning = true;
                gamePaused = false;
                gameEnd = false;
                run();
            }
            // Control framerate
            try {
                Thread.sleep(10);
                timeLeft = gameLength - timeCount / 100;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        while (gameEnd) {
            render();
            // Check for space button
            if (pc.launch) {
                //Game.ball.launch();
                timeLeft = gameLength;
                timeCount = 0;
                gameRunning = true;
                gamePaused = false;
                gameEnd = false;
                game.resetGame(StartMenu.numberOfPlayers);
                run();
            }
            // Control framerate
            try {
                Thread.sleep(10);
                timeLeft = gameLength - timeCount / 100;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Start the game in paused mode
     */
    public synchronized void start() {
        gamePaused = true;
        Game.ball.launch();
        new Thread(this).start();
    }

    /**
     * Stop the game
     */
    public static synchronized void stop() {
        gameRunning = false;
        System.exit(0);
    }

    /**
     * Create the play area
     */
    public GameCanvas() {
        frame = new JFrame();

        gameRunning = false;
        gamePaused = true;
        gameEnd = false;
        timeLeft = gameLength;

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

        playAreaWidth = getWidth();
        playAreaHeight = getHeight();
        game = new Game(StartMenu.numberOfPlayers);
    }

    /**
     * Draw objects
     */
    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g2d = (Graphics2D) g;

        // Draw background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw players
        game.player1.render(g);
        game.player2.render(g);
        game.player3.render(g);
        game.player4.render(g);

        // Draw ball
        game.ball.render(g);

        // Draw drawInfo and other text
        drawInfo(StartMenu.numberOfPlayers);

        //
        g.dispose();
        bs.show();
    }

    public void drawInfo(int numberOfPlayers) {
        if (gamePaused) {
            g2d.drawString("Press SPACE to launch the ball.", playAreaWidth / 2 - 75, playAreaHeight / 2);
        }
        if (gameRunning) {
            g2d.drawString("Time left: " + timeLeft, playAreaWidth / 2 - 30, playAreaHeight / 2);
        }
        if (gameEnd) {
            g2d.drawString("Time is up! Press SPACE to continue.", playAreaWidth / 2 - 85, playAreaHeight / 2);
        }
        switch (numberOfPlayers) {
            case 1:
                // Top
                g2d.drawString("P1 Score: " + game.p1score + "  Controls: A and D", playAreaWidth / 2 - Paddle.height / 2 - 40, 10);
                // Bottom
                g2d.drawString("P1 Score: " + game.p1score + "  Controls: J and L", playAreaWidth / 2 - Paddle.height / 2 - 40, playAreaHeight - 5);
                // Rotate left side text
                at = new AffineTransform();
                at.setToRotation(Math.PI / 2.0);
                g2d.setTransform(at);
                g2d.drawString("P1 Score: " + game.p1score + "  Controls: W and S", playAreaWidth / 2 - Paddle.height / 2 - 40, -5);
                // Rotate right side text
                at.setToRotation(Math.PI / 2.0);
                g2d.setTransform(at);
                g2d.drawString("P1 Score: " + game.p1score + "  Controls: I and K", playAreaWidth / 2 - Paddle.height / 2 - 40, -playAreaWidth + 10);
                break;
            case 2:
                // Top
                g2d.drawString("P1 Score: " + game.p1score + "  Controls: A and D", playAreaWidth / 2 - Paddle.height / 2 - 40, 10);
                // Bottom
                g2d.drawString("P2 Score: " + game.p2score + "  Controls: J and L", playAreaWidth / 2 - Paddle.height / 2 - 40, playAreaHeight - 5);
                // Rotate left side text
                at = new AffineTransform();
                at.setToRotation(Math.PI / 2.0);
                g2d.setTransform(at);
                g2d.drawString("P1 Score: " + game.p1score + "  Controls: W and S", playAreaWidth / 2 - Paddle.height / 2 - 40, -5);
                // Rotate right side text
                at.setToRotation(Math.PI / 2.0);
                g2d.setTransform(at);
                g2d.drawString("P2 Score: " + game.p2score + "  Controls: I and K", playAreaWidth / 2 - Paddle.height / 2 - 40, -playAreaWidth + 10);
                break;
            case 4:
                // Top
                g2d.drawString("P2 Score: " + game.p2score + "  Controls: C and V", playAreaWidth / 2 - Paddle.height / 2 - 40, 10);
                // Bottom
                g2d.drawString("P4 Score: " + game.p4score + "  Controls: N and M", playAreaWidth / 2 - Paddle.height / 2 - 40, playAreaHeight - 5);
                // Rotate left side text
                at = new AffineTransform();
                at.setToRotation(Math.PI / 2.0);
                g2d.setTransform(at);
                g2d.drawString("P1 Score: " + game.p1score + "  Controls: W and S", playAreaWidth / 2 - Paddle.height / 2 - 40, -5);
                // Rotate right side text
                at.setToRotation(Math.PI / 2.0);
                g2d.setTransform(at);
                g2d.drawString("P3 Score: " + game.p3score + "  Controls: I and K", playAreaWidth / 2 - Paddle.height / 2 - 40, -playAreaWidth + 10);
                break;
            default:
                break;
        }

    }

}
