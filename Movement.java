public class Movement {
	private int ind;
	private double direction;

	public Movement(int i, double d) {
		ind = i;
		direction = d;
	}	

	public int getInd() {
		return ind;
	}

	public double getDirection() {
		return direction;
	}
	
	/** get the reverse movement where the direction is the opposite 
 * 	@return the reverse movement */
	public Movement reverse() {
		int resultI = ind;
		double resultD = (Math.PI + direction)%(2*Math.PI);
		Movement result = new Movement(resultI, resultD);
		return result;
	}
}
