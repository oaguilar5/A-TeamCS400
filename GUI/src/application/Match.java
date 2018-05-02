package application;


public class Match {
	Challenger c1;
	Challenger c2;
	
	public Match(Challenger c1, Challenger c2) {
		this.c1 = c1;
		this.c2 = c2;
	}
	
	public Challenger getWinner() {
		if(c1.getScore() > c2.getScore()) {
			return c1;
		}
		else
			return c2;  
	}

}