package Colony;

import java.util.ArrayList;

public class Colony {

	protected String nest;
	protected ArrayList<Ant> ant = new ArrayList<Ant>();
	
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
	
	public ArrayList<Ant> getAnts() {
		return ant;
	}
}
