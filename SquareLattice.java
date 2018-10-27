import java.util.*;

//This is for treatment of square lattices.

public class SquareLattice {

	/**computes the eligible pairs of integer (n,m) such that n^2+m^2=k, where k is an integer >=0.
 	@param k positive integer, depicting the square of distance from origin to the point (n,m)
	@return the number of eligible pairs of integers (n,m) whose distance to origin is sqrt(l)*/
	public static int degeneracy(int k) {
		if (k<0) {
			throw new ArithmeticException("The input value k of degeneracy has to be >= 0.");
		}
		else {
			int count = 0;
			for (int n = 0; n <= Math.sqrt((double)k); n++) {
				int mSquare = k - n * n;
				if (isSquare(mSquare)) {
					if (n==0 && mSquare==0) {
						count++;
					}
					else if (n==0 || mSquare==0) {
						count = count + 2;
					}
					else {
						count = count + 4;	
					}
				}
			}
			return count;
		}
	}

	/**determines whether an integer mm is a square of some integer.
 	@param mm nonnegative integer, candidate of a square number
	@return boolean, whether mm is really a square number*/
	public static boolean isSquare(int mm) {
		//the candidate square root
		int mCandi = (int) Math.round(Math.sqrt((double) mm));
		return (mm == mCandi * mCandi);
	}


	/**computes the LJ potential energy on a point in the square lattice scaled by a factor
 	@param scale double, the scaling factor, the nearest neighbor distance in the square lattice
	@return double, the LJ potential of the (scaled) square lattice, assuming e=r0=0 in the LJ eqn. */
	public static double ljPot(double scale) {
		double result = 0;
		for (int k = 1; k <= 1000; k++) {
			double potK = LjPotential.lj(Math.sqrt(k)*scale);
			double degK = (double) degeneracy(k);
			double termK = potK * degK;
			result += termK;
		}
		return result;
	}

	/**computes the LJ potential energy on a point in the square lattice with a given value of area per particle
        @param app double, area per particle
	@param dummy int, a dummy indicator that we are in the area-per-particle mode. Can be any int.
        @return double, the LJ potential of the specified square lattice, assuming e=r0=0 in the LJ eqn. */
        public static double ljPot(double app, int dummy) {
                double scale = Math.sqrt(app);
		return ljPot(scale);
        }

}
