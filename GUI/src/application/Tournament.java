package application;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tournament {
	
	public Challenger[] challengers;
	public Match[] matches;
	
	public Challenger[] readFile(String filePath) {
		ArrayList<String> teamNames = new ArrayList();
		try {
			Stream<String> stream = Files.lines(Paths.get(filePath));
			stream = stream.map(String::toUpperCase).map(String::trim).filter(x -> x!= null && !x.equals(""));
		 	List<String> listChallengers = stream.collect(Collectors.toList());	
		 	for(String s: listChallengers) {
		 		teamNames.add(s);
		 	}
		 	challengers = new Challenger[teamNames.size()];
		 	for(int i = 0; i < challengers.length; i++) {
		 		System.out.println(teamNames.get(i));
		 		challengers[i] = new Challenger(teamNames.get(i));
		 	}
		 	} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		matchMaking(challengers);
		return challengers; 
	}
	public void matchMaking(Challenger[] challengers) { 
		matches = new Match[challengers.length/2];
		for(int i = 0; i < matches.length; i++) {
			for(int j = challengers.length-1; j > matches.length-1; j--)
			matches[i] = new Match(challengers[i], challengers[j]);
		}
	}
	public void tournament (Match[] matches) {
		Match[] newMatches = new Match[matches.length/2];
		for(int i = 0; i <newMatches.length; i++) {
			int j = 0;
			newMatches[i] = new Match(matches[j].getWinner(), matches[j+1].getWinner());
			j+=2;
		}
	}

}