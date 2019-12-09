package algorithms;

import java.util.Random;
// import java.util.Map;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Main {

    // 0 = greedy // 1 = random // 2 = PSO
    private static int mode = 2;

    // Limits for the grid
    private static int minx = -10;
    private static int maxx = 10;
    private static int miny = -10;
    private static int maxy = 10;

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

    // Number of iterations per algorithm
    private static int nits = 2;

    public static void main(String[] args) {

        try
        {
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


                // Start counting time
                long start = System.nanoTime();

                for (int i = 0; i < nits; ++i) {
                    //Random int between min and max: (int)(Math.random()*((max - min)+1)+min)
                    int x = new Random().nextInt(maxx-minx)+minx;
                    int y = new Random().nextInt(maxy-miny)+miny;
                    //System.out.println(x);
                    //System.out.println(y);
                    //System.out.println(z);

                    double values[] = greedy(x, y, i);

                    double z = f(x, y);
                }
                long elapsedTime = System.nanoTime() - start;
                // Printing random execution time
                //System.out.println("Time for Hill Climbing algorithm is " + elapsedTime);

            }

            else if (mode == 1) {
                // Print flag to determine algorithm
                System.out.println(1);
                // Start counting time
                long start = System.nanoTime();
                double maxz = random();
                long elapsedTime = System.nanoTime() - start;
                // Printing random execution time
                //System.out.println("Time for random algorithm is " + elapsedTime);
            }

            else if (mode == 2) {
                // Print flag to determine algorithm
                System.out.println(2);
                // Start counting time
                long start = System.nanoTime();
                PSO.setNP(nits);
                Point g = PSO.run();
                System.out.println("Best solution for PSO is point (" + g.getX() + ", " + g.getY() + ") = " + f(g));
                long elapsedTime = System.nanoTime() - start;
                // Printing random execution time
                System.out.println("Time for PSO algorithm is " + elapsedTime);

            }
            System.out.println("END");

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
    //Could have done with a priority queue, checking all the point in the grid.
    private static double[] greedy(int x, int y, int i) {
        //System.out.println("NEW CALL");
        double z = f(x, y);

        System.out.println(i + ") " + x + " " + y + " " + z);

        int x1 = x+1;
        int y1 = y+1;
        int x2 = x-1;
        int y2 = y-1;

        double newValues[] = {0,0,0,0,0,0,0,0};

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

        double values[] = {x, y, f(x, y)};

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

            //System.out.println(newZ);

            switch (pos) {
                case 1:
                    return greedy(x1, y, i);

                case 2:
                    return greedy(x1, y1, i);

                case 3:
                    return greedy(x1, y2, i);

                case 4:
                    return greedy(x2, y, i);

                case 5:
                    return greedy(x2, y1, i);

                case 6:
                    return greedy(x2, y2, i);

                case 7:
                    return greedy(x, y1, i);

                case 0:
                    return greedy(x, y2, i);
                default:
                    //System.out.println("DEFAULT: Goal point is: (" + x + " " + y + ")" );
                    return values;

            }
        }
    }

    private static double random() {

        int x = new Random().nextInt(xoffset);
        int y = new Random().nextInt(yoffset);
        double z = f(x, y);
        int i = 0;

        System.out.println(i + ") " + x + " " + y + " " + z);


        for (i = 1; i < nits; ++i) {

            x = new Random().nextInt(xoffset);
            y = new Random().nextInt(yoffset);
            if (f(x, y) > z) {
                z = f(x, y);
            }
            System.out.println(i + ") " + x + " " + y + " " + f(x, y));
        }
        return z;
    }


    private static double f(int x, int y) {
        return Math.sin(Math.sqrt( (Math.pow(x, 2)) + (Math.pow(y, 2)) ));
    }

    public static double f(Point p) {
        double x = p.getX();
        double y = p.getY();
        return Math.sin(Math.sqrt( (Math.pow(x, 2)) + (Math.pow(y, 2)) ));
    }
}

