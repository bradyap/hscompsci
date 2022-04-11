public class Asteroid extends Polygon {
    public Asteroid(Point[] shape, int x, int y) {
        super(shape, new Point(x, y), 0);
    }

    public void move() {
        super.move(0);
    }

    public void rotate(int angle) {
        super.rotate(angle);
    }
}
