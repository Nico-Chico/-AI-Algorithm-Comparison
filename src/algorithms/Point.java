package algorithms;

public class Point {

    private static double x;
    private static double y;

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

    public static void setX(double nx) {
        x = nx;
    }

    public double getY() {
        return y;
    }

    public static void setY(double ny) {
        y = ny;
    }
}
