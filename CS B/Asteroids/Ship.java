import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Ship extends Polygon {
    private ArrayList<Missile> missiles = new ArrayList<Missile>();

    public Ship(Point[] shape, Point loc) {
        super(shape, loc, 0);
        this.setColor(Color.GREEN);
    }

    public void paint(Graphics g) {
        super.paint(g);

        for (Missile m : missiles) {
            m.move();
            m.paint(g);
        }
    }

    public void fire() {
        Point[] shape = { new Point(position.x, position.y), new Point(position.x + 5, position.y), new Point(position.x + 5, position.y + 5),
                new Point(position.x, position.y + 5) };
        Missile m = new Missile(shape, position.x, position.y, rotation);
        missiles.add(m);
    }

    public ArrayList<Missile> getMissiles() {
        return missiles;
    }
}
