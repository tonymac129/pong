//Tony Hsu, Vihaan Jani

import javax.swing.JFrame;

public class Pong {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Unit2, Project: Ping-Pong");
        frame.setSize(550, 550);
        frame.setLocation(150, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new MenuPanel(frame)); // New panel
        frame.setVisible(true);
    }
}