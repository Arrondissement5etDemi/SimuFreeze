import java.util.*;


//this is a d*d square box with n Particles in the periodic boundary condition.
public class Box {
	private int n; //the number of particles
	private double d; //the dimension of the box
	private Particle[] partiArr;
	private double cutoff; //the cutoff distance for calculating the energy at a particle
	
	/** constructs a box with randomized particle distribution 
 * 	@param nGiven int, the given numer of particles
 * 	@param dGiven double, the given size of the box
 * 	@param co     double, the given cutoff distance
 * 	@return Box, the constructed box */
	public Box(int nGiven, double dGiven, double co) {
		n = nGiven;
		d = dGiven;
		cutoff = co;
		partiArr = new Particle[n];
		for (int i = 0; i < n; i++) {
			double x = getRandomNumberInRange(0,d);
			double y = getRandomNumberInRange(0,d);
			Particle p = new Particle(x,y);		
			partiArr[i] = p;
		}
	}

	/** FOR TESTING ONLY: a box whose particle positions are predetermined
 *      @param nGiven int, the given numer of particles
 *      @param dGiven double, the given size of the box
 *      @param co     double, the given cutoff distance
 *      @return Box, the constructed box */
	public Box(int nGiven, double dGiven, double co, double[][] coordinates) {
		n = nGiven;
                d = dGiven;
                cutoff = co;
		partiArr = new Particle[n];
		for (int i = 0; i < n; i++) {
			double x = coordinates[i][0];
			double y = coordinates[i][1];
			Particle p = new Particle(x,y);
			partiArr[i] = p;
		}
	}

	/** gets d, the dimension of the box
 * 	@return double */
	public double getD() {
		return d;
	}

	/** gets n, the number of particles
 *      @return int */
        public int getN() {
                return n;
        }

	/**computes the sum energy of all particles in the box
 * 	@return double, the ensemble energy */
	public double getEnergy() {
		double sum = 0;
		for (int i=0; i < n; i++) {
			sum = sum + partiE(i);
		}
		return sum;
	}

	/**gets the energy felt by a single particle in the box
 * 	@param ind integer, the index of the particle in partiArr 
 * 	@return double, the energy felt by the particle */
	private double partiE(int ind) {
		//get the particle in question
		if (ind >= n) {
			throw new IllegalArgumentException("input index must be < the number of particles");
		}
		Particle thisParti = partiArr[ind];
		//get the bound cells
		int bound = (int) Math.ceil(cutoff / d);
		
		//for every particle in the box, compute their countribution to the particle
		double result = 0;
		for (int i = 0; i < n ; i++) {
			Particle thatParti = partiArr[i];
			double thatX = thatParti.getx();
			double thatY = thatParti.gety();
			//scan through the relavant cells...
			for (int j = -bound; j <= bound; j++) {
				for (int k = -bound; k <= bound; k++) {
					boolean self = (j==0 && k==0 && i==ind);
					//duplicate thatParti in the scanned cell
					Particle dupliOfThat = new Particle(thatX + j*d, thatY + k*d);
					//compute the distance between thisParti and the duplicate
					double distToDupli = thisParti.distanceto(dupliOfThat);
					//if the duplicate is in the cutoff sphere, get the pair energy
					if (distToDupli <= cutoff && !self) {
						double pairEnergy = LjPotential.lj(distToDupli);
						result = result + pairEnergy;
					}
				}
			}
		
		}
		return result;
	}

	/**moves a random particle in a random direction by a distance of 0.001
 * 	@return Movement, what movement has occured. */
	public Movement move() {
		//randomly choose a particle to move
		int ind = (int) Math.floor(getRandomNumberInRange(0.0,(double)n));
		//randomly choose a direction
		double direction = getRandomNumberInRange(0.0,2*Math.PI);
		Movement result = new Movement(ind, direction);
		move(result);
		return result;
	}
	
	/**moves a particlar particle in a particular direction by a distance of 0.001
 * 	@param m, Movement, including the index of the particle and the direction to move */
	public void move(Movement m) {
		int ind = m.getInd();
		double direction = m.getDirection();
		if (direction >= 2*Math.PI || direction < 0) {
			throw new IllegalArgumentException("input direction must be between 0 and 2*PI");
		}
		if (ind >= n) {
                        throw new IllegalArgumentException("input index must be < the number of particles");
                }
		double moveDist = 0.001;
		//obtain the coordinates of the particle at ind
		double x = partiArr[ind].getx();
		double y = partiArr[ind].gety();
		//obtain the amount of x and y to move
		double deltaX = Math.cos(direction)*moveDist;
		double deltaY = Math.sin(direction)*moveDist;
		//get new coordinates of the partilce at ind
		partiArr[ind].setX(positiveModulo(x + deltaX,d));
		partiArr[ind].setY(positiveModulo(y + deltaY,d));	
	}

	/** returns the array of particles
 * 	@return a newly constructed array of particles that is a deep copy of partiArr */
	public Particle[] toArray() {
		Particle[] partiArrCopy = new Particle[n];
		for (int i = 0; i < n; i++) {
			double x = partiArr[i].getx();
			double y = partiArr[i].gety();
			Particle pCopy = new Particle(x,y);
			partiArrCopy[i] = pCopy;
		}
		return partiArrCopy;
	}

	/** returns the contant of partiArray as a string
 * 	@return the content of partiArr */
	public String toString() {
		String result = "";
		for (int i = 0; i < n; i++) {
			result = result + partiArr[i].toString() + "\n";
		}
		return result;
	}

	//auxillary function
	private static double getRandomNumberInRange(double min, double max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
	        }		
		Random r = new Random();	
       		return r.nextDouble()*(max - min) + min;
	}
	
		
	private static double positiveModulo(double x, double d) {
		double result = x%d;
		if (result < 0) {
			result = result + d;
		}
		return result;
	}
}
