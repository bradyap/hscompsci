import java.awt.Color;

public class Asteroid extends Polygon {
    public Asteroid(Point[] shape, int x, int y) {
        super(shape, new Point(x, y), 0);
        this.setColor(Color.GRAY);
    }

    public void move() {
        super.move(0);
    }

    public void rotate(int angle) {
        super.rotate(angle);
    }
}
