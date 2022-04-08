import static java.lang.Math.*;
public class Planet {
	public static final double G = 6.67e-11;
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass =  p.mass; 
		imgFileName = p.imgFileName;
	}
	public double calcDistance(Planet p) {
		return sqrt(pow(abs(this.xxPos - p.xxPos), 2) + pow(abs(this.yyPos - p.yyPos), 2));
	}
	public double calcForceExertedBy(Planet p) {
		return G * this.mass * p.mass / pow(calcDistance(p), 2);
	}
	public double calcForceExertedByX(Planet p) {
		if(calcDistance(p) == 0)
			return 0;
		return calcForceExertedBy(p) * (p.xxPos - this.xxPos) / calcDistance(p);
	}
	public double calcForceExertedByY(Planet p) {
		if(calcDistance(p) == 0)
			return 0;
		return calcForceExertedBy(p) * (p.yyPos - this.yyPos) / calcDistance(p);
	}
	public double calcNetForceExertedByX(Planet[] plants) {
		double FS = 0;
		for(Planet p : plants) {
			FS += calcForceExertedByX(p);
		}
		return FS;
	}
	public double calcNetForceExertedByY(Planet[] plants) {
		double FS = 0;
		for(Planet p : plants) {
			FS += calcForceExertedByY(p);
		}
		return FS;
	}
}
