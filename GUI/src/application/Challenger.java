package application;
///////////////////////////////////////////////////////////////////////////////
//Title:            CS400MileStone3
//
//Files:            Challenger.java
//Tournament.java
//BracketGUI.java
//
//Semester:         CS400 Spring 2018
//
//Author:           Jinhyung Ahn, Oscar Aguilar, Zachary Wille
//Email:            jahn36@wisc.edu, aguilarruval@wisc.edu, 
//zwille@wisc.edu
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
	//the name of the challenger
	private String name;
	//the score of the challenger
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
	 * The mutator method for the name of the Challenger
	 * 
	 * @param String name  new name of the challenger 
	 */
	public void setName(String name) {
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
