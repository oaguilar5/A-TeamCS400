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
		ArrayList<String> teamNames = new ArrayList();
		try {
			//use stream to read file 
			Stream<String> stream = Files.lines(Paths.get(filePath));
			stream = stream.map(String::toUpperCase).map(String::trim).filter(x -> x!= null && !x.equals(""));
		 	List<String> listChallengers = stream.collect(Collectors.toList());	
		 	for(String s: listChallengers) {
		 		teamNames.add(s);
		 	}
		 	challengers = new Challenger[teamNames.size()];
		 	//populating the challenger array
		 	int j = teamNames.size()-1;
		 	int k = 0;
		 	for(int i = 0; i < challengers.length; i+=2) {
		 		challengers[i] = new Challenger(teamNames.get(k));
		 		challengers[i+1] = new Challenger(teamNames.get(j));
		 		k++;
		 		j--;
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
	public Challenger[] matchMaking(Challenger[] c) { 
//		matches = new Match[challengers.length/2];
//		for(int i = 0; i < matches.length; i++) {
//			for(int j = challengers.length-1; j > matches.length-1; j--)
//			matches[i] = new Match(challengers[i], challengers[j]);
//		}
		Challenger[] c2 = new Challenger[this.challengers.length/2];
		int j = 0;
		for(int i = 0; i < c2.length; i++) {
			if(c[j].getScore() < c[j+1].getScore()) {
				c2[i] = c[j+1];
			}
			else {
				c2[i] = c[j];
			}
			j+=2;
		}
		return c2;
	}

}
