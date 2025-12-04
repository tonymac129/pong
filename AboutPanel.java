//Tony Hsu, Vihaan Jani

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AboutPanel extends JPanel {
    JFrame frame;
    JFrame newFrame;

    public AboutPanel(JFrame frame) {
        this.frame = frame;
        setLayout(null);
        JButton backButton = new MenuButton("Back", 400, new ButtonListener());
        add(backButton);
        JLabel textLabel = new JLabel(
                "<html><div style=\"text-align: center;color:white;font-size:14px\">This is a simple pong game with 2 different game modes,<br>"
                        + "regular mode where the ball speeds up after every hit, and chaos mode where a new ball is added to the game every 5 seconds. Whoever scores 10 points first wins the game.<br>"
                        + "Made by Tony Hsu and Vihaan Jani, December 2025</div></html>");
        textLabel.setBounds(25, 150, 500, 200);
        add(textLabel);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 550, 550);
        g.setFont(new Font("Sans Serif", Font.BOLD, 40));
        g.setColor(Color.GREEN);
        g.drawString("About Game", 140, 100);
        g.setFont(new Font("Sans Serif", Font.PLAIN, 18));
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