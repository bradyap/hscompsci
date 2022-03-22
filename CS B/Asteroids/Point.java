/*
CLASS: Point
DESCRIPTION: Ah, if only real-life classes were this straight-forward. 
    We'll use 'Point' throughout the program to store and access coordinates.
*/

class Point {
    double x, y;

    public Point(double inX, double inY) {
        x = inX;
        y = inY;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double newX) {
        x = newX;
    }

    public void setY(double newY) {
        y = newY;
    }
}