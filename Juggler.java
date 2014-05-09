/**
 * @author: Amanda Swinton
 * 
 * Juggler.java sets up a juggler object that holds the statistics of a juggler and 
 * a list of preferred circuits 
 */

import java.util.ArrayList;


public class Juggler {

	private int id;
	private int hand;
	private int endurance;
	private int pizzazz;
	private ArrayList<Integer> preferred;
	//the string version of the prefered list
	private String pre;
	int index;

	
	public Juggler(int i, int h, int e, int p, String pre) {

		id = i;
		hand = h;
		endurance = e;
		pizzazz = p;
		this.pre = pre;

		preferred = new ArrayList<Integer>();
		parsePreferred(pre);

		index = 0;
	}

	//parses the string of preferred circuits into an arrayList
	private void parsePreferred(String p){
		String[] temp = p.split(",");

		for (String t: temp){
			preferred.add(Integer.parseInt(t));
		}
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

	//increments index for which preferred circuit to try.
	//returns -1 when the end of the list is reached.
	public int getTopPref(){
		if (index < preferred.size()){
			index ++;
			return preferred.get(index-1);
		} else {
			return -1;
		}
	}

	public String getPrefString(){
		return pre;
	}

	public String toString(){
		return "J" + id;
	}
}
