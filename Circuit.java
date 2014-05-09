/**
 * @author: Amanda Swinton
 * 
 * Circuit.java sets up a juggler object that holds the statistics of a circuit and 
 * a list of team members
 */

import java.util.ArrayList;
import java.util.Collections;


public class Circuit {
	
	private int id;
	private int hand;
	private int endurance;
	private int pizzazz;
	private int teamSize;
	ArrayList<MatchRank> members;

	public Circuit(int i, int h, int e, int p){
		id = i;
		hand = h;
		endurance = e;
		pizzazz = p;
		
		members = new ArrayList<MatchRank>();
		
		teamSize = 0;
	}
	
	//addMember function
	//returns a juggler with an id of -1 if the juggler is added with no conflicts
	//goes through the list comparing ranks with the existing members and the 
	//one being added. The one with the lowest rank gets returned and 
	//removed from the list if applicable. 
	public Juggler addMember(MatchRank m){
		if (members.size() < teamSize){
			members.add(m);
			Collections.sort(members);
			return new Juggler(-1, -1, -1, -1, "-1");
		} else {
			for(int i = 0; i<members.size(); i++){
				if (members.get(i).compareTo(m) == -1){
					MatchRank temp = members.get(i);
					members.remove(members.get(i));
					members.add(m);
					Collections.sort(members);
					return temp.getJuggler();
				}
			}
		} 
		return m.getJuggler();
	}
	
	public void setTeamSize(int i){
		teamSize =i;
	}
	
	public int getMembersCount(){
		return members.size();
	}
	
	//returns a String of the member list
	public String getMembers(){
		String temp = "";
		for (MatchRank m: members){
			temp += m.getJuggler() + " ";
		}
		return temp;
	}
	
	public int getId(){
		return id;
	}
	
	public int getHand(){
		return hand;
	}
	
	public int getEndurance(){
		return endurance;
	}
	
	public int getPizzazz(){
		return pizzazz;
	}
	
	public String toString(){
		return "C" + id;
	}
}
