package algorithms;

import java.util.Random;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.*;

public class Main {

    // 0 = greedy / Hill Climbing // 1 = random // 2 = PSO
    private static int mode;

    // Limits for the grid
    private static int minx;
    private static int maxx;
    private static int miny;
    private static int maxy;

    // Solution threshold
    public static double thresh;

    public static int getMinx() {
        return minx;
    }

    public static int getMaxx() {
        return maxx;
    }

    public static int getMiny() {
        return miny;
    }

    public static int getMaxy() {
        return maxy;
    }

    public static int getXoffset() {
        return xoffset;
    }

    public static int getYoffset() {
        return yoffset;
    }

    private static int xoffset = ((maxx - minx)+1)+minx;
    private static int yoffset = ((maxy - miny)+1)+miny;

    // Number of iterations per algorithm (number of particles)
    private static int nits;

    private static long elapsedTime;

    private static long findingTime;

    public static long time[] = {-1};

    // Starting counting time
    public static long start;

    // Best solution
    public static Point g;

    public static void main(String[] args) {

        try
        {
            Scanner scanner;
            if ((args == null) || (args.length == 0)) {
                scanner = new Scanner(System.in);
                System.out.println("Please: Write the name of the input file next to the command.");
            }else {
                scanner = new Scanner(new FileReader(args[0]));
            }

            System.out.println("Enter left limit for X grid");
            minx = scanner.nextInt();
            System.out.println("Enter right limit for X grid");
            maxx = scanner.nextInt();
            System.out.println("Enter left limit for Y grid");
            miny = scanner.nextInt();
            System.out.println("Enter right limit for Y grid");
            maxy = scanner.nextInt();
            System.out.println("Enter mode: (note: 0 for Hill Climbing // 1 for Random // 2 for PSO");
            mode = scanner.nextInt();
            System.out.println("Enter number of particles");
            nits = scanner.nextInt();
            System.out.println("Enter threshold percentage of accuracy for finding the solution");
            thresh = 1 - scanner.nextDouble()/100;
            // Save original out stream.
            PrintStream originalOut = System.out;
            // Create a new file output stream.
            PrintStream fileOut = new PrintStream("./solutions.txt");
            // Redirect standard out to file.
            System.setOut(fileOut);
            //First four lines of the document correspond to the limits of the grid
            System.out.println(minx);
            System.out.println(maxx);
            System.out.println(miny);
            System.out.println(maxy);

            if (mode == 0) {

                // Print flag to determine algorithm
                System.out.println(0);
                //Print empty parameter
                System.out.println(0);
                //Print empty parameter
                System.out.println(0);

                System.setOut(originalOut);
                System.out.println("Enter the number of maximum iterations (between 1 and 800, to avoid infinite loop when finding a plateau)");
                int maxit = scanner.nextInt();
                System.out.println("Enter the value for the step value");
                double step = scanner.nextDouble();

                // Save original out stream.
                originalOut = System.out;
                // Redirect standard out to file.
                System.setOut(fileOut);

                // Start counting time
                start = System.nanoTime();

                for (int i = 0; i < nits; ++i) {
                    //Random int between min and max: (int)(Math.random()*((max - min)+1)+min)
                    double x = new Random().nextInt(maxx-minx)+minx;
                    double y = new Random().nextInt(maxy-miny)+miny;

                    g = new Point(x, y);
                    //System.out.println(x);
                    //System.out.println(y);
                    //System.out.println(z);

                    double values[] = greedy(x, y, i, 0, 0, maxit, step, time);
                    findingTime = time[0] - start;
                    if (findingTime < 0) {
                        findingTime = elapsedTime;
                    }
                    double z = f(x, y);
                }
                elapsedTime = System.nanoTime() - start;
                // Printing random execution time
                //System.out.println("Time for Hill Climbing algorithm is " + elapsedTime);

            }

            else if (mode == 1) {
                // Print flag to determine algorithm
                System.out.println(1);
                //Print empty parameter
                System.out.println(0);
                //Print empty parameter
                System.out.println(0);
                // Start counting time
                start = System.nanoTime();
                random(time);
                elapsedTime = System.nanoTime() - start;
                findingTime = time[0] - start;
                if (findingTime < 0) {
                    findingTime = elapsedTime;
                }
                // Printing random execution time
                //System.out.println("Time for random algorithm is " + elapsedTime);
            }

            else if (mode == 2) {
                // Print flag to determine algorithm
                System.out.println(2);
                // Start counting time
                start = System.nanoTime();
                System.setOut(originalOut);
                System.out.println("Enter the value for the fp parameter (between 0 and 1)");
                double fp = scanner.nextDouble();
                Particle.setFp(fp);
                System.out.println("Enter the value for the fg parameter (between 0 and 1)");
                double fg = scanner.nextDouble();
                Particle.setFg(fg);
                System.out.println("Enter the number of steps (timesteps)");
                int ttp = scanner.nextInt();
                PSO.setTTP(ttp);
                // Save original out stream.
                originalOut = System.out;

                // Redirect standard out to file.
                System.setOut(fileOut);
                // Printing number of particles
                System.out.println(nits);
                // Printing number of timesteps
                System.out.println(ttp);
                PSO.setNP(nits);
                g = PSO.run(time, thresh);
                //System.out.println("Best solution for PSO is point (" + g.getX() + ", " + g.getY() + ") = " + f(g));
                elapsedTime = System.nanoTime() - start;
                findingTime = time[0] - start;
                if (findingTime < 0) {
                    findingTime = elapsedTime;
                }
                // Printing random execution time
                //System.out.println("Time for PSO algorithm is " + elapsedTime);

            }
            System.out.println("END");
            System.out.println("TOTAL TIME: " + elapsedTime);
            System.out.println("POINT TIME: " + findingTime);
            System.out.println("BEST: " + g.getX() + " " + g.getY() + " " + f(g));

            // Do not forget set original output and error stream back again.
            System.setOut(originalOut);
        }catch(FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        //Math.sin(Math.sqrt( (Math.pow(x, 2)) + (Math.pow(x, 2)) ));
    }

//system.out.println("1")

    //Maximizing Greedy Algorithm
    //Could have done with a priority queue, checking all the points in the grid.
    private static double[] greedy(double x, double y, double i, int timestep, int it, int maxit, double offset, long[] time) {
        double values[] = {x, y, f(x, y)};
        if (it < maxit) {
            ++it;
            double z = f(x, y);
            if (z > f(g)) g = new Point(x, y);
            if (time[0] == -1 && z >= thresh) time[0] = System.nanoTime();
            System.out.println((int) i + ") " + timestep + " " + x + " " + y + " " + z);
            ++timestep;
            //double offset = new Random().nextDouble();
            double x1 = x + offset;
            double y1 = y + offset;
            double x2 = x - offset;
            double y2 = y - offset;

            double newValues[] = {0, 0, 0, 0, 0, 0, 0, 0};

            //boolean found = false;

            // Checking plot limits (-5, 5)
            //while(!found) {

            boolean changed = false;

            if (x1 > minx && x1 < maxx) {
                //System.out.println(x1 + " " + f(x1,y));
                if (f(x1, y) >= z) {
                    newValues[1] = f(x1, y);
                    changed = true;
                }

                if (y1 > miny && y1 < maxy) {
                    //System.out.println(f(x1,y1));
                    if (f(x1, y1) >= z) {
                        newValues[2] = f(x1, y1);
                        changed = true;
                    }
                }

                if (y2 > miny && y2 < maxy) {
                    //System.out.println(f(x1,y2));
                    if (f(x1, y2) >= z) {
                        newValues[3] = f(x1, y2);
                        changed = true;
                    }
                }
            }
            if (x2 > minx && x2 < maxx) {
                if (f(x2, y) >= z) {
                    newValues[4] = f(x2, y);
                    changed = true;
                }

                if (y1 > miny && y1 < maxy) {
                    if (f(x2, y1) >= z) {
                        newValues[5] = f(x2, y1);
                        changed = true;
                    }
                }

                if (y2 > miny && y2 < maxy) {
                    if (f(x2, y2) >= z) {
                        newValues[6] = f(x2, y2);
                        changed = true;
                    }
                }
            }
            if (y1 > miny && y1 < maxy && f(x, y1) >= z) {
                newValues[7] = f(x, y1);
                changed = true;
            }
            if (y2 > miny && y2 < maxy && f(x, y2) >= z) {
                newValues[0] = f(x, y2);
                changed = true;
            }



            if (!changed) {
                return values;
            } else {

                double newZ = f(x, y);
                int pos = 8;

                for (int k = 0; k < newValues.length; k++) {
                    if (newValues[k] > newZ) {
                        newZ = newValues[k];
                        pos = k;
                    }
                }

                switch (pos) {
                    case 1:
                        return greedy(x1, y, i, timestep, it, maxit, offset, time);

                    case 2:
                        return greedy(x1, y1, i, timestep, it, maxit, offset, time);

                    case 3:
                        return greedy(x1, y2, i, timestep, it, maxit, offset, time);

                    case 4:
                        return greedy(x2, y, i, timestep, it, maxit, offset, time);

                    case 5:
                        return greedy(x2, y1, i, timestep, it, maxit, offset, time);

                    case 6:
                        return greedy(x2, y2, i, timestep, it, maxit, offset, time);

                    case 7:
                        return greedy(x, y1, i, timestep, it, maxit, offset, time);

                    case 0:
                        return greedy(x, y2, i, timestep, it, maxit, offset, time);
                    default:
                        //System.out.println("DEFAULT: Goal point is: (" + x + " " + y + ")" );
                        return values;

                }
            }
        }
        return values;
    }

    private static double random(long[] time) {

        double x = new Random().nextInt(maxx-minx)+minx;
        double y = new Random().nextInt(maxy-miny)+miny;
        double z = f(x, y);
        g = new Point (x,y);

        //
        double bestX = x;
        double bestY = y;
        //

        //int i = 0;

        //System.out.println(i + ") " + x + " " + y + " " + z);
        //System.out.println(i + ") " + bestX + " " + bestY);


        for (int i = 0; i < nits; ++i) {

            x = new Random().nextInt(maxx-minx)+minx;
            y = new Random().nextInt(maxy-miny)+miny;
            if (f(x, y) > f(g)) {
                z = f(x, y);
                g = new Point(x,y);
            }
            if (time[0] == -1 && z >= thresh) time[0] = System.nanoTime();
            System.out.println(i + ") " + "0 "+ x + " " + y + " " + f(x, y));
            //System.out.println(i + ") " + bestX + " " + bestY);
        }
        return z;
    }


    public static double f(double x, double y) {
        return Math.sin(Math.sqrt( (Math.pow(x, 2)) + (Math.pow(y, 2)) ));
    }

    public static double f(Point p) {
        double x = p.getX();
        double y = p.getY();
        return Math.sin(Math.sqrt( (Math.pow(x, 2)) + (Math.pow(y, 2)) ));
    }
}

