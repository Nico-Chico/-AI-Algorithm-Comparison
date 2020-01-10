package algorithms;

import java.util.*;

import static algorithms.Main.*;

public class PSO {
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
    //Array of particles
    private static Particle[] swarm;
    //Number of particles
    private static int np = -1;
    //Algorithm threshold (if some Particle reaches it, the solution is considered to be found)
    private static double thresh = 0.97;
    //Number of dimensions
    private static int ndim = 2;
    //Best global solution
    private static Point g;

    private static double[][] output;

    public static Point getG() {
        return g;
    }

    public static void setNP(int npt) {
        np = npt;
        swarm = new Particle [np];
    }

    public double[][] getOutput() {
        return output;
    }

    /*private static double velocityInitialization() {
        //Lower bound for the velocity computation (assuming maxx = maxy && minx = miny)
        //int lb = (Math.abs(getMaxx() - getMinx()));
        //Upper bound for the velocity computation (assuming maxx = maxy && minx = miny)
        //int ub = (Math.abs(getMaxx() - getMinx()));
        return Math.random()*(getMaxx() - getMinx()) + getMinx();
    }*/

    // Initializes all particles and returns initial best solution
    private static Point particlesInitialization() {

        // Global maximum declaration (assuming -999 as not defined)
        //Point g = new Point();

        // To know if the number of particles is defined
        if (np != -1) {

            // Particles creation
            for (int i = 0; i < np; ++i) {
                // Creating new random initial Point for the particle
                double x = new Random().nextDouble()*(getMaxx() - getMinx()) + getMinx();
                double y = new Random().nextDouble()*(getMaxy() - getMiny()) + getMiny();
                Point point = new Point(x, y);
                // Initial velocity for the particle
                // Lower bound
                double lb = -(Math.abs(getMaxx() - getMinx()));
                // Upper bound
                double ub = Math.abs(getMaxx() - getMinx());
                double vX = lb + new Random().nextDouble()*(ub - lb);
                //System.out.println("X Velocity of " + i + ": " + vX);
                double vY = lb + new Random().nextDouble()*(ub - lb);
                //System.out.println("Y Velocity of " + i + ": " + vY);
                // Particle creation
                swarm[i] = new Particle(point, point, vX, vY);
                //System.out.println("Particle " + i + " has position " + swarm[i].getX().getX() + " " + swarm[i].getX().getY() );

                // Global maximum initialization
                if (i == 0) {
                    g = point;
                } else {
                    if (f(point) > f(g)) g = point;
                }

            }

            // Initializing Particles global maximum
            for (Particle p : swarm) {
                p.setG(g);
            }

            // Output initialization
            output = new double[300][4];

        }
        else {
            System.out.println("Number of particles not defined");
        }

        return g;

    }

    public static Point run() {
        Point bestPoint = particlesInitialization();
        double solution = (f(bestPoint));
        int i = 0; // i = timestep
        int tp = 1; // op = output position
        // TODO: establish better criteria to stop
        while (i < 30) {
        // while (solution < thresh) {
            for (int j = 0; j < np; ++j) {

                for (int d = 0; d < ndim; ++d) {
                    if (d == 0) {
                        double rpx = new Random().nextDouble();
                        double rgx = new Random().nextDouble();
                        swarm[j].setSpeedX(rpx, rgx);
                    } else if (d == 1) {
                        double rpy = new Random().nextDouble();
                        double rgy = new Random().nextDouble();
                        swarm[j].setSpeedY(rpy, rgy);
                    }
                }

                /*if (i == 0) {
                    output[j][0] = i;
                    output[j][1] = swarm[j].getX().getX();
                    output[j][2] = swarm[j].getX().getY();
                }*/

                //System.out.println("Particle " + j + " has position " + swarm[j].getX().getX() + " " + swarm[j].getX().getY() );
                swarm[j].updatePosition();
                //System.out.println("Particle " + j + " has position " + swarm[j].getX().getX() + " " + swarm[j].getX().getY() );
                //System.out.println(f(swarm[j].getX()));
                if (tp < 300) {
                    output[tp][0] = i;
                    output[tp][1] = j;
                    output[tp][2] = swarm[j].getX().getX();
                    output[tp][3] = swarm[j].getX().getY();
                    ++tp;
                }
                //System.out.println("Timestesp " + i + " Particle " + j + " Position " + swarm[j].getX().getX() + " " + swarm[j].getX().getY());
                /*output[j][0] = i+1;
                output[j][1] = swarm[j].getX().getX();
                output[j][2] = swarm[j].getX().getY();*/

                if (f(swarm[j].getX()) > f(swarm[j].getP())) {
                    swarm[j].setP(swarm[j].getX());
                    if (f(swarm[j].getP()) > f(swarm[j].getG())) {
                        swarm[j].setG(swarm[j].getP());
                        g = swarm[j].getP();
                        //solution = f(g);
                    }
                }
                //System.out.println(f(swarm[j].getP()));
            }
            ++i;
        }
        readOutput();
        return g;
    }

    public static void readOutput() {
        for (int i = 0; i < output.length; ++i) {
            System.out.println(output[i][1] + " " + output[i][0] + " " + output[i][2] + " " + output[i][3]);
        }
    }

}
