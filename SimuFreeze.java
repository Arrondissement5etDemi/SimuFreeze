import java.util.*;

public class SimuFreeze {
	public static void main(String[] args) {
		//create a new box with random particles
		Box pandora = new Box(120,Math.sqrt(120*0.8),8.0);
		//get the initial energy
		System.out.println(pandora.getN()+" "+pandora.getD());
		Scanner keyboard = new Scanner(System.in);
                System.out.println("Enter displacement");
                double displace = keyboard.nextDouble();

		//begin the cooling
		for (double temperature = 10; temperature >= 0.001; temperature = temperature * 0.98) {
			System.out.println("Temperature now = " + Double.toString(temperature));
			int numAccept = 0;
			int numReject = 0;
			int timePerTemp = 10000;
			
			for (int i=1; i<= timePerTemp; i++) {
				int ind = 
				(int) Math.floor(Box.getRandomNumberInRange(0.0,(double)pandora.getN()));
				double oldE = pandora.partiE(ind);
				Movement proposedMove = pandora.move(ind,displace);
				double newE = pandora.partiE(ind);
				//do we accept it?
				if (Boltzmann.accept(newE,oldE,temperature)) {
					numAccept ++;
					//System.out.println("Energy now = " + pandora.getEnergy());
				} 
				else {
					pandora.move(proposedMove.reverse());
					numReject ++;
				}
			}
			System.out.println("Accept ratio =" + (double)numAccept/(double)timePerTemp);
			System.out.println("Energy now = " + pandora.getEnergy());
			//System.out.println(pandora);
		}
		//get the final configuration, record energy, etc
		System.out.println("The final configuration: ");
		System.out.println(pandora);
		System.out.println("The final energy : " + pandora.getEnergy());
	}
}
