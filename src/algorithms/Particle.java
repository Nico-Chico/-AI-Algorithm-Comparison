package algorithms;

import static algorithms.Main.*;

public class Particle {
/*
        ---PSO---
        1. i = 1..S partícules
            2. Ini posicions xi (uniformement distribuït entre límits (-10..10))
            3. Ini millor pos pi <- xi
            4. Ini millor pos global g
            5. Ini velocitat v
        6. while !hagi d'aturar (màx it, sol suficientment bona)
            7. per cada i
                8. per cada dimensió d = 1..n
                    9. Triar 2 nums aleatoris rp, rg (0..1)
                    10. Actualitzar v (w·vid + fp·rp·(pid - xid) + fprg·(gd - xid))
                    //w, fp i fp paràmetres a definir
                11. Act pos de la partícula xi <- xi + vi
                12. Si f(xi) < f(pi):
                    13. Act millor pos coneguda (pi <- xi)
                    14. Si f(pi) < f(g) act millor pos global (g <- pi)
        15. Retornar g


*/

// Double attribute should be arranged on the algorithms class (chapuza con el getter)
    //Position of the particle
    private Point x;
    //Best position of the particle
    private Point p;
    //Speed of the particle in the X axis
    private double speedX;
    //Speed of the particle in the Y axis
    private double speedY;
    // Global maximum
    private static Point g;
    // Omega parameter
    private static double w = 1;
    // Phi p parameter
    private static double fp = 0.5;
    // Phi g parameter
    private static double fg = 0.5;

    public Point getG() {
        return g;
    }

    public void setG(Point g) {
        this.g = g;
    }

    // Default constructor
    public Particle() {}

    // Constructor with Point class initialization
    public Particle(Point x, Point p, double speedX, double speedY) {
        this.x = x;
        this.p = p;
        this.speedX = speedX;
        this.speedY = speedY;

    }

    public Point getX() {
        return x;
    }

    public void setX(Point x) {
        this.x = x;
        // Recalculate best position of the particle
        if (Main.f(x) > Main.f(p)) p = x;
    }

    public Point getP() {
        return p;
    }

    public void setP(Point p) {
        this.p = p;
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speed) {
        this.speedX = speed;
    }

    public void setSpeedY(double speed) {
        this.speedY = speed;
    }

    public void setSpeedX(double rp, double rg) {
        speedX = w * speedX + fp * rp * (p.getX() - x.getX()) + fg * rg * (PSO.getG().getX() - x.getX());
        //System.out.println("Velocity of X: " + speedX);
    }

    public void setSpeedY(double rp, double rg) {
        speedY = w * speedY + fp * rp * (p.getY() - x.getY()) + fg * rg * (PSO.getG().getY() - x.getY());
        //System.out.println("Velocity of Y: " + speedY);
    }

    /*public void checkPosition() {
        if ()
    }*/

    public void updatePosition() {
        double newX = x.getX() + speedX;
        double newY = x.getY() + speedY;
        if (newX > getMaxx()) newX = getMaxx();
        else if (newX < getMinx()) newX = getMinx();
        if (newY > getMaxy()) newY = getMaxy();
        else if (newY < getMiny()) newY = getMiny();
        this.x = new Point(newX, newY);
    }
}
