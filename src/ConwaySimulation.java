import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.paint.Color;

public class ConwaySimulation extends Simulation{
	
	private static final String DEAD = "DEAD";
	private static final String ALIVE = "ALIVE";	
	
	public ConwaySimulation(Grid g) {
		super(g);
		initStates();
	}
	
	protected void initStates() {
		states.put(DEAD, Color.BLACK);
		states.put(ALIVE, Color.WHITE);
	}
	
	protected String nextState(Grid g, Cell c, Neighborhood n) {
		ArrayList<Cell> neighbors = n.getNeighbors(g,  c);
		int alive = 0;
		for(Cell nc: neighbors) if(nc.getColor() == Color.WHITE) alive++;
		if(c.getColor() == Color.WHITE) {
			if(alive<2 || alive > 3) return DEAD;
			else return ALIVE;
		}
		else {
			if(alive == 3) return ALIVE;
			else return DEAD;
		}
	}
}
