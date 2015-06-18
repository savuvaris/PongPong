package paddleControl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import game.Game;
import game.GameCanvas;
import startMenu.StartMenu;

/**
 *
 * @author Tomi
 * Class controls the paddles. Different controls in 1p, 2p and 4p mode
 */
public class PaddleControl implements KeyListener {

    public int keyCode;

    /**
     *  Start the ball moving
     */
    public boolean launch;

    /**
     *
     * @param game
     */
    public PaddleControl(GameCanvas game) {
        game.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_SPACE) {
            launch = true;
        }

        // 1P or 2P controls
        if ((StartMenu.numberOfPlayers == 1) || (StartMenu.numberOfPlayers == 2)) {
            if (keyCode == KeyEvent.VK_W) {
                Game.player1.goUp(true);
                Game.player3.goUp(true);
            }
            if (keyCode == KeyEvent.VK_S) {
                Game.player1.goDown(true);
                Game.player3.goDown(true);
            }
            if (keyCode == KeyEvent.VK_A) {
                Game.player1.goLeft(true);
                Game.player3.goLeft(true);
            }
            if (keyCode == KeyEvent.VK_D) {
                Game.player1.goRight(true);
                Game.player3.goRight(true);
            }

            if (keyCode == KeyEvent.VK_I) {
                Game.player2.goUp(true);
                Game.player4.goUp(true);
            }
            if (keyCode == KeyEvent.VK_K) {
                Game.player2.goDown(true);
                Game.player4.goDown(true);
            }
            if (keyCode == KeyEvent.VK_J) {
                Game.player2.goLeft(true);
                Game.player4.goLeft(true);
            }
            if (keyCode == KeyEvent.VK_L) {
                Game.player2.goRight(true);
                Game.player4.goRight(true);
            }
        }
        // 4P controls
        if (StartMenu.numberOfPlayers == 4) {
            if (keyCode == KeyEvent.VK_W) {
                Game.player1.goUp(true);
                Game.player3.goUp(true);
            }
            if (keyCode == KeyEvent.VK_S) {
                Game.player1.goDown(true);
                Game.player3.goDown(true);
            }
            if (keyCode == KeyEvent.VK_C) {
                Game.player1.goLeft(true);
                Game.player3.goLeft(true);
            }
            if (keyCode == KeyEvent.VK_V) {
                Game.player1.goRight(true);
                Game.player3.goRight(true);
            }

            if (keyCode == KeyEvent.VK_I) {
                Game.player2.goUp(true);
                Game.player4.goUp(true);
            }
            if (keyCode == KeyEvent.VK_K) {
                Game.player2.goDown(true);
                Game.player4.goDown(true);
            }
            if (keyCode == KeyEvent.VK_N) {
                Game.player2.goLeft(true);
                Game.player4.goLeft(true);
            }
            if (keyCode == KeyEvent.VK_M) {
                Game.player2.goRight(true);
                Game.player4.goRight(true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_SPACE) {
            launch = false;
        }

        // 1P or 2P controls
        if ((StartMenu.numberOfPlayers == 1) || (StartMenu.numberOfPlayers == 2)) {
            if (keyCode == KeyEvent.VK_W) {
                Game.player1.goUp(false);
                Game.player3.goUp(false);
            }
            if (keyCode == KeyEvent.VK_S) {
                Game.player1.goDown(false);
                Game.player3.goDown(false);
            }
            if (keyCode == KeyEvent.VK_A) {
                Game.player1.goLeft(false);
                Game.player3.goLeft(false);
            }
            if (keyCode == KeyEvent.VK_D) {
                Game.player1.goRight(false);
                Game.player3.goRight(false);
            }

            if (keyCode == KeyEvent.VK_I) {
                Game.player2.goUp(false);
                Game.player4.goUp(false);
            }
            if (keyCode == KeyEvent.VK_K) {
                Game.player2.goDown(false);
                Game.player4.goDown(false);
            }
            if (keyCode == KeyEvent.VK_J) {
                Game.player2.goLeft(false);
                Game.player4.goLeft(false);
            }
            if (keyCode == KeyEvent.VK_L) {
                Game.player2.goRight(false);
                Game.player4.goRight(false);
            }
        }

        // 4P controls
        if (StartMenu.numberOfPlayers == 4) {
            if (keyCode == KeyEvent.VK_W) {
                Game.player1.goUp(false);
                Game.player3.goUp(false);
            }
            if (keyCode == KeyEvent.VK_S) {
                Game.player1.goDown(false);
                Game.player3.goDown(false);
            }
            if (keyCode == KeyEvent.VK_C) {
                Game.player1.goLeft(false);
                Game.player3.goLeft(false);
            }
            if (keyCode == KeyEvent.VK_V) {
                Game.player1.goRight(false);
                Game.player3.goRight(false);
            }

            if (keyCode == KeyEvent.VK_I) {
                Game.player2.goUp(false);
                Game.player4.goUp(false);
            }
            if (keyCode == KeyEvent.VK_K) {
                Game.player2.goDown(false);
                Game.player4.goDown(false);
            }
            if (keyCode == KeyEvent.VK_N) {
                Game.player2.goLeft(false);
                Game.player4.goLeft(false);
            }
            if (keyCode == KeyEvent.VK_M) {
                Game.player2.goRight(false);
                Game.player4.goRight(false);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
