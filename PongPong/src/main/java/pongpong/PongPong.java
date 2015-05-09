
package pongpong;

import javax.swing.JFrame;

public class PongPong extends JFrame {

    public final int HEIGHT = 800;
    public final int WIDTH = 800;
    
    public PongPong() {
        //add(new Board());
        setTitle("PongPong");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setIgnoreRepaint(true);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        
        // Missing: Game start page
        // Missing: Game end page
        new PongPong();
    }

}
