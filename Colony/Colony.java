package Colony;

import java.util.ArrayList;

public class Colony implements ColonyI {

	protected String nest;
	protected ArrayList<AntI> ant = new ArrayList<AntI>();
	
	// constructor
	Colony(int size, String nestnode) {
			this.nest = nestnode;
		
		if (size < 1) {
			System.out.println("Number of ants generated should be at least 1.");
			System.exit(0);
		}
		
		for (int i = 0; i < size; i++) {
			this.ant.add(new Ant(nestnode));
		}
		
	}
	
	// methods
	
	public int getSize() {
		return ant.size();
	}
	
	public String getNest() {
		return nest;
	}
	
	public ArrayList<AntI> getAnts() {
		return ant;
	}
}
