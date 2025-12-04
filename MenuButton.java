//Tony Hsu, Vihaan Jani

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuButton extends JButton {
    public MenuButton(String text, int y, ActionListener buttonListener) {
        super(text);
        setBounds(175, y, 200, 50);
        setOpaque(true);
        setFont(new Font("SansSerif", Font.BOLD, 20));
        setBackground(new Color(0, 150, 0));
        setForeground(Color.WHITE);
        setFocusable(false);
        setBorder(null);
        addActionListener(buttonListener);
    }
}
