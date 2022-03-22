/*
CLASS: Polygon
DESCRIPTION: A polygon is a sequence of points in space defined by a set of
    such points, an offset, and a rotation. The offset is the
    distance between the origin and the center of the shape.
    The rotation is measured in degrees, 0-360.
USAGE: You are intended to instantiate this class with a set of points that
    forever defines its shape, and then modify it by repositioning and
    rotating that shape. In defining the shape, the relative positions
    of the points you provide are used, in other words: {(0,1),(1,1),(1,0)}
    as the same shape as {(9,10),(10,10),(10,9)}.
NOTE: You don't need to worry about the "magic math" details.
*/

import java.awt.*;

public class Polygon {
    private Point[] shape; // An array of points.
    protected Point position; // The offset mentioned above.
    protected double rotation; // Zero degrees is due east.
    private Color myColor; // The color that the polygon will be drawn in
    private boolean fill; // Whether the polygon is painted filled or hollow.
    private boolean wrap; // An indicator of whether the polygon wraps the screen (reappears on the other
                          // side)

    public Polygon(Point[] inShape, Point inPosition, double inRotation) {
        shape = inShape;
        position = inPosition;
        rotation = inRotation;
        myColor = Color.YELLOW;
        fill = true;
        wrap = true;

        // First, we find the shape's top-most left-most boundary, its origin.
        // Position origin = shape[0].clone();
        Point origin = new Point(shape[0].x, shape[0].y);
        for (Point p : shape) {
            if (p.x < origin.x)
                origin.x = p.x;
            if (p.y < origin.y)
                origin.y = p.y;
        }

        // Then, we orient all of its points relative to the real origin.
        for (Point p : shape) {
            p.x -= origin.x;
            p.y -= origin.y;
        }
    }

    // "getPoints" applies the rotation and offset to the shape of the polygon.
    public Point[] getPoints() {
        Point center = findCenter();
        Point[] points = new Point[shape.length];
        // SEH - had to declare i
        int i = 0;
        for (Point p : shape) {
            // SEH - had to change x and y to doubles
            double x = ((p.x - center.x) * Math.cos(Math.toRadians(rotation))) - ((p.y - center.y) * Math.sin(Math.toRadians(rotation))) + position.x;
            // + center.x/2 + position.x;
            double y = ((p.x - center.x) * Math.sin(Math.toRadians(rotation))) + ((p.y - center.y) * Math.cos(Math.toRadians(rotation))) + position.y;
            // + center.y/2 + position.y;
            points[i] = new Point(x, y);
            i++;
        }
        return points;
    }

    // "contains" implements some magical math (i.e. the ray-casting algorithm).
    public boolean contains(Point point) {
        Point[] points = getPoints();
        double crossingNumber = 0;
        for (int i = 0, j = 1; i < shape.length; i++, j = (j + 1) % shape.length) {
            if ((((points[i].x < point.x) && (point.x <= points[j].x)) || ((points[j].x < point.x) && (point.x <= points[i].x)))
                    && (point.y > points[i].y + (points[j].y - points[i].y) / (points[j].x - points[i].x) * (point.x - points[i].x))) {
                crossingNumber++;
            }
        }
        return crossingNumber % 2 == 1;
    }

    public void paint(Graphics brush) {
        Point[] adjShape = getPoints(); // Use the provided method to figure out where the shape goes based on current
                                        // offset and rotation.

        // APCS - When we had just the ship as a polygon, we always wanted this yellow.
        // You now
        // need to change this line to provide the ability to use different colors for
        // the different
        // subclasses of Polygons

        brush.setColor(myColor);

        int[] xPoints = new int[adjShape.length];
        int[] yPoints = new int[adjShape.length];
        for (int i = 0; i < adjShape.length; i++) {
            xPoints[i] = (int) adjShape[i].x;
            yPoints[i] = (int) adjShape[i].y;
        }

        // APCS - For the ship polygon we wanted it filled. Asteroids should not be
        // filled so again
        // we need a way to do this differently depending on what subclass the polygon
        // is. You
        // need to replace the condition for the if statement with your solution to this
        // problem.
        if (fill)
            brush.fillPolygon(xPoints, yPoints, adjShape.length);
        else
            brush.drawPolygon(xPoints, yPoints, adjShape.length);
    }

    public void rotate(int degrees) {
        rotation = (rotation + degrees) % 360;
    }

    public void setColor(Color c) {
        myColor = c;
    }

    public void setFill(boolean f) {
        fill = f;
    }

    public void setWrap(boolean w) {
        wrap = w;
    }

    public void move(int distance) {
        // Because of how the rotation angle is stored, x uses sin and y uses cos. Also
        // the x
        // has to add the calculation while y subtracts it because in Java graphics, the
        // y-axis
        // coordinates increase as you go DOWN not UP.
        Point newPos = new Point(position.x + (distance * Math.sin(Math.toRadians(rotation))), position.y - (distance * Math.cos(Math.toRadians(rotation))));

        // APCS - One more point where we need different behavior for different
        // subclasses. Some subclasses
        // may want to wrap around the edges of the screen, such as the ship and the
        // asteroids. Others may
        // not want to, such as the bullets. Replace the if condition below to have this
        // behavior vary.

        // make adjustments to the position so the ship rolls around edges of the
        // screen.
        if (wrap) {
            if (newPos.x > Game.SCREEN_WIDTH) {
                Point adjPos = new Point(newPos.x % Game.SCREEN_WIDTH, newPos.y);
                newPos = adjPos;
            } else if (newPos.x < 0) {
                Point adjPos = new Point(newPos.x + Game.SCREEN_WIDTH, newPos.y);
                newPos = adjPos;
            } else if (newPos.y > Game.SCREEN_HEIGHT) {
                Point adjPos = new Point(newPos.x, newPos.y % Game.SCREEN_HEIGHT);
                newPos = adjPos;
            } else if (newPos.y < 0) {
                Point adjPos = new Point(newPos.x, newPos.y + Game.SCREEN_HEIGHT);
                newPos = adjPos;
            }
        }
        position = newPos;

    }

    public boolean collides(Polygon other) {
        // Need to use getPoints
        if (other == null)
            return false;

        Point[] otherpts = other.getPoints();
        for (int i = 0; i < otherpts.length; i++) {
            if (contains(otherpts[i]))
                return true;
        }
        return false;

    }

    // "findArea" implements some more magic math.
    private double findArea() {
        double sum = 0;
        for (int i = 0, j = 1; i < shape.length; i++, j = (j + 1) % shape.length) {
            sum += shape[i].x * shape[j].y - shape[j].x * shape[i].y;
        }
        return Math.abs(sum / 2);
    }

    // "findCenter" implements another bit of math.
    private Point findCenter() {
        Point sum = new Point(0, 0);
        for (int i = 0, j = 1; i < shape.length; i++, j = (j + 1) % shape.length) {
            sum.x += (shape[i].x + shape[j].x) * (shape[i].x * shape[j].y - shape[j].x * shape[i].y);
            sum.y += (shape[i].y + shape[j].y) * (shape[i].x * shape[j].y - shape[j].x * shape[i].y);
        }
        double area = findArea();
        return new Point(Math.abs(sum.x / (6 * area)), Math.abs(sum.y / (6 * area)));
    }
}