import java.util.*;

public class SimuFreeze {
	public static void main(String[] args) {
		//create a new box with random particles
		Box pandora = new Box(100,9.0,10.0);
		//get the initial energy
		double recordE = pandora.getEnergy();
		System.out.print(recordE);
		//begin the cooling
		for (double temperature = 3; temperature >= 0.01; temperature = temperature * 0.9) {
			System.out.println("Temperature now = " + Double.toString(temperature));
			int numAccept = 0;
			while (numAccept <= 200) {
				double oldE = pandora.getEnergy();
				//move a random particle in a random direction
				Movement proposedMove = pandora.move(5.0,1);
				double newE = pandora.getEnergy();
				//if the energy gets lower, we update the record
				if (newE < recordE) {
					recordE = newE;
				}
				//do we accept it?
				if (Boltzmann.accept(newE,oldE,temperature)) {
					numAccept ++;
					System.out.println(numAccept+" "+newE);
				} 
				else {
					pandora.move(proposedMove.reverse());	
				}
			}
		}
		//get the final configuration, record energy, etc
		System.out.println("The final configuration: ");
		System.out.println(pandora);
		System.out.println("The final energy : " + pandora.getEnergy());
		System.out.println("Record low energy: " + Double.toString(recordE));
	}
}
