public class NBody {
	public static double readRadius(String filename) {
		In in = new In(filename);
		in.readInt();
		return in.readDouble();
	}
	public static Planet[] readPlanets(String filename) {
		In in = new In(filename);
		int len = in.readInt();
		in.readDouble();
		Planet[] Planets = new Planet[len];
		for(int i = 0; i < len; i++) {
			Planet temp = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
			Planets[i] = temp;
		}
		return Planets;
	}
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet[] planets = readPlanets(filename);
		double radius = readRadius(filename);
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");
		for(Planet p : planets) {
			p.draw();
		}
		double[] xForces = new double[planets.length];
		double[] yForces = new double[planets.length];
		for(int i = 0; i < T; i+=dt) {
			StdDraw.enableDoubleBuffering();
			for(int j = 0; j < planets.length; j++) {
				xForces[j] = planets[j].calcNetForceExertedByX(planets);
				yForces[j] = planets[j].calcNetForceExertedByY(planets);
			}
			for(int j = 0; j < planets.length; j++) {
				planets[j].update(dt, xForces[j], yForces[j]);
			}
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for(Planet p : planets) {
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for(int j = 0; j < planets.length; j++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
							planets[j].xxPos, planets[j].yyPos, planets[j].xxVel,
							planets[j].yyVel, planets[j].mass, planets[j].imgFileName);
		}
	}

}
