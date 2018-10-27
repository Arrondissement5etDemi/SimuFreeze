import java.util.*;

public class TestBox {
	public static void main(String[] args) {
		double[][] coordinates = {{0.5,0.5}};
		Box box1 = new Box(1,1.0,5.0,coordinates);
		int n = box1.getN();
		double d = box1.getD();
		System.out.println("number of particles and dimension of box1 is " + n + " and " + d);
		System.out.println("the positions of particles in box1:");
		System.out.println(box1.toString());
		System.out.println("the energy of box1:" + box1.getEnergy());
		System.out.println("the above should be equal to the square lattice energy per particle");
		System.out.println(SquareLattice.ljPot(1.0));
		double[][] coordinates2 = {{0.25,0.75},{0.75,0.25}};
		Box box2 = new Box(2,1.0,100.0,coordinates2);
		System.out.println("the energy of box2:" + box2.getEnergy());
		System.out.println("the above should be twice the square lattice energy per particle");
                System.out.println(SquareLattice.ljPot(0.5,1));

	}
}
