package PaddleControl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import pongpong.PongPong;

public class PaddleControl implements KeyListener {

    public int keyCode;

    public PaddleControl(PongPong game) {
        game.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) {
            PongPong.player1.goUp(true);
            PongPong.player3.goUp(true);
        }
        if (keyCode == KeyEvent.VK_S) {
            PongPong.player1.goDown(true);
            PongPong.player3.goDown(true);
        }
        if (keyCode == KeyEvent.VK_A) {
            PongPong.player1.goLeft(true);
            PongPong.player3.goLeft(true);
        }
        if (keyCode == KeyEvent.VK_D) {
            PongPong.player1.goRight(true);
            PongPong.player3.goRight(true);
        }

        if (keyCode == KeyEvent.VK_I) {
            PongPong.player2.goUp(true);
            PongPong.player4.goUp(true);
        }
        if (keyCode == KeyEvent.VK_K) {
            PongPong.player2.goDown(true);
            PongPong.player4.goDown(true);
        }
        if (keyCode == KeyEvent.VK_J) {
            PongPong.player2.goLeft(true);
            PongPong.player4.goLeft(true);
        }
        if (keyCode == KeyEvent.VK_L) {
            PongPong.player2.goRight(true);
            PongPong.player4.goRight(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) {
            PongPong.player1.goUp(false);
            PongPong.player3.goUp(false);
        }
        if (keyCode == KeyEvent.VK_S) {
            PongPong.player1.goDown(false);
            PongPong.player3.goDown(false);
        }
        if (keyCode == KeyEvent.VK_A) {
            PongPong.player1.goLeft(false);
            PongPong.player3.goLeft(false);
        }
        if (keyCode == KeyEvent.VK_D) {
            PongPong.player1.goRight(false);
            PongPong.player3.goRight(false);
        }

        if (keyCode == KeyEvent.VK_I) {
            PongPong.player2.goUp(false);
            PongPong.player4.goUp(false);
        }
        if (keyCode == KeyEvent.VK_K) {
            PongPong.player2.goDown(false);
            PongPong.player4.goDown(false);
        }
        if (keyCode == KeyEvent.VK_J) {
            PongPong.player2.goLeft(false);
            PongPong.player4.goLeft(false);
        }
        if (keyCode == KeyEvent.VK_L) {
            PongPong.player2.goRight(false);
            PongPong.player4.goRight(false);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}

