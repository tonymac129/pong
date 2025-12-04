//Tony Hsu, Vihaan Jani

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuPanel extends JPanel {
    JFrame frame;
    JFrame newFrame;

    public MenuPanel(JFrame frame) {
        this.frame = frame;
        JButton button1 = new MenuButton("Normal Mode", 150, new ButtonListener());
        JButton button2 = new MenuButton("Chaos Mode", 230, new ButtonListener2());
        JButton button3 = new MenuButton("About Game", 310, new ButtonListener3());
        add(button1);
        add(button2);
        add(button3);
        setLayout(null);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 550, 550);
        g.setFont(new Font("Sans Serif", Font.BOLD, 40));
        g.setColor(Color.GREEN);
        g.drawString("Two Player Pong", 120, 100);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            if (newFrame == null) {
                newFrame = new JFrame("Content Frame");
                newFrame.setVisible(true);
                newFrame.setLocation(150, 150);
                newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                newFrame.setContentPane(new PongPanelR(frame));
                newFrame.setSize(550, 550);
            } else {
                newFrame.setVisible(true);
            }
        }
    }

    private class ButtonListener2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            if (newFrame == null) {
                newFrame = new JFrame("Content Frame");
                newFrame.setVisible(true);
                newFrame.setLocation(150, 150);
                newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                newFrame.setContentPane(new PongPanelC(frame));
                newFrame.setSize(550, 550);
            } else {
                newFrame.setVisible(true);
            }
        }
    }

    private class ButtonListener3 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            if (newFrame == null) {
                newFrame = new JFrame("Content Frame");
                newFrame.setVisible(true);
                newFrame.setLocation(150, 150);
                newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                newFrame.setContentPane(new AboutPanel(frame));
                newFrame.setSize(550, 550);
            } else {
                newFrame.setVisible(true);
            }
        }
    }
}