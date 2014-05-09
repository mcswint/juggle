/**
 * @author: Amanda Swinton
 * 
 * MatchRank.java is a comparable class that takes a juggler and
 * a circuit and calculates the dot product, which is saved
 * as the rank 
 */


public class MatchRank implements Comparable <MatchRank> {
	
	private Juggler j;
	private Circuit c;
	private int rank;
	
	public MatchRank(Juggler j, Circuit c){
		this.j = j;
		this.c = c;
		
		rank = findRank();
	}

	//returns the rank
	private int findRank(){
		int h = j.getHand() * c.getHand();
		int e = j.getEndurance() * c.getEndurance();
		int p = j.getPizzazz() * c.getPizzazz();
		
		return h + e + p;
	}
	
	public int getRank(){
		return rank;
	}

	//compare by rank
	@Override
	public int compareTo(MatchRank m) {
		// TODO Auto-generated method stub
		if (rank > m.getRank()){
			return 1;
		} else if (rank < m.getRank()){
			return -1;
		} else 
		return 0;
	}

	public Juggler getJuggler(){
		return j;
	}
}
