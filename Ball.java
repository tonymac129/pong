//Tony Hsu, Vihaan Jani

import java.awt.*;

public class Ball extends Polkadot {
    private double dx; // pixels to move each time step() is called.
    private double dy;

    // constructors
    public Ball() // default constructor
    {
        super(200, 200, 40, Color.WHITE);
        dx = Math.random() * 12 - 6; // to move vertically
        dy = Math.random() * 12 - 6; // to move sideways
        setRadius(40 / 2);

    }

    public Ball(double x, double y, double dia, Color c) {
        super(x, y, dia, c);
        dx = Math.random() * 12 - 6;
        dy = Math.random() * 12 - 6;
    }

    // modifier methods
    public void setdx(double x) {
        dx = x;
    }

    public void setdy(double y) {
        dy = y;
    }

    // accessor methods
    public double getdx() {
        return dx;
    }

    public double getdy() {
        return dy;
    }

    // instance methods
    public void move(double rightEdge, double bottomEdge, double speed) {
        setX(getX() + (dx / Math.abs(dx)) * (Math.abs(dx) + speed)); // move horizontally

        if (getX() >= rightEdge - getRadius()) // hit right edge
        {
            setX(rightEdge - getRadius());
            dx = dx * -1;
        }
        if (getX() <= getRadius()) // hit left edge
        {
            setX(getRadius());
            if (dx < 0) {
                dx = dx * -1;
            } else {
                dx = dx * 1;
            }
        }

        setY(getY() + dy);
        if (getY() >= bottomEdge - getRadius()) {
            setY(bottomEdge - getRadius());
            dy = dy * -1;
        }
        if (getY() <= getRadius()) {
            setY(getRadius());
            if (dy < 0) {
                dy = dy * -1;
            } else {
                dy = dy * 1;
            }
        }
    }
}