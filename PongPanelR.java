//Tony Hsu, Vihaan Jani

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public class PongPanelR extends JPanel {
    JFrame frame;
    JFrame newFrame;
    private static final int FRAME = 550;
    private static final Color BACKGROUND = Color.BLACK;

    private BufferedImage myImage;
    private Graphics myBuffer;
    private Ball ball;
    private Paddle paddle1;
    private Paddle paddle2;
    private Timer timer;
    private int increment = 50;
    private int score1 = 0;
    private int score2 = 0;
    private double speed = 1;

    public PongPanelR(JFrame frame) {
        this.frame = frame;
        myImage = new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
        myBuffer = myImage.getGraphics();
        paddle1 = new Paddle(0, Color.RED);
        paddle2 = new Paddle(540, Color.BLUE);
        addKeyListener(new Key());
        ball = new Ball();
        setFocusable(true);
        timer = new Timer(5, new Listener());
        timer.start();
    }

    public void paintComponent(Graphics g) {
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }

    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            myBuffer.setColor(BACKGROUND);
            myBuffer.fillRect(0, 0, FRAME, FRAME);
            ball.move(FRAME, FRAME, speed);
            checkOut(ball);
            ball.draw(myBuffer);
            speed = BumperCollision.collide(paddle1, ball, speed);
            speed = BumperCollision.collide(paddle2, ball, speed);
            paddle1.draw(myBuffer);
            paddle2.draw(myBuffer);
            myBuffer.setColor(Color.RED);
            myBuffer.setFont(new Font("Monospaced", Font.BOLD, 30));
            myBuffer.drawString(score1 + "", 138, 50);
            myBuffer.setColor(Color.BLUE);
            myBuffer.drawString(score2 + "", 413, 50);
            myBuffer.setColor(Color.WHITE);
            for (int i = 0; i < 550; i += 25) {
                myBuffer.drawLine(275, i, 275, i + 20);
            }
            repaint();
        }
    }

    private class Key extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (paddle2.getY() <= 0) {
                    paddle2.setY(0);
                } else {
                    paddle2.setY(paddle2.getY() - increment);
                }
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (paddle2.getY() + 100 >= FRAME) {
                    paddle2.setY(FRAME - 100);
                } else {
                    paddle2.setY(paddle2.getY() + increment);
                }
            } else if (e.getKeyCode() == KeyEvent.VK_W) {
                if (paddle1.getY() <= 0) {
                    paddle1.setY(0);
                } else {
                    paddle1.setY(paddle1.getY() - increment);
                }
            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                if (paddle1.getY() + 100 >= FRAME) {
                    paddle1.setY(FRAME - 100);
                } else {
                    paddle1.setY(paddle1.getY() + increment);
                }
            }
        }
    }

    private void checkOut(Ball b) {
        if (b.getX() <= b.getRadius()) {
            ball.setX(275);
            ball.setdx(Math.random() * 4 - 2);
            ball.setdy(Math.random() * 4 - 2);
            score2++;
            speed = 1;
            if (score2 == 10) {
                frame.setVisible(false);
                newFrame = new JFrame("Content Frame");
                newFrame.setVisible(true);
                newFrame.setLocation(150, 150);
                newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                newFrame.setContentPane(new GameOverPanel(frame, "Player 2 won!"));
                newFrame.setSize(550, 550);
            }
        } else if (b.getX() >= FRAME - b.getRadius()) {
            ball.setX(275);
            ball.setdx(Math.random() * 4 - 2);
            ball.setdy(Math.random() * 4 - 2);
            score1++;
            speed = 1;
            if (score1 == 10) {
                frame.setVisible(false);
                newFrame = new JFrame("Content Frame");
                newFrame.setVisible(true);
                newFrame.setLocation(150, 150);
                newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                newFrame.setContentPane(new GameOverPanel(frame, "Player 1 won!"));
                newFrame.setSize(550, 550);
            }
        }
    }
}