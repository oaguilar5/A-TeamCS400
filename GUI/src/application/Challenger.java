package application;

public class Challenger {
	private String name;
	private int score;
	
	public Challenger(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getScore() {
		return score; 
	}
}