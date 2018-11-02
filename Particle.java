import java.util.*;

public class Particle {
        //data
	private double x,y;

	//constructor
        public Particle(double xx, double yy) {
		x = xx;
                y = yy;
        }

        //gets the coordinates
        public double getx() {
		return x;
	}
    
	public double gety() {
		return y;
	}

	//modifiers
	public void setX(double xx) {
		x = xx;
	}

	public void setY(double yy) {
		y = yy;
	}

	//distance to another city
	public double distanceto(Particle another) {
		double anotherX = another.getx();
                double anotherY = another.gety();
                return Math.sqrt(Math.pow((x - anotherX),2) + Math.pow((y - anotherY),2));
	}

	public boolean equals(Particle another) {
		return (x == another.getx() && y == another.gety());
	}

	public String toString() {
		return "(" + Double.toString(x) + "," + Double.toString(y) + ")";
	}
}
