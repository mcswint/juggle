/**
 * @author: Amanda Swinton
 * 
 * AssignTeams.java takes a file name that contains the circuit and juggler
 * data in the constructor, parses it, assigns the team, and then outputs it
 * to a file called output.txt
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class AssignTeams {

	private String dataFile;
	private ArrayList<Juggler> jugglers;
	//staticJuggler contains all the jugglers in their original order
	private ArrayList<Juggler> staticJugglers;
	private ArrayList<Circuit> circuits;
	private ArrayList<Juggler> matched;
	private ArrayList<Juggler> failed;
	int teamSize;

	public AssignTeams(String df){

		dataFile = df;

		jugglers = new ArrayList<Juggler>();
		circuits = new ArrayList<Circuit>();
		matched = new ArrayList<Juggler>();
		failed = new ArrayList<Juggler>();

		try {
			importData();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//copy of jugglers
		staticJugglers = new ArrayList<Juggler>(jugglers);

		teamSize = jugglers.size() / circuits.size();

		for (Circuit c: circuits){
			c.setTeamSize(teamSize);
		}

	}

	//contains the methods needed for the program to complete
	public void run(){
		assign();
		assignRest();
		try {
			writeOut();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//imports the data from the file and parses it
	public void importData() throws FileNotFoundException{
		File data = new File(dataFile);

		Scanner in = new Scanner(data);

		while (in.hasNext()){
			String line = in.nextLine();
			if (line.length() > 1){
				if (line.substring(0,1).equals("C")){
					//takes out the letters
					line = line.replaceAll("[A-Za-z:]", "");
					String[] tempC = line.split(" ");
					int id= Integer.parseInt(tempC[1]);
					int hand = Integer.parseInt(tempC[2]);
					int endu = Integer.parseInt(tempC[3]);
					int pizz = Integer.parseInt(tempC[4]);
					circuits.add(new Circuit(id, hand, endu, pizz));

				} else if (line.substring(0,1).equals("J")){
					line = line.replaceAll("[A-Za-z:]", "");
					String[] tempJ = line.split(" ");
					int id= Integer.parseInt(tempJ[1]);
					int hand = Integer.parseInt(tempJ[2]);
					int endu = Integer.parseInt(tempJ[3]);
					int pizz = Integer.parseInt(tempJ[4]);
					String cir = tempJ[5];
					jugglers.add(new Juggler(id, hand, endu, pizz, cir));

				}
			}
		}
		in.close();
	}

	public void assign(){
		//first pass through the list. stops when all the jugglers have
		//moved to matched or failed
		while (!(jugglers.isEmpty())){
			Juggler current = jugglers.get(0);
			int topPref = current.getTopPref();
			
			//topPref will equal -1 when it has gone through all preferences 
			//add unmatched jugglers to failed
			if (topPref == -1) {
				failed.add(current);
				jugglers.remove(0);
			} else {
				//try to add juggler to preferred circuit
				MatchRank tempMatch = new MatchRank(current, circuits.get(topPref));
				Juggler tempJ = circuits.get(topPref).addMember(tempMatch);

				//if the add was successful with no conflicts
				if (tempJ.getId() == -1){
					matched.add(current);
					jugglers.remove(current);
					
					//return juggler to the end of the list
				} else {
					jugglers.remove(current);
					jugglers.add(tempJ);
				}
			}
		}
	}

	//after jugglers is empty, ignore preference and match the rest of the
	//jugglers up with the circuits with open spots based on rank alone.
	public void assignRest(){
		//get a list of circuits with open spots
		ArrayList<Circuit> notFull = new ArrayList<Circuit>();
		for(Circuit c: circuits){
			if (c.getMembersCount() != teamSize){
				notFull.add(c);		
			}
		}

		//add remaining juggler with top rank
		for(Circuit c: notFull){
			while(c.getMembersCount() != teamSize){
				MatchRank topMatch = new MatchRank(failed.get(0), c);
				for (int i = 1; i<failed.size(); i++){
					MatchRank temp = new MatchRank(failed.get(i), c);
					if (temp.compareTo(topMatch) == 1){
						topMatch = temp;
					}
				}
				c.addMember(topMatch);
				failed.remove(topMatch.getJuggler());
				matched.add(topMatch.getJuggler());
			}
		}

	}

	//writes output to file called output.txt
	public void writeOut() throws FileNotFoundException{
		PrintWriter out = new PrintWriter("output.txt");

		for (Circuit c: circuits){
			String temp = c.toString() + " ";
			String memList[] = c.getMembers().split(" ");
			for (String s: memList){
				s=s.substring(1,s.length());
				int jIndex = Integer.parseInt(s);
				temp += staticJugglers.get(jIndex).toString() + " ";
				String[] prefString = staticJugglers.get(jIndex).getPrefString().split(",");
				for (String p: prefString){
					int cIndex = Integer.parseInt(p);
					MatchRank tempMatch = new MatchRank(staticJugglers.get(jIndex), circuits.get(cIndex));
					temp += circuits.get(cIndex).toString() + ":" + tempMatch.getRank() + " ";

				}
				temp += ", ";
			} 
			String noComma = temp.substring(0, temp.length()-2);
			out.println(noComma);
			temp = "";
		}
		out.close();

	}
	
	//method to help with testing 

	/*public void testPrint(){
			System.out.println("circuits ");
			System.out.println(circuits);

		System.out.println("matched number " + matched.size());
		System.out.println("failed number " + failed.size());
		System.out.println(failed);
		System.out.println(circuits.get(1970));
	}
	 */
	 

}
