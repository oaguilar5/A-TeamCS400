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
		 	for(int i = 0; i < challengers.length; i++) {
		 		challengers[i] = new Challenger(teamNames.get(i));
		 	}
		 	} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		matchMaking(challengers);
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
	public void matchMaking(Challenger[] challengers) { 
		matches = new Match[challengers.length/2];
		for(int i = 0; i < matches.length; i++) {
			for(int j = challengers.length-1; j > matches.length-1; j--)
			matches[i] = new Match(challengers[i], challengers[j]);
		}
	}
	
	/**
	 * The tournament method receives match and reset the match after
	 * each round
	 * 
	 * @param Match[] matches
	 * 						Match object with the result of the match
	 */
	public void tournament (Match[] matches) {
		Match[] newMatches = new Match[matches.length/2];
		for(int i = 0; i <newMatches.length; i++) {
			int j = 0;
			newMatches[i] = new Match(matches[j].getWinner(), matches[j+1].getWinner());
			j+=2;
		}
	}

}
