import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Queue;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.lang.Math;


public class Draft{
	private List<Player> draftBoard;
	private List<Player> myTeam;
	private List<Player> allPlayers;
	private HashTableMap<Integer,Player> otherTeams;
	private Queue<Player> myQueue = new PriorityQueue<>();

	public Draft(){
		FootballLoader load = new FootballLoader();
		try {
			draftBoard = load.loadPlayers("fantasy.xml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		allPlayers = draftBoard;
		otherTeams = new HashTableMap<Integer,Player>(9);
	}

	public void runDraft(){
		//Simulate other picks
		Scanner kb = new Scanner(System.in);

		System.out.println("Welcome to Fantasy Football Draft Simulation");
		System.out.println("What draft pick do you have?");
		int mypick = Integer.parseInt(kb.next());
		int currPick = 1;
		int round = 0;
		int ratio = 5;

		//For the entire draft
		while(round <= 16){

			//other teams picks
			for(int i = 1; i < 10; i++){
				if(currPick != mypick){
					int picknum = (int)(Math.random()*ratio) + 1;
					Player p = draftBoard.get(picknum);
					otherTeams.put(i,p);
					draftBoard.remove(picknum);
					System.out.println("team " + i + " drafted " + p);
				}else{
					System.out.println("Your pick is up!");
					//MAKE YOUR SELECTION
					System.out.println("Press 1 to make your selection, 2 to queue for autodraft, and 3 to autodraft");
					if(kb.nextInt() == 1){
						System.out.println("Enter First Name");
						String firstN = kb.next();
						System.out.println("Enter Last Name");
						String lastN = kb.next();
						String namePick = firstN + " " + lastN;
						System.out.println("PICK :" + namePick);
						for(int j = 0; j <draftBoard.size(); j++){
							if(namePick.equals(draftBoard.get(j).getName())){
								System.out.println("PICKED");
								System.out.println("THIS" + draftBoard.get(j));
								Player pick = draftBoard.get(j);
								System.out.println("PICK" + pick);
								myTeam.add(pick);
								System.out.println("MADE IT");
								draftBoard.remove(j);
							}
							//else{
							//	if(allPlayers.contains(draftBoard.get(j))){
							//		System.out.println("Player has already been drafted");
							//	}else{
							//		System.out.println("Player cannot be found");
							////	}
							//}
						}
					}
					//ADD PLAYERS TO QUEUE//Custom autodraft
					if(kb.nextInt() == 2){
						System.out.println("adding to queue");
					}
					// AUTODRAFT
					if(kb.nextInt() == 3){
						System.out.println("autodraft");
					}
				}
				currPick++;
			}
			ratio+=5;
			round++;
		}

	}

}