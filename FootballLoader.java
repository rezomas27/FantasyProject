import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class FootballLoader {

	public List<Player> loadPlayers(String filepath) throws FileNotFoundException{
		Scanner scnr = new Scanner(new File(filepath));

		System.out.println("CALLED");
		
		scnr.nextLine();
		String line = null;
		String name = "";
		String team = "";
		String pos = "";
		int rank = 0;
		int bye = 0;
		List<Player> list = new ArrayList<Player>();
		while(scnr.hasNext()) {
			line = scnr.nextLine();
			
			if(line.contains("<RK>")) {
				rank = Integer.parseInt(scnr.next());
			}else if(line.contains("<PLAYER-NAME>")) {
				name = scnr.nextLine().strip();
			}else if(line.contains("<TEAM>")) {
				team = scnr.next();
			}else if(line.contains("<POS>")) {
				pos = scnr.next();
			}else if(line.contains("<BYE-WEEK>")) {
				bye = Integer.parseInt(scnr.next());
			}else if(line.contains("<dataitem>")) {
				//NEW PLAYER ADD TO LIST
				
				Player p = new Player(name,team,pos,rank,bye);
				System.out.println(p);
				list.add(p);
			}
		}
		
		
		return list;
		
		
	}
}

