package game;

import paddleControl.PaddleControl;
import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class GameCanvas extends Canvas implements Runnable {

    public static PaddleControl pc;
    JFrame frame;
    public final int screenWidth = 600;
    public final int screenHeight = screenWidth;
    public final Dimension gameScreenSize = new Dimension(screenWidth, screenHeight);
    public final String gameTitle = "PongPong";

    public static int playAreaWidth;
    public static int playAreaHeight;

    static boolean gameRunning = false;

    Game game;

    @Override
    public void run() {
        while (gameRunning) {
            //update();
            game.update();
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
        game.player1.render(g);
        game.player2.render(g);
        game.player3.render(g);
        game.player4.render(g);
        // Draw ball
        game.ball.render(g);

        g.dispose();
        bs.show();
    }

}
