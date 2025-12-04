//Tony Hsu, Vihaan Jani

import java.awt.*;

public class Paddle extends Bumper {
    public Paddle() {
        super(25, 225, 10, 100, Color.WHITE);
    }

    public Paddle(int x, Color c) {
        super(x, 225, 10, 100, c);
    }

    public Paddle(int x) {
        super(x, 225, 10, 100, Color.WHITE);
    }
}