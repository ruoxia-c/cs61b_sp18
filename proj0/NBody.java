public class NBody{

    public static double readRadius(String files){
        In in = new In(files);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String files){
        In in = new In(files);
        int num = in.readInt();
        Planet[] p = new Planet [num];
        in.readDouble();
        for (int i=0; i<num; i = i+1){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            p[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,img);
        }
        return p;
    }

    public static void main(String arg[]){
        String Ts = arg[0];
        double T = Double.valueOf(Ts.toString());
        String dts = arg[1];
        double dt = Double.valueOf(dts.toString());
        String filename = arg[2];
        double radius = readRadius(filename);
        Planet[] P = readPlanets(filename);
        /* Drawing the Background*/
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0,0,"images/starfield.jpg");
        for (Planet element : P){
            element.draw();
        }
        StdDraw.show();

        /* Creating an Animation */
        StdDraw.enableDoubleBuffering();
        int num = P.length;
        for (double time=0; time <=T; time = time + dt){
            double[] xForces = new double[num];
            double[] yForces = new double[num];
            for(int i=0; i<num; i=i+1){
                xForces[i] = P[i].calcNetForceExertedByX(P);
                yForces[i] = P[i].calcNetForceExertedByY(P);
            }
            for(int i=0; i<num; i=i+1){
                P[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.setScale(-radius, radius);
            StdDraw.clear();
            StdDraw.picture(0,0,"images/starfield.jpg");
            for (Planet element : P){
                element.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", num);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < num; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    P[i].xxPos, P[i].yyPos, P[i].xxVel,
                    P[i].yyVel, P[i].mass, P[i].imgFileName);
        }
    }
}