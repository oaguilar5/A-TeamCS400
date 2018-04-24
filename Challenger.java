
///////////////////////////////////////////////////////////////////////////////
//Title:            CS400MileStone2
//
//Files:            Challenger.java
//					Match.java
//					Tournament.java
//					BracketGUI.java
//
//Semester:         CS400 Spring 2018
//
//Author:           Jinhyung Ahn, Oscar Aguilar, Zachary Wille
//Email:            jahn36@wisc.edu, aguilarruval@wisc.edu, 
//					zwille@wisc.edu
//
//Lecturer's Name:  Deb Deppeler
//
//Bugs:				no known bugs. 
////////////////////////////80 columns wide //////////////////////////////////
/**
 * The Challenger class and a constructor for instantiating Challenger object 
 * and accessor and mutator methods for its name and the score. 
 */
public class Challenger {
	private String name;
	private int score;
	
	/**
	 * The constructor for Challenger object by receiving name of the challenger
	 * 
	 * @param String name
	 * 					name of the challenger
	 */
	public Challenger(String name) {
		this.name = name;
	}
	/**
	 * The accessor method of the Challenger object's name.
	 * 
	 * @return String   name of the challenger
	 */
	public String getName() {
		return name;
	}
	/**
	 * The mutator method for the score of the Challenger
	 * 
	 * @param int score   score of the challenger 
	 */
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * The accessor method for the Challenger object's score
	 * 
	 * @return int   	score of the challenger
	 */
	public int getScore() {
		return score; 
	}
}
