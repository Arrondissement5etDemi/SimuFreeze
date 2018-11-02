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
		System.out.println("The positions of the particles in box2:"+ box2.toString());
		System.out.println("the energy of box2:" + box2.getEnergy());
		System.out.println("the above should be twice the square lattice energy per particle");
                System.out.println(SquareLattice.ljPot(0.5,1));
		//test the move method for box1
		Movement left = new Movement(0,Math.PI);
		box1.move(left);
		System.out.println("we move left, the new psitions of particles in box1:");
		System.out.println(box1.toString());
		Movement right = new Movement(0,0);
		box1.move(right);
		System.out.println("we move right, now the particle should return to the original position:");
		System.out.println(box1.toString());
		System.out.println("now we move the particle randomly...");
		box1.move();
		System.out.println("The new position of the particle:"+ box1.toString());
		System.out.println("the energy of box1:" + box1.getEnergy());
		//test the move method for box2
		box2.move();
		System.out.println("test move for box 2:");
		System.out.println("The new positions of the particles in box2: "+ box2.toString());
                System.out.println("the energy of box2: " + box2.getEnergy());
	}
}
