
public class Player {
	private String name;
	private String team;
	private int rank;
	private int bye;
	private String position;
	
	public Player(String name, String position, String team, int rank, int bye) {
		this.name = name;
		this.team = team;
		this.rank = rank;
		this.bye = bye;
		this.position = position;
	}
	
	public String toString() {
		return rank + ": " + name + " " + position + " " + team + " bye week : " + bye;
	}

	public String getName(){
		return name;
	}
}
