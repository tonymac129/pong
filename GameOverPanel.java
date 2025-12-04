//Tony Hsu, Vihaan Jani

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameOverPanel extends JPanel {
    JFrame frame;
    JFrame newFrame;
    private final String text;

    public GameOverPanel(JFrame frame, String text) {
        this.text = text;
        this.frame = frame;
        setLayout(null);
        JButton backButton = new MenuButton("Back", 300, new ButtonListener());
        add(backButton);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 550, 550);
        g.setFont(new Font("Sans Serif", Font.BOLD, 40));
        g.setColor(Color.GREEN);
        g.drawString(text, 140, 100);
        g.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        g.setColor(Color.GRAY);
        g.drawString(
                "Congrats! Up for another game?",
                150, 150);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            if (newFrame == null) {
                newFrame = new JFrame("Content Frame");
                newFrame.setVisible(true);
                newFrame.setLocation(150, 150);
                newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                newFrame.setContentPane(new MenuPanel(frame));
                newFrame.setSize(550, 550);
            } else {
                newFrame.setVisible(true);
            }
        }
    }
}