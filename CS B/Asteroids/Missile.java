import java.awt.Color;

public class Missile extends Polygon {

    public static final int MISSILE_SPEED = 9;

    public Missile(Point[] shape, double x, double y, double rotation) {
        super(shape, new Point(x, y), rotation);
        this.setColor(Color.RED);
        this.setWrap(false);
    }

    public void move() {
        super.move(MISSILE_SPEED);
    }

}
