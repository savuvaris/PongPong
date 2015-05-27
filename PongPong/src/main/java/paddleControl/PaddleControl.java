package paddleControl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import game.Game;
import game.GameCanvas;

public class PaddleControl implements KeyListener {

    public int keyCode;

    public PaddleControl(GameCanvas game) {
        game.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyCode = e.getKeyCode();

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

    @Override
    public void keyReleased(KeyEvent e) {
        keyCode = e.getKeyCode();

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

    @Override
    public void keyTyped(KeyEvent e) {

    }

}

