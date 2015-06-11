package startMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import game.GameCanvas;

public class StartMenu extends JFrame {

    public static int numberOfPlayers;

    int screenWidth = 250;
    int screenHeight = 250;
    int buttonWidth = 150;
    int buttonHeight = 40;

    JButton onePlayer, twoPlayer, fourPlayer;

    public StartMenu() {
        getContentPane().setLayout(null);

        // Add buttons
        onePlayer = new JButton("One Player");
        getContentPane().add(onePlayer);
        onePlayer.setBounds((screenWidth - buttonWidth) / 2, 5 + buttonHeight, buttonWidth, buttonHeight);

        twoPlayer = new JButton("Two Players");
        getContentPane().add(twoPlayer);
        twoPlayer.setBounds((screenWidth - buttonWidth) / 2, 2 * (5 + buttonHeight), buttonWidth, buttonHeight);

        fourPlayer = new JButton("Four Players");
        getContentPane().add(fourPlayer);
        fourPlayer.setBounds((screenWidth - buttonWidth) / 2, 3 * (5 + buttonHeight), buttonWidth, buttonHeight);

        // 
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(screenWidth, screenHeight);
        setTitle("PongPong");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Actionlisteners
        onePlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                numberOfPlayers = 1;
                GameCanvas pongpong = new GameCanvas();
                pongpong.start();
            }
        });

        twoPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                numberOfPlayers = 2;
                GameCanvas pongpong = new GameCanvas();
                pongpong.start();
            }
        });

        fourPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                numberOfPlayers = 4;
                GameCanvas pongpong = new GameCanvas();
                pongpong.start();
            }
        });

    }

    public static void main(String[] args) {
        new StartMenu();
    }
}
