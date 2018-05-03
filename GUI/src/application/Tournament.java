package application;
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
	
	public Challenger[] challengers;
	public Match[] matches;
	
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
			//use stream to read file 
			if(filePath == null) {
				throw new NullPointerException("invalid filePath");
			}
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
		 	}
		 	}
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
	 * The third method receives the challenger array of 3rd place and 4th place
	 * and determine the thrid place. 
	 * @param 	array filled with challengers according to their ranking
	 *
	 * @return	String the string of third place of the tournament 					
	 */
	public String thirdPlace(Challenger[] c) {
		if(c[0].getScore() > c[1].getScore()) {
			return c[0].getName();
		}
		else
		return c[1].getName();
	}
}
