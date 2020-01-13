package algorithms;

public class Point {

    private double x;
    private double y;

    // Default constructor
    public Point() {}

    // Constructor with initialization
    public Point(double nx, double ny) {
        x = nx;
        y = ny;
    }

    public double getX() {
        return x;
    }

    public void setX(double nx) {
        x = nx;
    }

    public double getY() {
        return y;
    }

    public void setY(double ny) {
        y = ny;
    }
}
