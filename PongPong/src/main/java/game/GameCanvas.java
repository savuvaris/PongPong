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

    Game game;

    @Override
    public void run() {
        while (gameRunning) {
            game.update();
            render();
            // Check for score, i.e. ball collided with borders
            if (game.wallCollisionCheck) {
                gameRunning = false;
                gamePaused = true;
            }
            // Control framerate
            try {
                Thread.sleep(10);
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
                run();
            }
            // Control framerate
            try {
                Thread.sleep(10);
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
    }

    /**
     * Create the play area
     */
    public GameCanvas() {

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

        playAreaWidth = getWidth();
        playAreaHeight = getHeight();
        game = new Game();
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

        // Draw scores
        scores(StartMenu.numberOfPlayers);

        //
        g.dispose();
        bs.show();
    }

    public void scores(int numberOfPlayers) {
        switch (numberOfPlayers) {
            case 1:
                // Top
                g2d.drawString("Player 1: " + game.p1score, playAreaWidth / 2 - Paddle.height / 2, 10);
                // Bottom
                g2d.drawString("Player 1: " + game.p1score, playAreaWidth / 2 - Paddle.height / 2, playAreaHeight - 5);
                // Rotate left side text
                at = new AffineTransform();
                at.setToRotation(Math.PI / 2.0);
                g2d.setTransform(at);
                g2d.drawString("Player 1: " + game.p1score, playAreaWidth / 2 - Paddle.height / 2, -5);
                // Rotate right side text
                at.setToRotation(Math.PI / 2.0);
                g2d.setTransform(at);
                g2d.drawString("Player 1: " + game.p1score, playAreaWidth / 2 - Paddle.height / 2, -playAreaWidth + 10);
                break;
            case 2:
                // Top
                g2d.drawString("Player 1: " + game.p1score, playAreaWidth / 2 - Paddle.height / 2, 10);
                // Bottom
                g2d.drawString("Player 2: " + game.p2score, playAreaWidth / 2 - Paddle.height / 2, playAreaHeight - 5);
                // Rotate left side text
                at = new AffineTransform();
                at.setToRotation(Math.PI / 2.0);
                g2d.setTransform(at);
                g2d.drawString("Player 1: " + game.p1score, playAreaWidth / 2 - Paddle.height / 2, -5);
                // Rotate right side text
                at.setToRotation(Math.PI / 2.0);
                g2d.setTransform(at);
                g2d.drawString("Player 2: " + game.p2score, playAreaWidth / 2 - Paddle.height / 2, -playAreaWidth + 10);
                break;
            case 4:
                // Top
                g2d.drawString("Player 2: " + game.p2score, playAreaWidth / 2 - Paddle.height / 2, 10);
                // Bottom
                g2d.drawString("Player 4: " + game.p4score, playAreaWidth / 2 - Paddle.height / 2, playAreaHeight - 5);
                // Rotate left side text
                at = new AffineTransform();
                at.setToRotation(Math.PI / 2.0);
                g2d.setTransform(at);
                g2d.drawString("Player 1: " + game.p1score, playAreaWidth / 2 - Paddle.height / 2, -5);
                // Rotate right side text
                at.setToRotation(Math.PI / 2.0);
                g2d.setTransform(at);
                g2d.drawString("Player 3: " + game.p3score, playAreaWidth / 2 - Paddle.height / 2, -playAreaWidth + 10);
                break;
            default:
                break;
        }

    }

}
