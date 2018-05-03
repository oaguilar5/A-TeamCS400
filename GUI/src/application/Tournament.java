package application;
///////////////////////////////////////////////////////////////////////////////
//Title:            CS400MileStone3
//
//Files:            Challenger.java
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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The Tournament class is like the control panel of Challenger and Match 
 * object. It has methods to make matches between challengers according to
 * the ranking and reading the text file to populate challengers.
 */
public class Tournament {
	//the array of challengers with the information receiving from text file
	//the index of challengers are stored according their ranking
	private Challenger[] challengers;
	/**
	 * The readFile method receives String of filePath and populate the
	 * challenger array according to the order of the challenger in the
	 * text file. It returns Challenger array which is filled according 
	 * to the ranking of the challengers
	 * 
	 * @param String filePath
	 * 					text filepath that contains challengers in order
	 * @return Challenger[]
	 * 					array filled with challenger in order of the ranking
	 */
	public Challenger[] readFile(String filePath) {
		ArrayList<String> teamNames = new ArrayList<String>();
		try {
			//use stream to read file  and if filePath is null
			if(filePath == null) {
				throw new NullPointerException("invalid filePath");
			}
			//using Stream<String> to read files 
			Stream<String> stream = Files.lines(Paths.get(filePath));
			stream = stream.map(String::toUpperCase).map(String::trim).filter(x -> x!= null && !x.equals(""));
		 	List<String> listChallengers = stream.collect(Collectors.toList());	

		 	for(String s: listChallengers) {
		 		teamNames.add(s);
		 	}
		 	if((teamNames.size() == 0) || (teamNames.size() == 1) ||
		 		(teamNames.size() == 2) || (teamNames.size() == 4) ||
		 		(teamNames.size() == 8) || (teamNames.size() == 16)) {
		 		challengers = new Challenger[teamNames.size()];
		 		Challenger[] c1 = new Challenger[teamNames.size()];
		 		for(int i = 0; i < c1.length; i++) {
		 			c1[i] = new Challenger("");
		 		}
			 	//populating the challenger array
			 	if(challengers.length <= 1) {
			 		for(int i = 0; i < challengers.length; i++) {
			 			challengers[i] = new Challenger(teamNames.get(i));
			 		}
			 	}
			 	else {
			 	int j = teamNames.size()-1;
			 	int k = 0;
			 	for(int i = 0; i < challengers.length; i+=2) {
			 		challengers[i] = new Challenger(teamNames.get(k));
			 		challengers[i+1] = new Challenger(teamNames.get(j));
			 		k++;
			 		j--;
			 	}
			 	//hard-code for the array when there are 8 challengers
			 	if(challengers.length == 8) {
			 		c1[0] = challengers[0];
			 		c1[1] = challengers[1];
			 		c1[2] = challengers[6];
			 		c1[3] = challengers[7];
			 		c1[4] = challengers[2];
			 		c1[5] = challengers[3];
			 		c1[6] = challengers[4];
			 		c1[7] = challengers[5];
			 		challengers = c1; 
			 	}
			 	//hard-code for the array when there are 16 challengers
			 	else if (challengers.length == 16) {
			 		c1[0] = challengers[0];
			 		c1[1] = challengers[1];
			 		c1[2] = challengers[14];
			 		c1[3] = challengers[15];
			 		c1[4] = challengers[6];
			 		c1[5] = challengers[7];
			 		c1[6] = challengers[8];
			 		c1[7] = challengers[9];
			 		c1[8] = challengers[2];
			 		c1[9] = challengers[3];
			 		c1[10] = challengers[12];
			 		c1[11] = challengers[13];
			 		c1[12] = challengers[4];
			 		c1[13] = challengers[5];
			 		c1[14] = challengers[10];
			 		c1[15] = challengers[11];
			 		challengers = c1;
			 	}
		 	}
		 	}
		 	//when the text file has not appropriate number of challengers, it throws
		 	//illegal argument exception 
		 	else {
		 		throw new IllegalArgumentException("Not appropriate number of challengers");
		 	}
		 	} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return challengers; 
	}
	
	/**
	 * The matchMaking method receives the file of Challengers and create
	 * matches between the challenger according to the ranking. For example,
	 * 1st place will have a match with 16th place if there are 16 teams
	 * @param Challenger[] challengers
	 * 					array filled with challengers according to their
	 * 					ranking
	 */
	public String thirdPlace(Challenger[] c) {
		if(c[0].getScore() > c[1].getScore()) {
			return c[0].getName();
		}
		else
		return c[1].getName();
	}
}
