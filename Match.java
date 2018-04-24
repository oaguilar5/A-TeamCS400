
/**
 * The Match class has constructor for making a Match object and has a method
 * for indicating who is the winner of the match. 
 */
public class Match {
	private Challenger c1;
	private Challenger c2;
	
	/**
	 * The constructor for match object by receiving two challenger object
	 * as a parameter
	 * 
	 * @param Challegner c1 -first challenger
	 * @param Challenger c2 -second challenger
	 */
	public Match(Challenger c1, Challenger c2) {
		this.c1 = c1;
		this.c2 = c2;
	}
	
	/**
	 * This method shows the winner of the match object. 
	 */
	public Challenger getWinner() {
		if(c1.getScore() > c2.getScore()) {
			return c1;
		}
		else
			return c2;  
	}

}
