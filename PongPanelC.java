//Tony Hsu, Vihaan Jani

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class PongPanelC extends JPanel {
    JFrame frame;
    JFrame newFrame;
    private static final int FRAME = 550;
    private static final Color BACKGROUND = Color.BLACK;

    private BufferedImage myImage;
    private Graphics myBuffer;
    private List<Ball> balls = new ArrayList<>();
    private Ball firstBall = new Ball();
    private Paddle paddle1;
    private Paddle paddle2;
    private Timer timer;
    private Timer timer2;
    private int increment = 50;
    private int score1 = 0;
    private int score2 = 0;

    public PongPanelC(JFrame frame) {
        this.frame = frame;
        myImage = new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
        myBuffer = myImage.getGraphics();
        paddle1 = new Paddle(0, Color.RED);
        paddle2 = new Paddle(540, Color.BLUE);
        addKeyListener(new Key());
        balls.add(firstBall);
        setFocusable(true);
        timer = new Timer(5, new Listener());
        timer.start();
        timer2 = new Timer(5000, new Listener2());
        timer2.start();
    }

    public void paintComponent(Graphics g) {
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }

    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            myBuffer.setColor(BACKGROUND);
            myBuffer.fillRect(0, 0, FRAME, FRAME);
            for (int i = 0; i < balls.size(); i++) {
                Ball ball = balls.get(i);
                ball.move(FRAME, FRAME, 0);
                checkOut(ball);
                ball.draw(myBuffer);
                BumperCollision.collide(paddle1, ball, 0);
                BumperCollision.collide(paddle2, ball, 0);
            }
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

    private class Listener2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Ball ball = new Ball();
            ball.setX(275);
            ball.setY(275);
            balls.add(ball);
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
            balls = balls.subList(0, 1);
            Ball newBall = balls.get(0);
            newBall.setX(275);
            newBall.setdx(Math.random() * 4 - 2);
            newBall.setdy(Math.random() * 4 - 2);
            score2++;
            timer2.stop();
            timer2.start(); // resets ball timer
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
            balls = balls.subList(0, 1);
            Ball newBall = balls.get(0);
            newBall.setX(275);
            newBall.setdx(Math.random() * 4 - 2);
            newBall.setdy(Math.random() * 4 - 2);
            score1++;
            timer2.stop();
            timer2.start(); // resets ball timer
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