import java.util.*;
/**
A class that computes the Lennard-Jones potential
@author Haina Wang (Studio-Darboux-Carbonnier)
*/

public class LjPotential {
	/** computes the LJ-potential given r, r0, e (see parameter descriptions)
 	@param r double, the internuclear distance variable
	@param r0 double, the minimum-energy internuclear distance
	@param e double, the depth of the energy well
	@return double, the LJ potential at internuclear distance r */
	public static double ljClassic(double r, double r0, double e) {
		double r0DivR = r0/r;
		double result = e*(Math.pow(r0DivR,12)-2.0*Math.pow(r0DivR,6));
		return result;
	}

	/** computes the LJ-potential given r, and r0 and e are all set to 1
        @param r double, the internuclear distance variable
        @return double, the LJ potential at internuclear distance r */
        public static double lj(double r) {
		return ljClassic(r,1.0,1.0);
	}
}
